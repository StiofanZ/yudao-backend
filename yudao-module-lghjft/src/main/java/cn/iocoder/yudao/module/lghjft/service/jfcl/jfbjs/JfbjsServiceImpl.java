package cn.iocoder.yudao.module.lghjft.service.jfcl.jfbjs;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfbjs.vo.JfbjsPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.jfbjs.JfclJfbjsDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.jfbjs.JfbjsMapper;
import cn.iocoder.yudao.module.lghjft.enums.constant.BackConstant;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.JFCL_JFBJS_NO_DATA;

@Service
@Validated
public class JfbjsServiceImpl implements JfbjsService {

    @Resource
    private JfbjsMapper jfbjsMapper;

    @Override
    public PageResult<JfclJfbjsDO> getJfbjsPage(JfbjsPageReqVO pageReqVO) {
        return jfbjsMapper.selectPage(pageReqVO);
    }

    /**
     * v1: updateJfclJfbjs — 工会经费补结算 (1:1 translation)
     *
     * 1. 查询 gh_jf 中满足条件 + jsbj='N' 的数据
     * 2. 若无数据 → 抛异常 "无有效数据进行结算"
     * 3. 对每条: jmse != 0 → jsbj='W', else → jsbj='Y'
     * 4. 设置 jsrq, updateBy, updateTime
     * 5. 批量 UPDATE gh_jf (CASE on spuuid)
     * 6. 查询 gh_qsjshkrj 中关联的入库日期日志
     * 7. 更新 gh_qsjshkrj 的 jsrq/updateBy/updateTime
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void settleJfbjs(JfbjsPageReqVO reqVO) {
        // v1: List<JfclJfbjs> jsList = jfclJfbjsMapper.selectJfclJfbjs(jfclJfbjs);
        List<Map<String, Object>> jsList = jfbjsMapper.selectJfclJfbjs(reqVO);
        if (jsList == null || jsList.isEmpty()) {
            throw exception(JFCL_JFBJS_NO_DATA);
        }

        // v1: String userId = BackConstant.USER_SYSTEM_ID;
        String userId = BackConstant.USER_SYSTEM_ID;
        Date nowDate = new Date();
        // v1: Date jsrq = jfclJfbjs.getJsrq(); if(null == jsrq) jsrq = nowDate;
        Date jsrq = nowDate;

        // v1: jsList.stream().forEach(item -> { ... })
        List<Map<String, Object>> updateList = new ArrayList<>(jsList.size());
        for (Map<String, Object> item : jsList) {
            Map<String, Object> updateItem = new HashMap<>(8);
            updateItem.put("spuuid", item.get("spuuid") != null ? item.get("spuuid") : item.get("SPUUID"));

            // v1: if (item.getJmse().compareTo(BigDecimal.ZERO) != 0) → 'W' else → 'Y'
            Object jmseObj = item.get("jmse") != null ? item.get("jmse") : item.get("JMSE");
            BigDecimal jmse = BigDecimal.ZERO;
            if (jmseObj instanceof BigDecimal) {
                jmse = (BigDecimal) jmseObj;
            } else if (jmseObj instanceof Number) {
                jmse = new BigDecimal(jmseObj.toString());
            }

            if (jmse.compareTo(BigDecimal.ZERO) != 0) {
                updateItem.put("jsbj", "W");
            } else {
                updateItem.put("jsbj", "Y");
            }
            updateItem.put("jsrq", jsrq);
            updateItem.put("updateBy", userId);
            updateItem.put("updateTime", nowDate);
            updateList.add(updateItem);
        }

        // v1: jfclJfbjsMapper.updateJfclJfbjs(jsList);
        jfbjsMapper.updateJfclJfbjs(updateList);

        // v1: 更新表 gh_qsjshkrj 字段 jsrq
        // List<Date> rurqList = jsList.stream().map(JfclJfbjs::getRkrq).distinct().collect(Collectors.toList());
        List<Date> rurqList = jsList.stream()
                .map(item -> {
                    Object rkrqObj = item.get("rkrq") != null ? item.get("rkrq") : item.get("RKRQ");
                    if (rkrqObj instanceof Date) {
                        return (Date) rkrqObj;
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        if (!rurqList.isEmpty()) {
            List<Map<String, Object>> qsjshkrjList = jfbjsMapper.selectQsjshkrjList(rurqList);
            if (qsjshkrjList != null && !qsjshkrjList.isEmpty()) {
                // v1: temp_.setJsrq(DateUtils.dateTime(finalJsrq));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String jsrqStr = sdf.format(jsrq);
                List<Map<String, Object>> updateQsjList = new ArrayList<>(qsjshkrjList.size());
                for (Map<String, Object> temp : qsjshkrjList) {
                    Map<String, Object> updateQsj = new HashMap<>(8);
                    updateQsj.put("rkrq", temp.get("rkrq") != null ? temp.get("rkrq") : temp.get("RKRQ"));
                    updateQsj.put("jsrq", jsrqStr);
                    updateQsj.put("updateBy", userId);
                    updateQsj.put("updateTime", nowDate);
                    updateQsjList.add(updateQsj);
                }
                jfbjsMapper.updateBatchQsjshkrj(updateQsjList);
            }
        }
    }
}
