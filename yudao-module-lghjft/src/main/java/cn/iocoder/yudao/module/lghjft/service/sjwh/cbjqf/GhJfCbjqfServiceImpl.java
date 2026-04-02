package cn.iocoder.yudao.module.lghjft.service.sjwh.cbjqf;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjqf.vo.GhJfCbjqfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjqf.vo.GhJfCbjqfSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.cbjqf.GhJfCbjqfDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.cbjqf.GhJfCbjqfMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.JF_NOT_EXISTS;

@Service
@Validated
public class GhJfCbjqfServiceImpl implements GhJfCbjqfService {

    @Resource
    private GhJfCbjqfMapper ghJfCbjqfMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long createGhJfCbjqf(GhJfCbjqfSaveReqVO createReqVO) {
        GhJfCbjqfDO ghJfCbjqf = BeanUtils.toBean(createReqVO, GhJfCbjqfDO.class);
        ghJfCbjqfMapper.insert(ghJfCbjqf);
        return ghJfCbjqf.getGhjfId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateGhJfCbjqf(GhJfCbjqfSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getGhjfId());
        GhJfCbjqfDO updateObj = BeanUtils.toBean(updateReqVO, GhJfCbjqfDO.class);
        ghJfCbjqfMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteGhJfCbjqf(Long id) {
        validateExists(id);
        ghJfCbjqfMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteGhJfCbjqfListByIds(List<Long> ids) {
        ghJfCbjqfMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (ghJfCbjqfMapper.selectById(id) == null) {
            throw exception(JF_NOT_EXISTS);
        }
    }

    @Override
    public GhJfCbjqfDO getGhJfCbjqf(Long id) {
        return ghJfCbjqfMapper.selectById(id);
    }

    @Override
    public PageResult<GhJfCbjqfDO> getGhJfCbjqfPage(GhJfCbjqfPageReqVO pageReqVO) {
        return ghJfCbjqfMapper.selectPage(pageReqVO);
    }
}
