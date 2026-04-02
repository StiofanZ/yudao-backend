package cn.iocoder.yudao.module.lghjft.service.cxtj.hbsbjl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjl.vo.HbsbjlPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjl.vo.HbsbjlSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.hbsbjl.HbsbjlDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.hbsbjl.HbsbjlMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.HKXX_NOT_EXISTS;

@Service
@Validated
public class HbsbjlServiceImpl implements HbsbjlService {

    @Resource
    private HbsbjlMapper hbsbjlMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createHbsbjl(HbsbjlSaveReqVO createReqVO) {
        HbsbjlDO obj = BeanUtils.toBean(createReqVO, HbsbjlDO.class);
        hbsbjlMapper.insert(obj);
        return obj.getHkxxId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateHbsbjl(HbsbjlSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getHkxxId());
        HbsbjlDO updateObj = BeanUtils.toBean(updateReqVO, HbsbjlDO.class);
        hbsbjlMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteHbsbjl(Long id) {
        validateExists(id);
        hbsbjlMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteHbsbjlListByIds(List<Long> ids) {
        hbsbjlMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (hbsbjlMapper.selectById(id) == null) {
            throw exception(HKXX_NOT_EXISTS);
        }
    }

    @Override
    public HbsbjlDO getHbsbjl(Long id) {
        return hbsbjlMapper.selectById(id);
    }

    @Override
    public PageResult<HbsbjlDO> getHbsbjlPage(HbsbjlPageReqVO pageReqVO) {
        return hbsbjlMapper.selectPage(pageReqVO);
    }
}
