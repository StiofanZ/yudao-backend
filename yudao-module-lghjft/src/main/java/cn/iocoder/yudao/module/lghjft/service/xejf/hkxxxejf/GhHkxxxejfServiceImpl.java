package cn.iocoder.yudao.module.lghjft.service.xejf.hkxxxejf;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejf.vo.GhHkxxxejfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejf.vo.GhHkxxxejfSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.hkxxxejf.GhHkxxxejfDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.hkxxxejf.GhHkxxxejfMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.HKXXXEJF_NOT_EXISTS;

@Service
@Validated
public class GhHkxxxejfServiceImpl implements GhHkxxxejfService {

    @Resource
    private GhHkxxxejfMapper ghHkxxxejfMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long createGhHkxxxejf(GhHkxxxejfSaveReqVO createReqVO) {
        GhHkxxxejfDO entity = BeanUtils.toBean(createReqVO, GhHkxxxejfDO.class);
        ghHkxxxejfMapper.insert(entity);
        return entity.getHkxxId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateGhHkxxxejf(GhHkxxxejfSaveReqVO updateReqVO) {
        GhHkxxxejfDO updateObj = BeanUtils.toBean(updateReqVO, GhHkxxxejfDO.class);
        ghHkxxxejfMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteGhHkxxxejf(Long id) {
        validateExists(id);
        ghHkxxxejfMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteGhHkxxxejfListByIds(List<Long> ids) {
        ghHkxxxejfMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (ghHkxxxejfMapper.selectById(id) == null) {
            throw exception(HKXXXEJF_NOT_EXISTS);
        }
    }

    @Override
    public GhHkxxxejfDO getGhHkxxxejf(Long id) {
        return ghHkxxxejfMapper.selectById(id);
    }

    @Override
    public PageResult<GhHkxxxejfDO> getGhHkxxxejfPage(GhHkxxxejfPageReqVO pageReqVO) {
        return ghHkxxxejfMapper.selectPage(pageReqVO);
    }
}
