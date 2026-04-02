package cn.iocoder.yudao.module.lghjft.service.cxtj.fyfcqk;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fyfcqk.vo.FyfcqkPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fyfcqk.vo.FyfcqkSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.fyfcqk.FyfcqkDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.fyfcqk.FyfcqkMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.HKXX_NOT_EXISTS;

@Service
@Validated
public class FyfcqkServiceImpl implements FyfcqkService {

    @Resource
    private FyfcqkMapper fyfcqkMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createFyfcqk(FyfcqkSaveReqVO createReqVO) {
        FyfcqkDO obj = BeanUtils.toBean(createReqVO, FyfcqkDO.class);
        fyfcqkMapper.insert(obj);
        return obj.getDeptId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFyfcqk(FyfcqkSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getDeptId());
        FyfcqkDO updateObj = BeanUtils.toBean(updateReqVO, FyfcqkDO.class);
        fyfcqkMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFyfcqk(String id) {
        validateExists(id);
        fyfcqkMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFyfcqkListByIds(List<String> ids) {
        fyfcqkMapper.deleteByIds(ids);
    }

    private void validateExists(String id) {
        if (fyfcqkMapper.selectById(id) == null) {
            throw exception(HKXX_NOT_EXISTS);
        }
    }

    @Override
    public FyfcqkDO getFyfcqk(String id) {
        return fyfcqkMapper.selectById(id);
    }

    @Override
    public PageResult<FyfcqkDO> getFyfcqkPage(FyfcqkPageReqVO pageReqVO) {
        return fyfcqkMapper.selectPage(pageReqVO);
    }
}
