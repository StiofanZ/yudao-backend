package cn.iocoder.yudao.module.lghjft.service.cxtj.yjhxx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.yjhxx.vo.GhYjhxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.yjhxx.vo.GhYjhxxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.yjhxx.GhYjhxxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.yjhxx.GhYjhxxMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.YJHXX_NOT_EXISTS;

@Service
@Validated
public class GhYjhxxServiceImpl implements GhYjhxxService {

    @Resource
    private GhYjhxxMapper ghYjhxxMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createGhYjhxx(GhYjhxxSaveReqVO createReqVO) {
        GhYjhxxDO obj = BeanUtils.toBean(createReqVO, GhYjhxxDO.class);
        obj.setCreateTime(LocalDateTime.now());
        ghYjhxxMapper.insert(obj);
        return obj.getJhxxId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateGhYjhxx(GhYjhxxSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getJhxxId());
        GhYjhxxDO updateObj = BeanUtils.toBean(updateReqVO, GhYjhxxDO.class);
        updateObj.setUpdateTime(LocalDateTime.now());
        ghYjhxxMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteGhYjhxx(Long id) {
        validateExists(id);
        ghYjhxxMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteGhYjhxxListByIds(List<Long> ids) {
        ghYjhxxMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (ghYjhxxMapper.selectById(id) == null) {
            throw exception(YJHXX_NOT_EXISTS);
        }
    }

    @Override
    public GhYjhxxDO getGhYjhxx(Long id) {
        return ghYjhxxMapper.selectById(id);
    }

    @Override
    public PageResult<GhYjhxxDO> getGhYjhxxPage(GhYjhxxPageReqVO pageReqVO) {
        return ghYjhxxMapper.selectPage(pageReqVO);
    }
}
