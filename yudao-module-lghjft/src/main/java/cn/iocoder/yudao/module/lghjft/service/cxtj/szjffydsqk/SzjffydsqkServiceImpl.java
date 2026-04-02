package cn.iocoder.yudao.module.lghjft.service.cxtj.szjffydsqk;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.szjffydsqk.vo.SzjffydsqkPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.szjffydsqk.vo.SzjffydsqkSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.szjffydsqk.SzjffydsqkDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.szjffydsqk.SzjffydsqkMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.HKXX_NOT_EXISTS;

@Service
@Validated
public class SzjffydsqkServiceImpl implements SzjffydsqkService {

    @Resource
    private SzjffydsqkMapper szjffydsqkMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createSzjffydsqk(SzjffydsqkSaveReqVO createReqVO) {
        SzjffydsqkDO obj = BeanUtils.toBean(createReqVO, SzjffydsqkDO.class);
        szjffydsqkMapper.insert(obj);
        return obj.getDwdm();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSzjffydsqk(SzjffydsqkSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getDwdm());
        SzjffydsqkDO updateObj = BeanUtils.toBean(updateReqVO, SzjffydsqkDO.class);
        szjffydsqkMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSzjffydsqk(String id) {
        validateExists(id);
        szjffydsqkMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSzjffydsqkListByIds(List<String> ids) {
        szjffydsqkMapper.deleteByIds(ids);
    }

    private void validateExists(String id) {
        if (szjffydsqkMapper.selectById(id) == null) {
            throw exception(HKXX_NOT_EXISTS);
        }
    }

    @Override
    public SzjffydsqkDO getSzjffydsqk(String id) {
        return szjffydsqkMapper.selectById(id);
    }

    @Override
    public PageResult<SzjffydsqkDO> getSzjffydsqkPage(SzjffydsqkPageReqVO pageReqVO) {
        return szjffydsqkMapper.selectPage(pageReqVO);
    }
}
