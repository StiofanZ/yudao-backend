package cn.iocoder.yudao.module.lghjft.service.jfcl.schbwj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.schbwj.vo.SchbwjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.schbwj.vo.SchbwjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.schbwj.JfclSchbwjDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.schbwj.SchbwjMapper;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 经费处理-生成划拨文件 Service实现
 * 1:1 translation of v1 JfclSchbwjServiceImpl (143 lines, async thread, synchronized getHkpch)
 */
@Service
@Validated
public class SchbwjServiceImpl implements SchbwjService {

    private static final Logger logger = LoggerFactory.getLogger(SchbwjServiceImpl.class);

    @Resource
    private SchbwjMapper schbwjMapper;

    @Override
    public PageResult<JfclSchbwjDO> getSchbwjPage(SchbwjPageReqVO pageReqVO) {
        // v1 selecList: paginated via XML with LEFT JOIN sys_user
        long count = schbwjMapper.selecListCount(pageReqVO);
        if (count == 0) {
            return PageResult.empty();
        }
        // Calculate offset for XML LIMIT/OFFSET; null = no pagination (export)
        if (pageReqVO.getPageSize() != null && pageReqVO.getPageSize() > 0) {
            pageReqVO.setOffset((pageReqVO.getPageNo() - 1) * pageReqVO.getPageSize());
        } else {
            pageReqVO.setOffset(null);
        }
        List<JfclSchbwjDO> list = schbwjMapper.selecList(pageReqVO);
        return new PageResult<>(list, count);
    }

    /**
     * v1 updateGhjfhb: queries gh_jf, then starts async Thread(UpdateGhjfhbRunnable)
     */
    @Override
    public String updateGhjfhb(SchbwjSaveReqVO reqVO) {
        String jsrqStart = reqVO.getJsrqStart();
        String jsrqEnd = reqVO.getJsrqEnd();
        List<Map<String, Object>> jsList = schbwjMapper.selectGhjfhb(jsrqStart, jsrqEnd);
        if (jsList == null || jsList.isEmpty()) {
            return "无有效数据进行划拨";
        }
        try {
            // v1: async thread
            Date hkrq = null;
            if (reqVO.getHkrq() != null && !reqVO.getHkrq().isEmpty()) {
                hkrq = new SimpleDateFormat("yyyy-MM-dd").parse(reqVO.getHkrq());
            }
            Thread thread = new Thread(new UpdateGhjfhbRunnable(jsrqStart, jsrqEnd, hkrq, jsList));
            thread.start();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "生成划拨数据异常";
        }
        return "成功进入生成划拨数据，请稍后查看结果";
    }

    /**
     * v1 getHkpch: synchronized, generates batch number from MAX(HKPCH) + 1
     */
    private synchronized String getHkpch() {
        String dqrq = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String hkpch = schbwjMapper.selectHkpch(dqrq);
        if (hkpch == null) {
            hkpch = dqrq + "00001";
        } else {
            Long hkpchl = Long.parseLong(hkpch);
            hkpchl++;
            hkpch = String.valueOf(hkpchl);
        }
        return hkpch;
    }

    /**
     * v1 UpdateGhjfhbRunnable inner class — async allocation generation
     */
    class UpdateGhjfhbRunnable implements Runnable {

        private final String jsrqStart;
        private final String jsrqEnd;
        private final Date hkrq;
        private final List<Map<String, Object>> jsList;

        public UpdateGhjfhbRunnable(String jsrqStart, String jsrqEnd, Date hkrq,
                                    List<Map<String, Object>> jsList) {
            this.jsrqStart = jsrqStart;
            this.jsrqEnd = jsrqEnd;
            this.hkrq = hkrq;
            this.jsList = jsList;
        }

