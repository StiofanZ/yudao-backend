package cn.iocoder.yudao.module.lghjft.service.qx.sfxx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.sfxx.GhQxSfxxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.qx.sfxx.GhQxSfxxMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserDeptId;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.SFXX_NOT_EXISTS;

@Service
@Validated
public class GhQxSfxxServiceImpl implements GhQxSfxxService {

    @Resource
    private GhQxSfxxMapper ghQxSfxxMapper;

    @Override
    public Long createSfxx(SfxxSaveReqVO createReqVO) {
        GhQxSfxxDO sfxx = BeanUtils.toBean(createReqVO, GhQxSfxxDO.class);
        if (sfxx.getDeptId() == null) {
            sfxx.setDeptId(getLoginUserDeptId());
        }
        if (sfxx.getStatus() == null) {
            sfxx.setStatus(0);
        }
        ghQxSfxxMapper.insert(sfxx);
        return sfxx.getId();
    }

    @Override
    public void updateSfxx(SfxxSaveReqVO updateReqVO) {
        validateSfxxExists(updateReqVO.getId());
        GhQxSfxxDO updateObj = BeanUtils.toBean(updateReqVO, GhQxSfxxDO.class);
        ghQxSfxxMapper.updateById(updateObj);
    }

    @Override
    public void deleteSfxx(Long id) {
        validateSfxxExists(id);
        ghQxSfxxMapper.deleteById(id);
    }

    @Override
    public void deleteSfxxListByIds(List<Long> ids) {
        ghQxSfxxMapper.deleteByIds(ids);
    }

    @Override
    public GhQxSfxxDO getSfxx(Long id) {
        return ghQxSfxxMapper.selectById(id);
    }

    @Override
    public PageResult<GhQxSfxxDO> getSfxxPage(SfxxPageReqVO pageReqVO) {
        return ghQxSfxxMapper.selectPage(pageReqVO);
    }

    @Override
    public void auditSfxx(Long id, Integer status) {
        validateSfxxExists(id);
        GhQxSfxxDO updateObj = new GhQxSfxxDO();
        updateObj.setId(id);
        updateObj.setStatus(status);
        ghQxSfxxMapper.updateById(updateObj);
    }

    private void validateSfxxExists(Long id) {
        if (id == null || ghQxSfxxMapper.selectById(id) == null) {
            throw exception(SFXX_NOT_EXISTS);
        }
    }

}
