package cn.iocoder.yudao.module.lghjft.service.sjwh.dmwh.skgk;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.skgk.vo.SkgkPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.skgk.vo.SkgkSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.dmwh.skgk.SkgkDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.dmwh.skgk.SkgkMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.SKGK_NOT_EXISTS;

/**
 * 收款国库 Service 实现类
 *
 * @author 李文军
 */
@Service
@Validated
public class SkgkServiceImpl implements SkgkService {

    @Resource
    private SkgkMapper skgkMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long createSkgk(SkgkSaveReqVO createReqVO) {
        SkgkDO skgk = BeanUtils.toBean(createReqVO, SkgkDO.class);
        skgkMapper.insert(skgk);
        return skgk.getGkId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateSkgk(SkgkSaveReqVO updateReqVO) {
        validateSkgkExists(updateReqVO.getGkId());
        SkgkDO updateObj = BeanUtils.toBean(updateReqVO, SkgkDO.class);
        skgkMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteSkgk(Long id) {
        validateSkgkExists(id);
        skgkMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteSkgkListByIds(List<Long> ids) {
        skgkMapper.deleteByIds(ids);
    }

    private void validateSkgkExists(Long id) {
        if (skgkMapper.selectById(id) == null) {
            throw exception(SKGK_NOT_EXISTS);
        }
    }

    @Override
    public SkgkDO getSkgk(Long id) {
        return skgkMapper.selectById(id);
    }

    @Override
    public PageResult<SkgkDO> getSkgkPage(SkgkPageReqVO pageReqVO) {
        return skgkMapper.selectPage(pageReqVO);
    }

}
