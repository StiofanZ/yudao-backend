package cn.iocoder.yudao.module.lghjft.service.cxtj.ghjfjfdw;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ghjfjfdw.vo.GhjfjfdwPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ghjfjfdw.vo.GhjfjfdwSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.ghjfjfdw.GhjfjfdwDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.ghjfjfdw.GhjfjfdwMapper;
import cn.iocoder.yudao.module.lghjft.framework.deptfilter.DeptFilterHelper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.GHJFJFDW_NOT_EXISTS;

@Service
@Validated
public class GhjfjfdwServiceImpl implements GhjfjfdwService {

    @Resource
    private GhjfjfdwMapper ghjfjfdwMapper;

    @Resource
    private DeptFilterHelper deptFilterHelper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createGhjfjfdw(GhjfjfdwSaveReqVO createReqVO) {
        GhjfjfdwDO obj = BeanUtils.toBean(createReqVO, GhjfjfdwDO.class);
        ghjfjfdwMapper.insert(obj);
        return obj.getDjxh();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateGhjfjfdw(GhjfjfdwSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getDjxh());
        GhjfjfdwDO updateObj = BeanUtils.toBean(updateReqVO, GhjfjfdwDO.class);
        ghjfjfdwMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteGhjfjfdw(String djxh) {
        validateExists(djxh);
        ghjfjfdwMapper.deleteById(djxh);
    }

    @Override
    public GhjfjfdwDO getGhjfjfdw(String djxh) {
        return ghjfjfdwMapper.selectById(djxh);
    }

    @Override
    public PageResult<GhjfjfdwDO> getGhjfjfdwPage(GhjfjfdwPageReqVO pageReqVO) {
        // 应用部门过滤逻辑（还原 V1 行为：root = "100000"）
        pageReqVO.setDeptId(deptFilterHelper.filterDeptId(pageReqVO.getDeptId()));
        return ghjfjfdwMapper.selectPage(pageReqVO);
    }

    private void validateExists(String djxh) {
        if (ghjfjfdwMapper.selectById(djxh) == null) {
            throw exception(GHJFJFDW_NOT_EXISTS);
        }
    }
}
