package cn.iocoder.yudao.module.lghjft.service.jf.ghjf;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jf.ghjf.vo.GhJfCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jf.ghjf.vo.GhJfReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jf.ghjf.vo.GhJfUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jf.ghjf.GhJfDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jf.ghjf.GhJfMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.GH_JF_NOT_EXISTS;

/**
 * 税务入库 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class GhJfServiceImpl implements GhJfService {

    @Resource
    private GhJfMapper ghJfMapper;

    @Override
    public Integer createGhJf(GhJfCreateReqVO createReqVO) {
        GhJfDO ghJf = BeanUtils.toBean(createReqVO, GhJfDO.class);
        ghJfMapper.insert(ghJf);
        return ghJf.getGhjfId();
    }

    @Override
    public void updateGhJf(GhJfUpdateReqVO updateReqVO) {
        // 校验存在
        validateGhJfExists(updateReqVO.getGhjfId());
        // 更新
        GhJfDO updateObj = BeanUtils.toBean(updateReqVO, GhJfDO.class);
        ghJfMapper.updateById(updateObj);
    }

    @Override
    public void deleteGhJf(Integer id) {
        // 校验存在
        validateGhJfExists(id);
        // 删除
        ghJfMapper.deleteById(id);
    }

    private void validateGhJfExists(Integer id) {
        if (ghJfMapper.selectById(id) == null) {
            throw exception(GH_JF_NOT_EXISTS);
        }
    }

    @Override
    public GhJfDO getGhJf(Integer id) {
        return ghJfMapper.selectById(id);
    }

    @Override
    public List<GhJfDO> getGhJfList(Collection<Integer> ids) {
        return ghJfMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<GhJfDO> getGhJfPage(GhJfReqVO pageReqVO) {
        return ghJfMapper.selectPage(pageReqVO);
    }

}
