package cn.iocoder.yudao.module.lghjft.service.cxtj.hbsbjlyxg;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjlyxg.vo.HbsbjlyxgPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjlyxg.vo.HbsbjlyxgSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.hbsbjlyxg.HbsbjlyxgDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.hbsbjlyxg.HbsbjlyxgMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.HKXX_NOT_EXISTS;

@Service
@Validated
public class HbsbjlyxgServiceImpl implements HbsbjlyxgService {

    @Resource
    private HbsbjlyxgMapper hbsbjlyxgMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createHbsbjlyxg(HbsbjlyxgSaveReqVO createReqVO) {
        HbsbjlyxgDO obj = BeanUtils.toBean(createReqVO, HbsbjlyxgDO.class);
        hbsbjlyxgMapper.insert(obj);
        return obj.getHkxxId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateHbsbjlyxg(HbsbjlyxgSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getHkxxId());
        HbsbjlyxgDO updateObj = BeanUtils.toBean(updateReqVO, HbsbjlyxgDO.class);
        hbsbjlyxgMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteHbsbjlyxg(Long id) {
        validateExists(id);
        hbsbjlyxgMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteHbsbjlyxgListByIds(List<Long> ids) {
        hbsbjlyxgMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (hbsbjlyxgMapper.selectById(id) == null) {
            throw exception(HKXX_NOT_EXISTS);
        }
    }

    @Override
    public HbsbjlyxgDO getHbsbjlyxg(Long id) {
        return hbsbjlyxgMapper.selectById(id);
    }

    @Override
    public PageResult<HbsbjlyxgDO> getHbsbjlyxgPage(HbsbjlyxgPageReqVO pageReqVO) {
        return hbsbjlyxgMapper.selectPage(pageReqVO);
    }
}
