package cn.iocoder.yudao.module.lghjft.service.sjwh.dmwh.fpbl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.fpbl.vo.CsFpblPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.fpbl.vo.CsFpblSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.dmwh.fpbl.CsFpblDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.dmwh.fpbl.CsFpblMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.FPBL_NOT_EXISTS;

@Service
@Validated
public class CsFpblServiceImpl implements CsFpblService {

    @Resource
    private CsFpblMapper csFpblMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createCsFpbl(CsFpblSaveReqVO createReqVO) {
        CsFpblDO obj = BeanUtils.toBean(createReqVO, CsFpblDO.class);
        csFpblMapper.insert(obj);
        return obj.getBlId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCsFpbl(CsFpblSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getBlId());
        CsFpblDO updateObj = BeanUtils.toBean(updateReqVO, CsFpblDO.class);
        csFpblMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCsFpbl(Long id) {
        validateExists(id);
        csFpblMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCsFpblListByIds(List<Long> ids) {
        csFpblMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (csFpblMapper.selectById(id) == null) {
            throw exception(FPBL_NOT_EXISTS);
        }
    }

    @Override
    public CsFpblDO getCsFpbl(Long id) {
        return csFpblMapper.selectById(id);
    }

    @Override
    public PageResult<CsFpblDO> getCsFpblPage(CsFpblPageReqVO pageReqVO) {
        return csFpblMapper.selectPage(pageReqVO);
    }
}
