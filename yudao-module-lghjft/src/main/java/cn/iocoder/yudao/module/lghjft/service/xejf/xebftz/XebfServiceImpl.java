package cn.iocoder.yudao.module.lghjft.service.xejf.xebftz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.GhhkxxxejfszResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.XebfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.XebfResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.XebfSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xebftz.XebfMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.XEBF_NOT_EXISTS;

@Service
@Validated
public class XebfServiceImpl implements XebfService {

    @Resource
    private XebfMapper xebfMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createXebf(XebfSaveReqVO createReqVO) {
        validateXebfExists(createReqVO.getGhjfId());
        xebfMapper.upsertConfirmByGhjfId(createReqVO, getLoginUserNickname());
        return createReqVO.getGhjfId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateXebf(XebfSaveReqVO updateReqVO) {
        validateXebfExists(updateReqVO.getGhjfId());
        xebfMapper.touchMainRow(updateReqVO.getGhjfId());
        xebfMapper.upsertConfirmByGhjfId(updateReqVO, getLoginUserNickname());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteXebf(Long id) {
        validateXebfExists(id);
        xebfMapper.deleteConfirmByGhjfId(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteXebfList(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        xebfMapper.deleteConfirmByGhjfIds(ids);
    }

    @Override
    public XebfResVO getXebf(Long id) {
        return xebfMapper.selectJoinedByGhjfId(id);
    }

    @Override
    public PageResult<XebfResVO> getXebfPage(XebfPageReqVO pageReqVO) {
        if (pageReqVO.getDeptId() == null || pageReqVO.getDeptId().isEmpty()) {
            Long loginDeptId = SecurityFrameworkUtils.getLoginUserDeptId();
            if (loginDeptId != null) {
                pageReqVO.setDeptId(loginDeptId.toString());
            }
        }
        if ("100000".equals(pageReqVO.getDeptId())) {
            pageReqVO.setDeptId(null);
        }
        return xebfMapper.selectPage(pageReqVO);
    }

    @Override
    public List<GhhkxxxejfszResVO> getGhHkxxxejfszList(String jfqj) {
        return xebfMapper.selectGhHkxxxejfszList(jfqj);
    }

    private void validateXebfExists(Long id) {
        if (id == null || xebfMapper.selectById(id) == null) {
            throw exception(XEBF_NOT_EXISTS);
        }
    }

    private String getLoginUserNickname() {
        return SecurityFrameworkUtils.getLoginUserNickname();
    }
}
