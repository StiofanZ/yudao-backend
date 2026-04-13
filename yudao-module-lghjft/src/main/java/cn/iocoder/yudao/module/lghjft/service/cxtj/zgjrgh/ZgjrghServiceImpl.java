package cn.iocoder.yudao.module.lghjft.service.cxtj.zgjrgh;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zgjrgh.vo.ZgjrghPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.zgjrgh.ZgjrghDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.zgjrgh.ZgjrghMapper;
import cn.iocoder.yudao.module.lghjft.framework.deptfilter.DeptFilterHelper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ZgjrghServiceImpl implements ZgjrghService {

    @Resource
    private ZgjrghMapper zgjrghMapper;

    @Resource
    private DeptFilterHelper deptFilterHelper;

    @Override
    public ZgjrghDO getZgjrgh(Long id) {
        return zgjrghMapper.selectById(id);
    }

    @Override
    public PageResult<ZgjrghDO> getZgjrghPage(ZgjrghPageReqVO pageReqVO) {
        // 应用部门过滤逻辑（还原 V1 行为：root = "100000"）
        pageReqVO.setDeptId(deptFilterHelper.filterDeptId(pageReqVO.getDeptId()));
        normalizeLegacyDateFields(pageReqVO);
        return zgjrghMapper.selectPage(pageReqVO);
    }

    private void normalizeLegacyDateFields(ZgjrghPageReqVO pageReqVO) {
        pageReqVO.setSkssqq(normalizeLegacyDateField(pageReqVO.getSkssqq()));
        pageReqVO.setSkssqz(normalizeLegacyDateField(pageReqVO.getSkssqz()));
        pageReqVO.setBeginRkrq(normalizeLegacyDateField(pageReqVO.getBeginRkrq()));
        pageReqVO.setEndRkrq(normalizeLegacyDateField(pageReqVO.getEndRkrq()));
        pageReqVO.setBeginJsrq(normalizeLegacyDateField(pageReqVO.getBeginJsrq()));
        pageReqVO.setEndJsrq(normalizeLegacyDateField(pageReqVO.getEndJsrq()));
    }

    private String normalizeLegacyDateField(String value) {
        if (value == null || value.isBlank()) {
            return value;
        }
        String digitsOnly = value.replaceAll("\\D", "");
        return digitsOnly.isEmpty() ? value : digitsOnly;
    }
}
