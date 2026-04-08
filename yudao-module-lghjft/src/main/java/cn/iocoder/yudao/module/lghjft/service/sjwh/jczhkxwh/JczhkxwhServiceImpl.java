package cn.iocoder.yudao.module.lghjft.service.sjwh.jczhkxwh;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jczhkxwh.vo.JczhkxwhPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jczhkxwh.vo.JczhkxwhSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.jczhkxwh.JczhkxwhDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.jczhkxwh.JczhkxwhMapper;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.HKXX_NOT_EXISTS;

@Service
@Validated
public class JczhkxwhServiceImpl implements JczhkxwhService {

    @Resource
    private JczhkxwhMapper jczhkxwhMapper;
    @Resource
    private AdminUserService userService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String createJczhkxwh(JczhkxwhSaveReqVO createReqVO) {
        JczhkxwhDO jczhkxwh = BeanUtils.toBean(createReqVO, JczhkxwhDO.class);
        jczhkxwhMapper.insert(jczhkxwh);
        return jczhkxwh.getDjxh();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateJczhkxwh(JczhkxwhSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getDjxh());
        JczhkxwhDO updateObj = BeanUtils.toBean(updateReqVO, JczhkxwhDO.class);
        jczhkxwhMapper.updateById(updateObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteJczhkxwh(String id) {
        validateExists(id);
        jczhkxwhMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteJczhkxwhListByIds(List<String> ids) {
        jczhkxwhMapper.deleteByIds(ids);
    }

    private void validateExists(String id) {
        if (jczhkxwhMapper.selectById(id) == null) {
            throw exception(HKXX_NOT_EXISTS);
        }
    }

    @Override
    public JczhkxwhDO getJczhkxwh(String id) {
        return jczhkxwhMapper.selectById(id);
    }

    @Override
    public PageResult<JczhkxwhDO> getJczhkxwhPage(JczhkxwhPageReqVO pageReqVO) {
        // v1 dept filtering: default to login user's deptId, 100000 means all
        if (StrUtil.isEmpty(pageReqVO.getDeptId())) {
            AdminUserDO user = userService.getUser(getLoginUserId());
            pageReqVO.setDeptId(user.getDeptId().toString());
        }
        if ("100000".equals(pageReqVO.getDeptId())) {
            pageReqVO.setDeptId(null);
        }
        return jczhkxwhMapper.selectPage(pageReqVO);
    }
}
