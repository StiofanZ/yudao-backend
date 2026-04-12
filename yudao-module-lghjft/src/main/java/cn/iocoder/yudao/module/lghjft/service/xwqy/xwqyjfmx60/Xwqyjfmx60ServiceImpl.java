package cn.iocoder.yudao.module.lghjft.service.xwqy.xwqyjfmx60;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjfmx60.vo.Xwqyjfmx60PageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xwqy.xwqyjfyfmx.XwqyjfyfmxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xwqy.xwqyjfmx60.Xwqyjfmx60Mapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class Xwqyjfmx60ServiceImpl implements Xwqyjfmx60Service {

    @Resource
    private Xwqyjfmx60Mapper xwqyjfmx60Mapper;

    @Override
    public PageResult<XwqyjfyfmxDO> getXwqyjfmx60Page(Xwqyjfmx60PageReqVO pageReqVO) {
        fillDeptId(pageReqVO);
        pageReqVO.setOffset((pageReqVO.getPageNo() - 1) * pageReqVO.getPageSize());
        List<XwqyjfyfmxDO> list = xwqyjfmx60Mapper.selectXwqyjfmx60List(pageReqVO);
        long total = xwqyjfmx60Mapper.selectXwqyjfmx60Count(pageReqVO);
        return new PageResult<>(list, total);
    }

    @Override
    public List<XwqyjfyfmxDO> getXwqyjfmx60List(Xwqyjfmx60PageReqVO pageReqVO) {
        fillDeptId(pageReqVO);
        pageReqVO.setOffset(null);
        return xwqyjfmx60Mapper.selectXwqyjfmx60List(pageReqVO);
    }

    private void fillDeptId(Xwqyjfmx60PageReqVO req) {
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
