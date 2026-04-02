package cn.iocoder.yudao.module.lghjft.service.jfcl.thpzcf;

import cn.idev.excel.util.StringUtils;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.thpzcf.vo.ThpzcfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.thpzcf.vo.ThpzcfSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.thpzcf.ThpzcfDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.thpzcf.ThpzcfMapper;
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
public class ThpzcfServiceImpl implements ThpzcfService {

    @Resource
    private ThpzcfMapper thpzcfMapper;

    @Resource
    private AdminUserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createThpzcf(ThpzcfSaveReqVO createReqVO) {
        ThpzcfDO entity = BeanUtils.toBean(createReqVO, ThpzcfDO.class);
        thpzcfMapper.insert(entity);
        return entity.getHkxxId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateThpzcf(ThpzcfSaveReqVO updateReqVO) {
        validateThpzcfExists(updateReqVO.getHkxxId());
        ThpzcfDO updateObj = BeanUtils.toBean(updateReqVO, ThpzcfDO.class);
        thpzcfMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteThpzcf(Long id) {
        validateThpzcfExists(id);
        thpzcfMapper.deleteById(id);
    }

    @Override
    public ThpzcfDO getThpzcf(Long id) {
        return thpzcfMapper.selectById(id);
    }

    @Override
    public PageResult<ThpzcfDO> getThpzcfPage(ThpzcfPageReqVO pageReqVO) {
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
        return thpzcfMapper.selectPage(pageReqVO);
    }

    private void validateThpzcfExists(Long id) {
        if (thpzcfMapper.selectById(id) == null) {
            throw exception(JFCL_NOT_EXISTS);
        }
    }
}
