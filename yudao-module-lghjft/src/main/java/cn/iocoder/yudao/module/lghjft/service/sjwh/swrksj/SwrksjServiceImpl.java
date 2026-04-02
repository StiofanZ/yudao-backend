package cn.iocoder.yudao.module.lghjft.service.sjwh.swrksj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.swrksj.vo.SwrksjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.swrksj.vo.SwrksjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.swrksj.SwrksjDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.swrksj.SwrksjMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.JF_NOT_EXISTS;

@Service
@Validated
public class SwrksjServiceImpl implements SwrksjService {

    @Resource
    private SwrksjMapper swrksjMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long createSwrksj(SwrksjSaveReqVO createReqVO) {
        SwrksjDO swrksj = BeanUtils.toBean(createReqVO, SwrksjDO.class);
        swrksjMapper.insert(swrksj);
        return swrksj.getGhjfId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateSwrksj(SwrksjSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getGhjfId());
        SwrksjDO updateObj = BeanUtils.toBean(updateReqVO, SwrksjDO.class);
        swrksjMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteSwrksj(Long id) {
        validateExists(id);
        swrksjMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteSwrksjListByIds(List<Long> ids) {
        swrksjMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (swrksjMapper.selectById(id) == null) {
            throw exception(JF_NOT_EXISTS);
        }
    }

    @Override
    public SwrksjDO getSwrksj(Long id) {
        return swrksjMapper.selectById(id);
    }

    @Override
    public PageResult<SwrksjDO> getSwrksjPage(SwrksjPageReqVO pageReqVO) {
        return swrksjMapper.selectPage(pageReqVO);
    }
}
