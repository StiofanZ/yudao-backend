package cn.iocoder.yudao.module.lghjft.service.jfcl.jfmxwh;

import cn.idev.excel.util.StringUtils;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfmxwh.vo.JfmxwhPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfmxwh.vo.JfmxwhSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.jfmxwh.JfmxwhDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.jfmxwh.JfmxwhMapper;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.JFCL_NOT_EXISTS;

@Service
@Validated
public class JfmxwhServiceImpl implements JfmxwhService {

    @Resource
    private JfmxwhMapper jfmxwhMapper;

    @Resource
    private AdminUserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createJfmxwh(JfmxwhSaveReqVO createReqVO) {
        JfmxwhDO entity = BeanUtils.toBean(createReqVO, JfmxwhDO.class);
        jfmxwhMapper.insert(entity);
        return entity.getGhjfId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateJfmxwh(JfmxwhSaveReqVO updateReqVO) {
        validateJfmxwhExists(updateReqVO.getGhjfId());
        JfmxwhDO updateObj = BeanUtils.toBean(updateReqVO, JfmxwhDO.class);
        jfmxwhMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteJfmxwh(Long id) {
        validateJfmxwhExists(id);
        jfmxwhMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteJfmxwhList(List<Long> ids) {
        jfmxwhMapper.deleteBatchIds(ids);
    }

    @Override
    public JfmxwhDO getJfmxwh(Long id) {
        return jfmxwhMapper.selectById(id);
    }

    @Override
    public PageResult<JfmxwhDO> getJfmxwhPage(JfmxwhPageReqVO pageReqVO) {
        // v1 deptId filtering: default to current user's deptId, root=100000 sees all
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
        return jfmxwhMapper.selectPage(pageReqVO);
    }

    private void validateJfmxwhExists(Long id) {
        if (jfmxwhMapper.selectById(id) == null) {
            throw exception(JFCL_NOT_EXISTS);
        }
    }
}
