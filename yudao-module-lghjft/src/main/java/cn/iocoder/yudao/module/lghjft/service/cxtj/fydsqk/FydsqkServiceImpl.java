package cn.iocoder.yudao.module.lghjft.service.cxtj.fydsqk;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fydsqk.vo.FydsqkPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fydsqk.vo.FydsqkSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.fydsqk.FydsqkDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.fydsqk.FydsqkMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.HKXX_NOT_EXISTS;

@Service
@Validated
public class FydsqkServiceImpl implements FydsqkService {

    @Resource
    private FydsqkMapper fydsqkMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createFydsqk(FydsqkSaveReqVO createReqVO) {
        FydsqkDO obj = BeanUtils.toBean(createReqVO, FydsqkDO.class);
        fydsqkMapper.insert(obj);
        return obj.getDeptId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFydsqk(FydsqkSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getDeptId());
        FydsqkDO updateObj = BeanUtils.toBean(updateReqVO, FydsqkDO.class);
        fydsqkMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFydsqk(String id) {
        validateExists(id);
        fydsqkMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFydsqkListByIds(List<String> ids) {
        fydsqkMapper.deleteByIds(ids);
    }

    private void validateExists(String id) {
        if (fydsqkMapper.selectById(id) == null) {
            throw exception(HKXX_NOT_EXISTS);
        }
    }

    @Override
    public FydsqkDO getFydsqk(String id) {
        return fydsqkMapper.selectById(id);
    }

    @Override
    public PageResult<FydsqkDO> getFydsqkPage(FydsqkPageReqVO pageReqVO) {
        return fydsqkMapper.selectPage(pageReqVO);
    }
}
