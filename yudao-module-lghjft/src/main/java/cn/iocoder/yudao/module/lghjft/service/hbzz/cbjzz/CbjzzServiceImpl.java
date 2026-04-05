package cn.iocoder.yudao.module.lghjft.service.hbzz.cbjzz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.cbjzz.vo.CbjzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.cbjzz.vo.CbjzzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.cbjzz.CbjzzDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.cbjzz.CbjzzMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.HKXX_NOT_EXISTS;

@Service
@Validated
public class CbjzzServiceImpl implements CbjzzService {

    @Resource
    private CbjzzMapper cbjzzMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long createCbjzz(CbjzzSaveReqVO createReqVO) {
        CbjzzDO cbjzz = BeanUtils.toBean(createReqVO, CbjzzDO.class);
        cbjzzMapper.insert(cbjzz);
        return cbjzz.getHkxxId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCbjzz(CbjzzSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getHkxxId());
        CbjzzDO updateObj = BeanUtils.toBean(updateReqVO, CbjzzDO.class);
        cbjzzMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteCbjzz(Long id) {
        validateExists(id);
        cbjzzMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteCbjzzListByIds(List<Long> ids) {
        cbjzzMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (cbjzzMapper.selectById(id) == null) {
            throw exception(HKXX_NOT_EXISTS);
        }
    }

    @Override
    public CbjzzDO getCbjzz(Long id) {
        return cbjzzMapper.selectById(id);
    }

    @Override
    public PageResult<CbjzzDO> getCbjzzPage(CbjzzPageReqVO pageReqVO) {
        return cbjzzMapper.selectPage(pageReqVO);
    }
}
