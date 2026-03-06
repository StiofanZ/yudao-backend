package cn.iocoder.yudao.module.lghjft.service.qx.aqzx;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.common.util.servlet.ServletUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.aqzx.vo.AqzxProfileRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.aqzx.vo.AqzxUpdateNoticeMobileReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.aqzx.vo.AqzxUpdatePasswordReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.dlzh.GhQxDlzhDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.qx.dlzh.GhQxDlzhMapper;
import cn.iocoder.yudao.module.system.api.sms.SmsCodeApi;
import cn.iocoder.yudao.module.system.api.sms.dto.code.SmsCodeSendReqDTO;
import cn.iocoder.yudao.module.system.api.sms.dto.code.SmsCodeUseReqDTO;
import cn.iocoder.yudao.module.system.enums.sms.SmsSceneEnum;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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
    private SmsCodeApi smsCodeApi;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public AqzxProfileRespVO getCurrentProfile() {
        return BeanUtils.toBean(getCurrentUser(), AqzxProfileRespVO.class);
    }

    @Override
    public void sendSmsCode(String mobile) {
        SmsCodeSendReqDTO reqDTO = new SmsCodeSendReqDTO();
        reqDTO.setMobile(mobile);
        reqDTO.setScene(SmsSceneEnum.ADMIN_MEMBER_RESET_PASSWORD.getScene());
        reqDTO.setCreateIp(ServletUtils.getClientIP());
        smsCodeApi.sendSmsCode(reqDTO);
    }

    @Override
    public void updateNoticeMobile(AqzxUpdateNoticeMobileReqVO reqVO) {
        SmsCodeUseReqDTO reqDTO = new SmsCodeUseReqDTO();
        reqDTO.setMobile(reqVO.getMobile());
        reqDTO.setCode(reqVO.getCode());
        reqDTO.setScene(SmsSceneEnum.ADMIN_MEMBER_RESET_PASSWORD.getScene());
        reqDTO.setUsedIp(ServletUtils.getClientIP());
        smsCodeApi.useSmsCode(reqDTO);

        ghQxDlzhMapper.updateById(GhQxDlzhDO.builder()
                .id(SecurityFrameworkUtils.getLoginUserId())
                .lxdh(reqVO.getMobile())
                .build());
    }

    @Override
    public void updatePassword(AqzxUpdatePasswordReqVO reqVO) {
        GhQxDlzhDO currentUser = getCurrentUser();
        String verifyMobile = currentUser.getLxdh();
        if (StrUtil.isBlank(verifyMobile)) {
            throw exception(AQZX_VERIFY_MOBILE_NOT_EXISTS);
        }

        SmsCodeUseReqDTO reqDTO = new SmsCodeUseReqDTO();
        reqDTO.setMobile(verifyMobile);
        reqDTO.setCode(reqVO.getCode());
        reqDTO.setScene(SmsSceneEnum.ADMIN_MEMBER_RESET_PASSWORD.getScene());
        reqDTO.setUsedIp(ServletUtils.getClientIP());
        smsCodeApi.useSmsCode(reqDTO);

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
