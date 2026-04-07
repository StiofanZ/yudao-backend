package cn.iocoder.yudao.module.lghjft.service.cxtj.dhjftz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dhjftz.vo.DhjftzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dhjftz.vo.DhjftzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.dhjftz.DhjftzDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.dhjftz.DhjftzMapper;
import cn.iocoder.yudao.module.lghjft.framework.deptfilter.DeptFilterHelper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.DHJFTZ_NOT_EXISTS;

@Service
@Validated
public class DhjftzServiceImpl implements DhjftzService {

    @Resource
    private DhjftzMapper dhjftzMapper;

    @Resource
    private DeptFilterHelper deptFilterHelper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createDhjftz(DhjftzSaveReqVO createReqVO) {
        DhjftzDO obj = BeanUtils.toBean(createReqVO, DhjftzDO.class);
        dhjftzMapper.insert(obj);
        return obj.getDjxh();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDhjftz(DhjftzSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getDjxh());
        DhjftzDO updateObj = BeanUtils.toBean(updateReqVO, DhjftzDO.class);
        dhjftzMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDhjftz(String djxh) {
        validateExists(djxh);
        dhjftzMapper.deleteById(djxh);
    }

    @Override
    public DhjftzDO getDhjftz(String djxh) {
        return dhjftzMapper.selectById(djxh);
    }

    @Override
    public PageResult<DhjftzDO> getDhjftzPage(DhjftzPageReqVO pageReqVO) {
        // 应用部门过滤逻辑（还原 V1 行为：root = "100000"）
        pageReqVO.setDeptId(deptFilterHelper.filterDeptId(pageReqVO.getDeptId()));
        return dhjftzMapper.selectPage(pageReqVO);
    }

    private void validateExists(String djxh) {
        if (dhjftzMapper.selectById(djxh) == null) {
            throw exception(DHJFTZ_NOT_EXISTS);
        }
    }
}
