package cn.iocoder.yudao.module.lghjft.service.xwqy.xwqyjfyfmx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjfyfmx.vo.XwqyjfyfmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjfyfmx.vo.XwqyjfyfmxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xwqy.xwqyjfyfmx.XwqyjfyfmxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xwqy.xwqyjfyfmx.XwqyjfyfmxMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.GH_JF_NOT_EXISTS;

@Service
@Validated
public class XwqyjfyfmxServiceImpl implements XwqyjfyfmxService {

    @Resource
    private XwqyjfyfmxMapper xwqyjfyfmxMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long createXwqyjfyfmx(XwqyjfyfmxSaveReqVO createReqVO) {
        XwqyjfyfmxDO obj = BeanUtils.toBean(createReqVO, XwqyjfyfmxDO.class);
        xwqyjfyfmxMapper.insert(obj);
        return obj.getGhjfId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateXwqyjfyfmx(XwqyjfyfmxSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getGhjfId());
        XwqyjfyfmxDO updateObj = BeanUtils.toBean(updateReqVO, XwqyjfyfmxDO.class);
        xwqyjfyfmxMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteXwqyjfyfmx(Long id) {
        validateExists(id);
        xwqyjfyfmxMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteXwqyjfyfmxListByIds(List<Long> ids) {
        xwqyjfyfmxMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (xwqyjfyfmxMapper.selectById(id) == null) {
            throw exception(GH_JF_NOT_EXISTS);
        }
    }

    @Override
    public XwqyjfyfmxDO getXwqyjfyfmx(Long id) {
        return xwqyjfyfmxMapper.selectById(id);
    }

    @Override
    public PageResult<XwqyjfyfmxDO> getXwqyjfyfmxPage(XwqyjfyfmxPageReqVO pageReqVO) {
        fillDeptId(pageReqVO);
        pageReqVO.setOffset((pageReqVO.getPageNo() - 1) * pageReqVO.getPageSize());
        List<XwqyjfyfmxDO> list = xwqyjfyfmxMapper.selectXwqyjfyfmxList(pageReqVO);
        long total = xwqyjfyfmxMapper.selectXwqyjfyfmxCount(pageReqVO);
        return new PageResult<>(list, total);
    }

    private void fillDeptId(XwqyjfyfmxPageReqVO req) {
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
