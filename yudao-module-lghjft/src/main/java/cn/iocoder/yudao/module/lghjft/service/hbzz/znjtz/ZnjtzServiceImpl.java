package cn.iocoder.yudao.module.lghjft.service.hbzz.znjtz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.znjtz.vo.ZnjtzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.znjtz.vo.ZnjtzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.znjtz.ZnjtzDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.znjtz.ZnjtzMapper;
import cn.iocoder.yudao.module.lghjft.framework.deptfilter.DeptFilterHelper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.JF_NOT_EXISTS;

@Service
@Validated
public class ZnjtzServiceImpl implements ZnjtzService {

    @Resource
    private ZnjtzMapper znjtzMapper;

    @Resource
    private DeptFilterHelper deptFilterHelper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createZnjtz(ZnjtzSaveReqVO createReqVO) {
        ZnjtzDO znjtz = BeanUtils.toBean(createReqVO, ZnjtzDO.class);
        znjtzMapper.insert(znjtz);
        return znjtz.getGhjfId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateZnjtz(ZnjtzSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getGhjfId());
        ZnjtzDO updateObj = BeanUtils.toBean(updateReqVO, ZnjtzDO.class);
        znjtzMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteZnjtz(Long id) {
        validateExists(id);
        // v1: cascade delete cxtj_cbjqrfb first
        znjtzMapper.deleteCbjqrfbByGhjfId(id);
        znjtzMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteZnjtzListByIds(List<Long> ids) {
        // v1: cascade delete cxtj_cbjqrfb first
        znjtzMapper.deleteCbjqrfbByGhjfIds(ids);
        znjtzMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (znjtzMapper.selectById(id) == null) {
            throw exception(JF_NOT_EXISTS);
        }
    }

    @Override
    public ZnjtzDO getZnjtz(Long id) {
        return znjtzMapper.selectById(id);
    }

    @Override
    public PageResult<ZnjtzDO> getZnjtzPage(ZnjtzPageReqVO pageReqVO) {
        // v1 dept filtering: root = 100000
        pageReqVO.setDeptId(deptFilterHelper.filterDeptId(pageReqVO.getDeptId()));
        return znjtzMapper.selectPage(pageReqVO);
    }
}
