package cn.iocoder.yudao.module.lghjft.service.xejf.hkxxxejfcbj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfcbj.vo.GhHkxxxejfcbjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfcbj.vo.GhHkxxxejfcbjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.hkxxxejfcbj.GhHkxxxejfcbjDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.hkxxxejfcbj.GhHkxxxejfcbjMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.HKXXXEJFCBJ_NOT_EXISTS;

@Service
@Validated
public class GhHkxxxejfcbjServiceImpl implements GhHkxxxejfcbjService {

    @Resource
    private GhHkxxxejfcbjMapper ghHkxxxejfcbjMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long createGhHkxxxejfcbj(GhHkxxxejfcbjSaveReqVO createReqVO) {
        GhHkxxxejfcbjDO entity = BeanUtils.toBean(createReqVO, GhHkxxxejfcbjDO.class);
        ghHkxxxejfcbjMapper.insert(entity);
        return entity.getHkxxId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateGhHkxxxejfcbj(GhHkxxxejfcbjSaveReqVO updateReqVO) {
        GhHkxxxejfcbjDO updateObj = BeanUtils.toBean(updateReqVO, GhHkxxxejfcbjDO.class);
        ghHkxxxejfcbjMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteGhHkxxxejfcbj(Long id) {
        validateExists(id);
        ghHkxxxejfcbjMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteGhHkxxxejfcbjListByIds(List<Long> ids) {
        ghHkxxxejfcbjMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (ghHkxxxejfcbjMapper.selectById(id) == null) {
            throw exception(HKXXXEJFCBJ_NOT_EXISTS);
        }
    }

    @Override
    public GhHkxxxejfcbjDO getGhHkxxxejfcbj(Long id) {
        return ghHkxxxejfcbjMapper.selectById(id);
    }

    @Override
    public PageResult<GhHkxxxejfcbjDO> getGhHkxxxejfcbjPage(GhHkxxxejfcbjPageReqVO pageReqVO) {
        return ghHkxxxejfcbjMapper.selectPage(pageReqVO);
    }
}
