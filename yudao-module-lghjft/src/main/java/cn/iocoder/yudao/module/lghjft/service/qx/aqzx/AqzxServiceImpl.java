package cn.iocoder.yudao.module.lghjft.service.qx.aqzx;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.aqzx.vo.AqzxProfileResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.aqzx.vo.AqzxUpdateNoticeMobileReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.aqzx.vo.AqzxUpdatePasswordReqVO;
import cn.iocoder.yudao.module.lghjft.service.dx.DxfwService;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.dal.mysql.user.AdminUserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.AQZX_VERIFY_MOBILE_NOT_EXISTS;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.USER_NOT_EXISTS;

@Service
@Validated
public class AqzxServiceImpl implements AqzxService {

    @Resource
    private AdminUserMapper adminUserMapper;
    @Resource
    private DxfwService dxfwService;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public AqzxProfileResVO getCurrentProfile() {
        return toProfileResVO(getCurrentUser());
    }

    @Override
    public void sendSmsCode(String mobile) {
        dxfwService.fsaqyzm(mobile);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateNoticeMobile(AqzxUpdateNoticeMobileReqVO reqVO) {
        dxfwService.jyaqyzm(reqVO.getMobile(), reqVO.getCode());
        AdminUserDO update = new AdminUserDO();
        update.setId(SecurityFrameworkUtils.getLoginUserId());
        update.setMobile(reqVO.getMobile());
        adminUserMapper.updateById(update);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePassword(AqzxUpdatePasswordReqVO reqVO) {
        AdminUserDO currentUser = getCurrentUser();
        String verifyMobile = currentUser.getMobile();
        if (StrUtil.isBlank(verifyMobile)) {
            throw exception(AQZX_VERIFY_MOBILE_NOT_EXISTS);
        }
        dxfwService.jyaqyzm(verifyMobile, reqVO.getCode());
        AdminUserDO update = new AdminUserDO();
        update.setId(currentUser.getId());
        update.setPassword(passwordEncoder.encode(reqVO.getPassword()));
        adminUserMapper.updateById(update);
    }

    private AdminUserDO getCurrentUser() {
        AdminUserDO user = adminUserMapper.selectById(SecurityFrameworkUtils.getLoginUserId());
        if (user == null) {
            throw exception(USER_NOT_EXISTS);
        }
        return user;
    }

    private AqzxProfileResVO toProfileResVO(AdminUserDO user) {
        AqzxProfileResVO resVO = new AqzxProfileResVO();
        resVO.setId(user.getId());
        resVO.setYhzh(user.getUsername());
        resVO.setYhxm(user.getNickname());
        resVO.setLxdh(user.getMobile());
        resVO.setYhyx(user.getEmail());
        resVO.setShxydm(user.getShxydm());
        resVO.setLoginIp(user.getLoginIp());
        resVO.setLoginDate(user.getLoginDate());
        return resVO;
    }
}
