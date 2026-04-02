package cn.iocoder.yudao.module.lghjft.service.jfcl.sgbflr;

import cn.idev.excel.util.StringUtils;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.sgbflr.vo.SgbflrPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.sgbflr.vo.SgbflrSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.sgbflr.SgbflrDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.sgbflr.SgbflrMapper;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.JFCL_NOT_EXISTS;

@Service
@Validated
public class SgbflrServiceImpl implements SgbflrService {

    @Resource
    private SgbflrMapper sgbflrMapper;

    @Resource
    private AdminUserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createSgbflr(SgbflrSaveReqVO createReqVO) {
        SgbflrDO entity = BeanUtils.toBean(createReqVO, SgbflrDO.class);
        sgbflrMapper.insert(entity);
        return entity.getHkxxId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSgbflr(SgbflrSaveReqVO updateReqVO) {
        validateSgbflrExists(updateReqVO.getHkxxId());
        SgbflrDO updateObj = BeanUtils.toBean(updateReqVO, SgbflrDO.class);
        sgbflrMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSgbflr(Long id) {
        validateSgbflrExists(id);
        sgbflrMapper.deleteById(id);
    }

    @Override
    public SgbflrDO getSgbflr(Long id) {
        return sgbflrMapper.selectById(id);
    }

    @Override
    public PageResult<SgbflrDO> getSgbflrPage(SgbflrPageReqVO pageReqVO) {
        // v1 deptId filtering
        if (StringUtils.isEmpty(pageReqVO.getDeptId())) {
            try {
                AdminUserDO user = userService.getUser(getLoginUserId());
                if (user != null && user.getDeptId() != null) {
                    pageReqVO.setDeptId(user.getDeptId().toString());
                }
            } catch (Exception e) { /* ignore */ }
        }
        if ("100000".equals(pageReqVO.getDeptId())) {
            pageReqVO.setDeptId(null);
        }
        return sgbflrMapper.selectPage(pageReqVO);
    }

    private void validateSgbflrExists(Long id) {
        if (sgbflrMapper.selectById(id) == null) {
            throw exception(JFCL_NOT_EXISTS);
        }
    }
}
