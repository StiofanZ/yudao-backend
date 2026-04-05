package cn.iocoder.yudao.module.lghjft.service.hbzz.sxfzz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.sxfzz.vo.SxfzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.sxfzz.vo.SxfzzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.sxfzz.SxfzzDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.sxfzz.SxfzzMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.HKXX_NOT_EXISTS;

@Service
@Validated
public class SxfzzServiceImpl implements SxfzzService {

    @Resource
    private SxfzzMapper sxfzzMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long createSxfzz(SxfzzSaveReqVO createReqVO) {
        SxfzzDO sxfzz = BeanUtils.toBean(createReqVO, SxfzzDO.class);
        sxfzzMapper.insert(sxfzz);
        return sxfzz.getHkxxId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateSxfzz(SxfzzSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getHkxxId());
        SxfzzDO updateObj = BeanUtils.toBean(updateReqVO, SxfzzDO.class);
        sxfzzMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteSxfzz(Long id) {
        validateExists(id);
        sxfzzMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteSxfzzListByIds(List<Long> ids) {
        sxfzzMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (sxfzzMapper.selectById(id) == null) {
            throw exception(HKXX_NOT_EXISTS);
        }
    }

    @Override
    public SxfzzDO getSxfzz(Long id) {
        return sxfzzMapper.selectById(id);
    }

    @Override
    public PageResult<SxfzzDO> getSxfzzPage(SxfzzPageReqVO pageReqVO) {
        return sxfzzMapper.selectPage(pageReqVO);
    }
}
