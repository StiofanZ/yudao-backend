package cn.iocoder.yudao.module.lghjft.service.qx.aqzx;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.aqzx.vo.AqzxProfileResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.aqzx.vo.AqzxUpdateNoticeMobileReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.aqzx.vo.AqzxUpdatePasswordReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.dlzh.GhQxDlzhDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.qx.dlzh.GhQxDlzhMapper;
import cn.iocoder.yudao.module.lghjft.service.dx.DxfwService;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.AQZX_VERIFY_MOBILE_NOT_EXISTS;
import static cn.iocoder.yudao.module.lghjft.enums.ErrorCodeConstants.DLZH_NOT_EXISTS;

@Service
@Validated
public class AqzxServiceImpl implements AqzxService {

    @Resource
    private GhQxDlzhMapper ghQxDlzhMapper;
    @Resource
    private DxfwService dxfwService;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public AqzxProfileResVO getCurrentProfile() {
        return BeanUtils.toBean(getCurrentUser(), AqzxProfileResVO.class);
    }

    @Override
    public void sendSmsCode(String mobile) {
        dxfwService.fsaqyzm(mobile);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateNoticeMobile(AqzxUpdateNoticeMobileReqVO reqVO) {
        dxfwService.jyaqyzm(reqVO.getMobile(), reqVO.getCode());

        ghQxDlzhMapper.updateById(GhQxDlzhDO.builder()
                .id(SecurityFrameworkUtils.getLoginUserId())
                .lxdh(reqVO.getMobile())
                .build());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePassword(AqzxUpdatePasswordReqVO reqVO) {
        GhQxDlzhDO currentUser = getCurrentUser();
        String verifyMobile = currentUser.getLxdh();
        if (StrUtil.isBlank(verifyMobile)) {
            throw exception(AQZX_VERIFY_MOBILE_NOT_EXISTS);
        }
        dxfwService.jyaqyzm(verifyMobile, reqVO.getCode());

        ghQxDlzhMapper.updateById(GhQxDlzhDO.builder()
                .id(currentUser.getId())
                .password(passwordEncoder.encode(reqVO.getPassword()))
                .build());
    }

    private GhQxDlzhDO getCurrentUser() {
        GhQxDlzhDO currentUser = ghQxDlzhMapper.selectById(SecurityFrameworkUtils.getLoginUserId());
        if (currentUser == null) {
            throw exception(DLZH_NOT_EXISTS);
        }
        return currentUser;
    }
}
