package cn.iocoder.yudao.module.lghjft.service.ghcbj.cbjqltz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjqltz.vo.CbjqltzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjqltz.vo.CbjqltzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.cbjqltz.CbjqltzDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.ghcbj.cbjqltz.CbjqltzMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.HKXX_NOT_EXISTS;

@Service
@Validated
public class CbjqltzServiceImpl implements CbjqltzService {

    @Resource
    private CbjqltzMapper cbjqltzMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCbjqltz(CbjqltzSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getDjxh());
        CbjqltzDO updateObj = BeanUtils.toBean(updateReqVO, CbjqltzDO.class);
        cbjqltzMapper.updateById(updateObj);
    }

    private void validateExists(String id) {
        if (cbjqltzMapper.selectById(id) == null) {
            throw exception(HKXX_NOT_EXISTS);
        }
    }

    @Override
    public CbjqltzDO getCbjqltz(String id) {
        return cbjqltzMapper.selectById(id);
    }

    @Override
    public PageResult<CbjqltzDO> getCbjqltzPage(CbjqltzPageReqVO pageReqVO) {
        return cbjqltzMapper.selectPage(pageReqVO);
    }
}
