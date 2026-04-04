package cn.iocoder.yudao.module.lghjft.service.xwqy.xwqyjftj;

import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjftj.vo.XwqyjftjAggVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjftj.vo.XwqyjftjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjftj.vo.XwqyjftjfhAggVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjftj.vo.XwqyjftjmxResVO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xwqy.xwqyjftj.XwqyjftjMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class XwqyjftjServiceImpl implements XwqyjftjService {

    @Resource
    private XwqyjftjMapper xwqyjftjMapper;

    @Override
    public List<XwqyjftjAggVO> getXwqyjftjList(XwqyjftjPageReqVO req) {
        fillDeptId(req);
        return xwqyjftjMapper.selectXwqyjftjList(req);
    }

    @Override
    public List<XwqyjftjfhAggVO> getXwqyjftjfhList(XwqyjftjPageReqVO req) {
        fillDeptId(req);
        return xwqyjftjMapper.selectXwqyjftjfhList(req);
    }

    @Override
    public List<XwqyjftjmxResVO> getXwqyjftjmxList(XwqyjftjPageReqVO req) {
        // v1 listmx: deptId直接等于(不展开子部门)
        fillDeptId(req);
        return xwqyjftjMapper.selectXwqyjftjmxList(req);
    }

    private void fillDeptId(XwqyjftjPageReqVO req) {
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
