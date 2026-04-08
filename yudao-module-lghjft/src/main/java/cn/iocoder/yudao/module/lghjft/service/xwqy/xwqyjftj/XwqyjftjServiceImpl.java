package cn.iocoder.yudao.module.lghjft.service.xwqy.xwqyjftj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
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
    public PageResult<XwqyjftjfhAggVO> getXwqyjftjfhPage(XwqyjftjPageReqVO req) {
        fillDeptId(req);
        req.setOffset((req.getPageNo() - 1) * req.getPageSize());
        List<XwqyjftjfhAggVO> list = xwqyjftjMapper.selectXwqyjftjfhList(req);
        long total = xwqyjftjMapper.selectXwqyjftjfhCount(req);
        return new PageResult<>(list, total);
    }

    @Override
    public List<XwqyjftjfhAggVO> getXwqyjftjfhList(XwqyjftjPageReqVO req) {
        fillDeptId(req);
        req.setOffset(null);
        return xwqyjftjMapper.selectXwqyjftjfhList(req);
    }

    @Override
    public PageResult<XwqyjftjmxResVO> getXwqyjftjmxPage(XwqyjftjPageReqVO req) {
        fillDeptId(req);
        req.setOffset((req.getPageNo() - 1) * req.getPageSize());
        List<XwqyjftjmxResVO> list = xwqyjftjMapper.selectXwqyjftjmxList(req);
        long total = xwqyjftjMapper.selectXwqyjftjmxCount(req);
        return new PageResult<>(list, total);
    }

    @Override
    public List<XwqyjftjmxResVO> getXwqyjftjmxList(XwqyjftjPageReqVO req) {
        fillDeptId(req);
        req.setOffset(null);
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
