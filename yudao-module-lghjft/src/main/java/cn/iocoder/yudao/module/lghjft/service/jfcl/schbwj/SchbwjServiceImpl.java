package cn.iocoder.yudao.module.lghjft.service.jfcl.schbwj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.schbwj.vo.SchbwjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.schbwj.vo.SchbwjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.schbwj.JfclSchbwjDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.schbwj.SchbwjMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.JFCL_NOT_EXISTS;

@Service
@Validated
public class SchbwjServiceImpl implements SchbwjService {

    @Resource
    private SchbwjMapper schbwjMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createSchbwj(SchbwjSaveReqVO createReqVO) {
        JfclSchbwjDO entity = BeanUtils.toBean(createReqVO, JfclSchbwjDO.class);
        schbwjMapper.insert(entity);
        return entity.getHkxxId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSchbwj(SchbwjSaveReqVO updateReqVO) {
        validateSchbwjExists(updateReqVO.getGhjfId());
        JfclSchbwjDO updateObj = BeanUtils.toBean(updateReqVO, JfclSchbwjDO.class);
        schbwjMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSchbwj(Long id) {
        validateSchbwjExists(id);
        schbwjMapper.deleteById(id);
    }

    @Override
    public JfclSchbwjDO getSchbwj(Long id) {
        return schbwjMapper.selectById(id);
    }

    @Override
    public PageResult<JfclSchbwjDO> getSchbwjPage(SchbwjPageReqVO pageReqVO) {
        return schbwjMapper.selectPage(pageReqVO);
    }

    private void validateSchbwjExists(Long id) {
        if (schbwjMapper.selectById(id) == null) {
            throw exception(JFCL_NOT_EXISTS);
        }
    }

    @Override
    public String generateHbData(String jsrqStart, String jsrqEnd, java.util.Date hkrq) {
        // 1. 查询待划拨经费数据
        java.util.List<java.util.Map<String, Object>> jsList = schbwjMapper.selectGhjfhb(jsrqStart, jsrqEnd);
        if (jsList == null || jsList.isEmpty()) {
            return "无有效数据进行划拨";
        }

        // 2. 获取划款批次号
        String dqrq = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
        String hkpch = schbwjMapper.selectHkpch(dqrq);
        if (hkpch == null) {
            hkpch = dqrq + "00001";
        } else {
            hkpch = String.valueOf(Long.parseLong(hkpch) + 1);
        }

        // 3. 从 schbwj VIEW 获取汇总数据并生成 gh_hkxx
        java.util.List<java.util.Map<String, Object>> hkxxList = schbwjMapper.getSchbwjList(jsrqStart, jsrqEnd);
        if (hkxxList != null && !hkxxList.isEmpty()) {
            String userId = "1"; // system user
            java.util.Date now = new java.util.Date();
            int xh = 1;
            for (java.util.Map<String, Object> item : hkxxList) {
                item.put("xh", (long) xh++);
                item.put("hkpch", hkpch);
                item.put("thbj", "N");
                item.put("scbz", "N");
                item.put("jym", java.util.UUID.randomUUID().toString());
                item.put("createBy", userId);
                item.put("updateBy", userId);
                item.put("createTime", now);
                item.put("updateTime", now);
            }
            schbwjMapper.deleteHkxxByHkpch(hkpch);
            schbwjMapper.insertBatchHkxx(hkxxList);
        }

        // 4. 更新 gh_jf 的 HKPCH
        String userId = "1";
        java.util.Date now = new java.util.Date();
        for (java.util.Map<String, Object> item : jsList) {
            item.put("hkpch", hkpch);
            item.put("updateBy", userId);
            item.put("updateTime", now);
        }
        schbwjMapper.updateGhjfhb(jsList);

        // 5. 更新 gh_qsjshkrj 划拨日志
        java.util.List<java.util.Date> rkrqList = jsList.stream()
                .map(m -> (java.util.Date) m.get("RKRQ"))
                .filter(java.util.Objects::nonNull)
                .distinct()
                .collect(java.util.stream.Collectors.toList());
        if (!rkrqList.isEmpty()) {
            java.util.List<java.util.Map<String, Object>> qsjList = schbwjMapper.selectQsjshkrjList(rkrqList);
            if (qsjList != null && !qsjList.isEmpty()) {
                java.util.Date finalHkrq = hkrq != null ? hkrq : now;
                String finalHkpch = hkpch;
                for (java.util.Map<String, Object> q : qsjList) {
                    q.put("hkrq", new java.text.SimpleDateFormat("yyyy-MM-dd").format(finalHkrq));
                    q.put("pch", finalHkpch);
                    q.put("updateBy", userId);
                    q.put("updateTime", now);
                }
                schbwjMapper.updateBatchQsjshkrj(qsjList);
            }
        }

        return "成功生成划拨数据，划款批次号：" + hkpch;
    }
}
