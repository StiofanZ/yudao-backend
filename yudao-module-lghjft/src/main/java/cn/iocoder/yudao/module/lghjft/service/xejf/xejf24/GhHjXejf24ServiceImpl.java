package cn.iocoder.yudao.module.lghjft.service.xejf.xejf24;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf24.vo.GhHjXejf24PageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf24.vo.GhHjXejf24SaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejf24.GhHjXejf24DO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xejf24.GhHjXejf24Mapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.XEJF24_NOT_EXISTS;

@Service
@Validated
public class GhHjXejf24ServiceImpl implements GhHjXejf24Service {

    @Resource
    private GhHjXejf24Mapper ghHjXejf24Mapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String createGhHjXejf24(GhHjXejf24SaveReqVO createReqVO) {
        GhHjXejf24DO entity = BeanUtils.toBean(createReqVO, GhHjXejf24DO.class);
        ghHjXejf24Mapper.insert(entity);
        return entity.getDeptId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateGhHjXejf24(GhHjXejf24SaveReqVO updateReqVO) {
        GhHjXejf24DO updateObj = BeanUtils.toBean(updateReqVO, GhHjXejf24DO.class);
        ghHjXejf24Mapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteGhHjXejf24(String id) {
        validateExists(id);
        ghHjXejf24Mapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteGhHjXejf24ListByIds(List<String> ids) {
        ghHjXejf24Mapper.deleteByIds(ids);
    }

    private void validateExists(String id) {
        if (ghHjXejf24Mapper.selectById(id) == null) {
            throw exception(XEJF24_NOT_EXISTS);
        }
    }

    @Override
    public GhHjXejf24DO getGhHjXejf24(String id) {
        return ghHjXejf24Mapper.selectById(id);
    }

    @Override
    public PageResult<GhHjXejf24DO> getGhHjXejf24Page(GhHjXejf24PageReqVO pageReqVO) {
        return ghHjXejf24Mapper.selectPage(pageReqVO);
    }
}
