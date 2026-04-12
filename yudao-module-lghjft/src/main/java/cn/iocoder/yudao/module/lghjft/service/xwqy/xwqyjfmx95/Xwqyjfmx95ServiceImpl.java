package cn.iocoder.yudao.module.lghjft.service.xwqy.xwqyjfmx95;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjfmx95.vo.Xwqyjfmx95PageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xwqy.xwqyjf.XwqyjfDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xwqy.xwqyjfmx95.Xwqyjfmx95Mapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class Xwqyjfmx95ServiceImpl implements Xwqyjfmx95Service {

    @Resource
    private Xwqyjfmx95Mapper xwqyjfmx95Mapper;

    @Override
    public PageResult<XwqyjfDO> getXwqyjfmx95Page(Xwqyjfmx95PageReqVO pageReqVO) {
        fillDeptId(pageReqVO);
        pageReqVO.setOffset((pageReqVO.getPageNo() - 1) * pageReqVO.getPageSize());
        List<XwqyjfDO> list = xwqyjfmx95Mapper.selectXwqyjfmx95List(pageReqVO);
        long total = xwqyjfmx95Mapper.selectXwqyjfmx95Count(pageReqVO);
        return new PageResult<>(list, total);
    }

    @Override
    public List<XwqyjfDO> getXwqyjfmx95List(Xwqyjfmx95PageReqVO pageReqVO) {
        fillDeptId(pageReqVO);
        pageReqVO.setOffset(null);
        return xwqyjfmx95Mapper.selectXwqyjfmx95List(pageReqVO);
    }

    private void fillDeptId(Xwqyjfmx95PageReqVO req) {
        if (req.getDeptId() == null || req.getDeptId().isEmpty()) {
            Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
            if (loginDeptId != null) {
                req.setDeptId(loginDeptId.toString());
            }
        }
        if ("100000".equals(req.getDeptId())) {
            req.setDeptId(null);
        }
    }
}
