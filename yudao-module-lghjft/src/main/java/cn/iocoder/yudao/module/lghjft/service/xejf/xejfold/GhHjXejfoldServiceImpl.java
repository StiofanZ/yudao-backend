package cn.iocoder.yudao.module.lghjft.service.xejf.xejfold;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfold.vo.GhHjXejfoldPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfold.vo.GhHjXejfoldSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejfold.GhHjXejfoldDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xejfold.GhHjXejfoldMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.XEJFOLD_NOT_EXISTS;

@Service
@Validated
public class GhHjXejfoldServiceImpl implements GhHjXejfoldService {

    @Resource
    private GhHjXejfoldMapper ghHjXejfoldMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String createGhHjXejfold(GhHjXejfoldSaveReqVO createReqVO) {
        GhHjXejfoldDO entity = BeanUtils.toBean(createReqVO, GhHjXejfoldDO.class);
        ghHjXejfoldMapper.insert(entity);
        return entity.getDeptId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateGhHjXejfold(GhHjXejfoldSaveReqVO updateReqVO) {
        GhHjXejfoldDO updateObj = BeanUtils.toBean(updateReqVO, GhHjXejfoldDO.class);
        ghHjXejfoldMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteGhHjXejfold(String id) {
        validateExists(id);
        ghHjXejfoldMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteGhHjXejfoldListByIds(List<String> ids) {
        ghHjXejfoldMapper.deleteByIds(ids);
    }

    private void validateExists(String id) {
        if (ghHjXejfoldMapper.selectById(id) == null) {
            throw exception(XEJFOLD_NOT_EXISTS);
        }
    }

    @Override
    public GhHjXejfoldDO getGhHjXejfold(String id) {
        return ghHjXejfoldMapper.selectById(id);
    }

    @Override
    public PageResult<GhHjXejfoldDO> getGhHjXejfoldPage(GhHjXejfoldPageReqVO pageReqVO) {
        return ghHjXejfoldMapper.selectPage(pageReqVO);
    }
}
