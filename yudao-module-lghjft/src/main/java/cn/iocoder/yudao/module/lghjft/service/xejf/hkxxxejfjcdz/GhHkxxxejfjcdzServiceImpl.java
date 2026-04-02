package cn.iocoder.yudao.module.lghjft.service.xejf.hkxxxejfjcdz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfjcdz.vo.GhHkxxxejfjcdzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfjcdz.vo.GhHkxxxejfjcdzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.hkxxxejfjcdz.GhHkxxxejfjcdzDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.hkxxxejfjcdz.GhHkxxxejfjcdzMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.HKXXXEJFJCDZ_NOT_EXISTS;

@Service
@Validated
public class GhHkxxxejfjcdzServiceImpl implements GhHkxxxejfjcdzService {

    @Resource
    private GhHkxxxejfjcdzMapper ghHkxxxejfjcdzMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long createGhHkxxxejfjcdz(GhHkxxxejfjcdzSaveReqVO createReqVO) {
        GhHkxxxejfjcdzDO entity = BeanUtils.toBean(createReqVO, GhHkxxxejfjcdzDO.class);
        ghHkxxxejfjcdzMapper.insert(entity);
        return entity.getHkxxId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateGhHkxxxejfjcdz(GhHkxxxejfjcdzSaveReqVO updateReqVO) {
        GhHkxxxejfjcdzDO updateObj = BeanUtils.toBean(updateReqVO, GhHkxxxejfjcdzDO.class);
        ghHkxxxejfjcdzMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteGhHkxxxejfjcdz(Long id) {
        validateExists(id);
        ghHkxxxejfjcdzMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteGhHkxxxejfjcdzListByIds(List<Long> ids) {
        ghHkxxxejfjcdzMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (ghHkxxxejfjcdzMapper.selectById(id) == null) {
            throw exception(HKXXXEJFJCDZ_NOT_EXISTS);
        }
    }

    @Override
    public GhHkxxxejfjcdzDO getGhHkxxxejfjcdz(Long id) {
        return ghHkxxxejfjcdzMapper.selectById(id);
    }

    @Override
    public PageResult<GhHkxxxejfjcdzDO> getGhHkxxxejfjcdzPage(GhHkxxxejfjcdzPageReqVO pageReqVO) {
        return ghHkxxxejfjcdzMapper.selectPage(pageReqVO);
    }
}
