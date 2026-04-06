package cn.iocoder.yudao.module.lghjft.service.qx.sfxx;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.sfxx.SystemUserSfxxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.qx.sfxx.SystemUserSfxxMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserDeptId;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.*;

@Service
@Validated
public class SystemUserSfxxServiceImpl implements SystemUserSfxxService {

    @Resource
    private SystemUserSfxxMapper systemUserSfxxMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long createSfxx(SfxxSaveReqVO createReqVO) {
        SystemUserSfxxDO sfxx = BeanUtils.toBean(createReqVO, SystemUserSfxxDO.class);
        if (sfxx.getDeptId() == null) {
            sfxx.setDeptId(getLoginUserDeptId());
        }
        if (sfxx.getStatus() == null) {
            sfxx.setStatus(0);
        }
        systemUserSfxxMapper.insert(sfxx);
        return sfxx.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateSfxx(SfxxSaveReqVO updateReqVO) {
        validateSfxxExists(updateReqVO.getId());
        SystemUserSfxxDO updateObj = BeanUtils.toBean(updateReqVO, SystemUserSfxxDO.class);
        systemUserSfxxMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteSfxx(Long id) {
        validateSfxxExists(id);
        systemUserSfxxMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteSfxxListByIds(List<Long> ids) {
        systemUserSfxxMapper.deleteByIds(ids);
    }

    @Override
    public SystemUserSfxxDO getSfxx(Long id) {
        return systemUserSfxxMapper.selectById(id);
    }

    @Override
    public SystemUserSfxxDO getSfxx(Long dlzhId, String djxh) {
        return systemUserSfxxMapper.selectOne(SystemUserSfxxDO::getDlzhId, dlzhId, SystemUserSfxxDO::getDjxh, djxh);
    }

    @Override
    public PageResult<SystemUserSfxxDO> getSfxxPage(SfxxPageReqVO pageReqVO) {
        return systemUserSfxxMapper.selectPage(pageReqVO);
    }

    @Override
    public List<SystemUserSfxxDO> getSfxxList(SfxxReqVO sfxxReqVO) {
        return systemUserSfxxMapper.selectList(sfxxReqVO);
    }

    @Override
    public void auditSfxx(Long id, Integer status, String jjyy) {
        validateSfxxExists(id);
        if (Integer.valueOf(2).equals(status) && StrUtil.isBlank(jjyy)) {
            throw exception(SFXX_REJECT_REASON_REQUIRED);
        }
        SystemUserSfxxDO updateObj = new SystemUserSfxxDO();
        updateObj.setId(id);
        updateObj.setStatus(status);
        updateObj.setJjyy(Integer.valueOf(2).equals(status) ? jjyy : null);
        systemUserSfxxMapper.updateById(updateObj);
    }

    @Override
    public void auditSfxxWithOwnerCheck(Long id, Integer status, String jjyy) {
        SystemUserSfxxDO sfxx = validateSfxxExistsAndReturn(id);
        validateSfxxOwner(sfxx);
        auditSfxx(id, status, jjyy);
    }

    @Override
    public void unbindSfxx(Long id, String jbyy) {
        validateSfxxExists(id);
        SystemUserSfxxDO updateObj = new SystemUserSfxxDO();
        updateObj.setId(id);
        updateObj.setJbyy(jbyy);
        systemUserSfxxMapper.updateById(updateObj);
        systemUserSfxxMapper.deleteById(id);
    }

    @Override
    public void unbindSfxxWithOwnerCheck(Long id, String jbyy) {
        SystemUserSfxxDO sfxx = validateSfxxExistsAndReturn(id);
        validateSfxxOwner(sfxx);
        unbindSfxx(id, jbyy);
    }

    private void validateSfxxExists(Long id) {
        if (id == null || systemUserSfxxMapper.selectById(id) == null) {
            throw exception(SFXX_NOT_EXISTS);
        }
    }

    private SystemUserSfxxDO validateSfxxExistsAndReturn(Long id) {
        SystemUserSfxxDO sfxx = systemUserSfxxMapper.selectById(id);
        if (id == null || sfxx == null) {
            throw exception(SFXX_NOT_EXISTS);
        }
        return sfxx;
    }

    private void validateSfxxOwner(SystemUserSfxxDO sfxx) {
        Long loginUserId = getLoginUserId();
        if (!Objects.equals(sfxx.getDlzhId(), loginUserId)) {
            throw exception(OPERATION_NOT_PERMITTED);
        }
    }
}
