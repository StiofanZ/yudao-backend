package cn.iocoder.yudao.module.lghjft.service.sjwh.cbjtz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjtz.vo.CbjtzBatchReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjtz.vo.CbjtzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjtz.vo.CbjtzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjtz.vo.DgjftzResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.cbjtz.CbjtzDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.cbjtz.CbjtzMapper;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.JF_NOT_EXISTS;

@Service
@Validated
public class CbjtzServiceImpl implements CbjtzService {

    @Resource
    private CbjtzMapper cbjtzMapper;
    @Resource
    private AdminUserService userService;

    @Override
    public Long createCbjtz(CbjtzSaveReqVO createReqVO) {
        CbjtzDO cbjtz = BeanUtils.toBean(createReqVO, CbjtzDO.class);
        cbjtzMapper.insert(cbjtz);
        return cbjtz.getGhjfId();
    }

    @Override
    public void updateCbjtz(CbjtzSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getGhjfId());
        CbjtzDO updateObj = BeanUtils.toBean(updateReqVO, CbjtzDO.class);
        cbjtzMapper.updateById(updateObj);
    }

    @Override
    public void deleteCbjtz(Long id) {
        validateExists(id);
        cbjtzMapper.deleteById(id);
    }

    @Override
    public void deleteCbjtzListByIds(List<Long> ids) {
        cbjtzMapper.deleteByIds(ids);
    }

    private void validateExists(Long id) {
        if (cbjtzMapper.selectById(id) == null) {
            throw exception(JF_NOT_EXISTS);
        }
    }

    @Override
    public CbjtzDO getCbjtz(Long id) {
        return cbjtzMapper.selectById(id);
    }

    @Override
    public PageResult<CbjtzDO> getCbjtzPage(CbjtzPageReqVO pageReqVO) {
        return cbjtzMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchCbjqrfbpl(List<CbjtzBatchReqVO> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        cbjtzMapper.batchUpdateCbjqrfbpl(records);
    }

    @Override
    public List<DgjftzResVO> getDgjftzList(CbjtzPageReqVO pageReqVO) {
        return cbjtzMapper.selectDgjftzList(pageReqVO);
    }

    @Override
    public PageResult<CbjtzDO> getDgjftzPage(CbjtzPageReqVO pageReqVO) {
        // v1 deptId logic
        if (pageReqVO.getDeptId() == null || pageReqVO.getDeptId().isEmpty()) {
            try {
                cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO user =
                        userService.getUser(cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.getLoginUserId());
                if (user != null && user.getDeptId() != null) {
                    pageReqVO.setDeptId(user.getDeptId().toString());
                }
            } catch (Exception e) { /* ignore */ }
        }
        if ("100000".equals(pageReqVO.getDeptId())) {
            pageReqVO.setDeptId(null);
        }
        return cbjtzMapper.selectDgjftzPage(pageReqVO);
    }
}
