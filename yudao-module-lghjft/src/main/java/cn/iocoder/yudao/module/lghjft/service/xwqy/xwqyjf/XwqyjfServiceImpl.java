package cn.iocoder.yudao.module.lghjft.service.xwqy.xwqyjf;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjf.vo.XwqyjfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjf.vo.XwqyjfSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjfmx95.vo.Xwqyjfmx95PageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xwqy.xwqyjf.XwqyjfDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xwqy.xwqyjf.XwqyjfMapper;
import cn.iocoder.yudao.module.lghjft.service.xwqy.xwqyjfmx95.Xwqyjfmx95Service;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.HKXX_NOT_EXISTS;

@Service
@Validated
public class XwqyjfServiceImpl implements XwqyjfService {

    @Resource
    private XwqyjfMapper xwqyjfMapper;

    @Resource
    private Xwqyjfmx95Service xwqyjfmx95Service;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long createXwqyjf(XwqyjfSaveReqVO createReqVO) {
        XwqyjfDO obj = BeanUtils.toBean(createReqVO, XwqyjfDO.class);
        xwqyjfMapper.insert(obj);
        return obj.getGhjfId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateXwqyjf(XwqyjfSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getGhjfId());
        XwqyjfDO updateObj = BeanUtils.toBean(updateReqVO, XwqyjfDO.class);
        xwqyjfMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteXwqyjf(Long id) {
        validateExists(id);
        xwqyjfMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteXwqyjfListByIds(List<Long> ids) {
        xwqyjfMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (xwqyjfMapper.selectById(id) == null) {
            throw exception(HKXX_NOT_EXISTS);
        }
    }

    @Override
    public XwqyjfDO getXwqyjf(Long id) {
        return xwqyjfMapper.selectById(id);
    }

    @Override
    public PageResult<XwqyjfDO> getXwqyjfPage(XwqyjfPageReqVO pageReqVO) {
        return xwqyjfmx95Service.getXwqyjfmx95Page(BeanUtils.toBean(pageReqVO, Xwqyjfmx95PageReqVO.class));
    }

    @Override
    public PageResult<XwqyjfDO> getXwqyjfPageYf(XwqyjfPageReqVO pageReqVO) {
        fillDeptId(pageReqVO);
        pageReqVO.setOffset((pageReqVO.getPageNo() - 1) * pageReqVO.getPageSize());
        List<XwqyjfDO> list = xwqyjfMapper.selectXwqyjfListyf(pageReqVO);
        long total = xwqyjfMapper.selectXwqyjfCountyf(pageReqVO);
        return new PageResult<>(list, total);
    }

    @Override
    public List<XwqyjfDO> getXwqyjfList(XwqyjfPageReqVO pageReqVO) {
        return xwqyjfmx95Service.getXwqyjfmx95List(BeanUtils.toBean(pageReqVO, Xwqyjfmx95PageReqVO.class));
    }

    private void fillDeptId(XwqyjfPageReqVO req) {
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
