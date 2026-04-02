package cn.iocoder.yudao.module.lghjft.service.xejf.xejf2023;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf2023.vo.Xejf2023PageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf2023.vo.Xejf2023SaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf2023.vo.XejftjResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejf2023.Xejf2023DO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xejf2023.Xejf2023Mapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.XEJF2023_NOT_EXISTS;

@Service
@Validated
public class Xejf2023ServiceImpl implements Xejf2023Service {

    @Resource
    private Xejf2023Mapper xejf2023Mapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long createXejf2023(Xejf2023SaveReqVO createReqVO) {
        Xejf2023DO entity = BeanUtils.toBean(createReqVO, Xejf2023DO.class);
        xejf2023Mapper.insert(entity);
        return entity.getGhjfId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateXejf2023(Xejf2023SaveReqVO updateReqVO) {
        Xejf2023DO updateObj = BeanUtils.toBean(updateReqVO, Xejf2023DO.class);
        xejf2023Mapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteXejf2023(Long id) {
        validateExists(id);
        xejf2023Mapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteXejf2023ListByIds(List<Long> ids) {
        xejf2023Mapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (xejf2023Mapper.selectById(id) == null) {
            throw exception(XEJF2023_NOT_EXISTS);
        }
    }

    @Override
    public Xejf2023DO getXejf2023(Long id) {
        return xejf2023Mapper.selectById(id);
    }

    @Override
    public PageResult<Xejf2023DO> getXejf2023Page(Xejf2023PageReqVO pageReqVO) {
        return xejf2023Mapper.selectPage(pageReqVO);
    }

    @Override
    public List<Xejf2023DO> getXejf2023PageTz(Xejf2023PageReqVO pageReqVO) {
        return xejf2023Mapper.selectPageTz(pageReqVO);
    }

    @Override
    public List<XejftjResVO> getXejf2023Xetj(Xejf2023PageReqVO pageReqVO) {
        return xejf2023Mapper.selectXetjList(pageReqVO);
    }
}
