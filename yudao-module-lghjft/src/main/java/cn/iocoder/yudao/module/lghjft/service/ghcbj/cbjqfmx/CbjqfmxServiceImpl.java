package cn.iocoder.yudao.module.lghjft.service.ghcbj.cbjqfmx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjqfmx.vo.CbjqfmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjqfmx.vo.CbjqfmxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.cbjqfmx.CbjqfmxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.ghcbj.cbjqfmx.CbjqfmxMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.JF_NOT_EXISTS;

@Service
@Validated
public class CbjqfmxServiceImpl implements CbjqfmxService {

    @Resource
    private CbjqfmxMapper cbjqfmxMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long createCbjqfmx(CbjqfmxSaveReqVO createReqVO) {
        CbjqfmxDO cbjqfmx = BeanUtils.toBean(createReqVO, CbjqfmxDO.class);
        cbjqfmxMapper.insert(cbjqfmx);
        return cbjqfmx.getGhjfId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCbjqfmx(CbjqfmxSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getGhjfId());
        CbjqfmxDO updateObj = BeanUtils.toBean(updateReqVO, CbjqfmxDO.class);
        cbjqfmxMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteCbjqfmx(Long id) {
        validateExists(id);
        cbjqfmxMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteCbjqfmxListByIds(List<Long> ids) {
        cbjqfmxMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (cbjqfmxMapper.selectById(id) == null) {
            throw exception(JF_NOT_EXISTS);
        }
    }

    @Override
    public CbjqfmxDO getCbjqfmx(Long id) {
        return cbjqfmxMapper.selectById(id);
    }

    @Override
    public PageResult<CbjqfmxDO> getCbjqfmxPage(CbjqfmxPageReqVO pageReqVO) {
        return cbjqfmxMapper.selectPage(pageReqVO);
    }
}