        @Override
        public void run() {
            logger.info("划拨开始：jsrqStart={}, jsrqEnd={}", jsrqStart, jsrqEnd);
            // v1: BackConstant.USER_SYSTEM_ID = "system"
            String userId = "system";
            Date nowDate = new Date();
            Date finalHkrq = hkrq;
            if (finalHkrq == null) {
                finalHkrq = nowDate;
            }
            String hkpch = getHkpch();

            // v1: GH_HKXX — delete by hkpch first, then insert
            schbwjMapper.deleteHkxxByHkpch(hkpch);
            List<Map<String, Object>> ghHkxxList = handleHkxx(userId, nowDate, hkpch);
            if (ghHkxxList == null || ghHkxxList.isEmpty()) {
                return;
            }
            schbwjMapper.insertBatchHkxx(ghHkxxList);

            // v1: update gh_jf — set HKPCH, UPDATE_BY, UPDATE_TIME for each spuuid
            for (Map<String, Object> item : jsList) {
                item.put("hkpch", hkpch);
                item.put("updateBy", userId);
                item.put("updateTime", nowDate);
                // v1 uses item.spuuid — Map keys from MySQL may be uppercase SPUUID
                if (item.get("spuuid") == null && item.get("SPUUID") != null) {
                    item.put("spuuid", item.get("SPUUID"));
                }
            }
            schbwjMapper.updateGhjfhb(jsList);

            // v1: update gh_qsjshkrj — set HKRQ, PCH
            List<Date> rkrqList = jsList.stream()
                    .map(m -> {
                        Object rkrq = m.get("RKRQ");
                        if (rkrq == null) rkrq = m.get("rkrq");
                        return (Date) rkrq;
                    })
                    .filter(Objects::nonNull)
                    .distinct()
                    .collect(Collectors.toList());
            List<Map<String, Object>> rurqListFinal = new ArrayList<>(100);
            if (!rkrqList.isEmpty()) {
                List<Map<String, Object>> qsjshkrjList = schbwjMapper.selectQsjshkrjList(rkrqList);
                if (qsjshkrjList != null && !qsjshkrjList.isEmpty()) {
                    String hkrqStr = new SimpleDateFormat("yyyy-MM-dd").format(finalHkrq);
                    for (Map<String, Object> temp : qsjshkrjList) {
                        temp.put("hkrq", hkrqStr);
                        temp.put("pch", hkpch);
                        temp.put("updateBy", userId);
                        temp.put("updateTime", nowDate);
                        // v1 uses temp.rkrq — Map keys from MySQL may be uppercase RKRQ
                        if (temp.get("rkrq") == null && temp.get("RKRQ") != null) {
                            temp.put("rkrq", temp.get("RKRQ"));
                        }
                        rurqListFinal.add(temp);
                    }
                }
            }
            if (!rurqListFinal.isEmpty()) {
                schbwjMapper.updateBatchQsjshkrj(rurqListFinal);
            }
            logger.info("划拨结束");
        }

        /**
         * v1 handleHkxx: get data from schbwj VIEW, set xh, hkpch, thbj, scbz, jym, createBy etc.
         */
        private List<Map<String, Object>> handleHkxx(String userId, Date nowDate, String hkpch) {
            AtomicInteger xh = new AtomicInteger(1);
            List<Map<String, Object>> ghHkxxList = schbwjMapper.getSchbwjList(jsrqStart, jsrqEnd);
            if (ghHkxxList != null && !ghHkxxList.isEmpty()) {
                for (Map<String, Object> item : ghHkxxList) {
                    item.put("xh", (long) xh.longValue());
                    item.put("hkpch", hkpch);
                    item.put("thbj", "N");
                    item.put("scbz", "N");
                    item.put("jym", UUID.randomUUID().toString());
                    item.put("createBy", userId);
                    item.put("updateBy", userId);
                    item.put("createTime", nowDate);
                    item.put("updateTime", nowDate);
                    // v1: map view column names — uppercase from MySQL → lowercase for insert
                    mapViewColumn(item, "LX", "lx");
                    mapViewColumn(item, "ZH", "zh");
                    mapViewColumn(item, "HH", "hh");
                    mapViewColumn(item, "JE", "je");
                    mapViewColumn(item, "DEPT_ID", "deptId");
                    mapViewColumn(item, "DZ", "dz");
                    mapViewColumn(item, "FY", "fy");
                    mapViewColumn(item, "HM", "hm");
                    mapViewColumn(item, "BZ", "bz");
                    xh.getAndAdd(1);
                }
            }
            return ghHkxxList;
        }

        /**
         * Map uppercase MySQL column key to lowercase key if needed
         */
        private void mapViewColumn(Map<String, Object> item, String upperKey, String lowerKey) {
            if (item.get(lowerKey) == null && item.get(upperKey) != null) {
                item.put(lowerKey, item.get(upperKey));
            }
        }
    }
}
