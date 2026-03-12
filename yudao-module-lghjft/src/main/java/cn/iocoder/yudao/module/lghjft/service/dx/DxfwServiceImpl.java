package cn.iocoder.yudao.module.lghjft.service.dx;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.enums.UserTypeEnum;
import cn.iocoder.yudao.framework.common.util.servlet.ServletUtils;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.dlzh.GhQxDlzhDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.qx.dlzh.GhQxDlzhMapper;
import cn.iocoder.yudao.module.system.api.sms.SmsCodeApi;
import cn.iocoder.yudao.module.system.api.sms.SmsSendApi;
import cn.iocoder.yudao.module.system.api.sms.dto.code.SmsCodeSendReqDTO;
import cn.iocoder.yudao.module.system.api.sms.dto.code.SmsCodeUseReqDTO;
import cn.iocoder.yudao.module.system.api.sms.dto.send.SmsSendSingleToUserReqDTO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.dal.mysql.user.AdminUserMapper;
import cn.iocoder.yudao.module.system.enums.sms.SmsSceneEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.USER_NOT_EXISTS;

@Service
@Validated
public class DxfwServiceImpl implements DxfwService {

    private static final String XXTX_DX_MB = "lghjft-xxtx-tzdx";

    @Resource
    private SmsCodeApi smsCodeApi;
    @Resource
    private SmsSendApi smsSendApi;
    @Resource
    private GhQxDlzhMapper ghQxDlzhMapper;
    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public void fsdlyzm(String lxdh, Integer yhlx) {
        yzdlyh(lxdh, yhlx);
        SmsCodeSendReqDTO reqDTO = new SmsCodeSendReqDTO();
        reqDTO.setMobile(lxdh);
        reqDTO.setScene(hqdlyzmScene(yhlx));
        reqDTO.setCreateIp(ServletUtils.getClientIP());
        smsCodeApi.sendSmsCode(reqDTO);
    }

    @Override
    public void jydlyzm(String lxdh, String yzm, Integer yhlx) {
        SmsCodeUseReqDTO reqDTO = new SmsCodeUseReqDTO();
        reqDTO.setMobile(lxdh);
        reqDTO.setCode(yzm);
        reqDTO.setScene(hqdlyzmScene(yhlx));
        reqDTO.setUsedIp(ServletUtils.getClientIP());
        smsCodeApi.useSmsCode(reqDTO);
    }

    @Override
    public void fsaqyzm(String lxdh) {
        SmsCodeSendReqDTO reqDTO = new SmsCodeSendReqDTO();
        reqDTO.setMobile(lxdh);
        reqDTO.setScene(SmsSceneEnum.ADMIN_MEMBER_RESET_PASSWORD.getScene());
        reqDTO.setCreateIp(ServletUtils.getClientIP());
        smsCodeApi.sendSmsCode(reqDTO);
    }

    @Override
    public void jyaqyzm(String lxdh, String yzm) {
        SmsCodeUseReqDTO reqDTO = new SmsCodeUseReqDTO();
        reqDTO.setMobile(lxdh);
        reqDTO.setCode(yzm);
        reqDTO.setScene(SmsSceneEnum.ADMIN_MEMBER_RESET_PASSWORD.getScene());
        reqDTO.setUsedIp(ServletUtils.getClientIP());
        smsCodeApi.useSmsCode(reqDTO);
    }

    @Override
    public Long fstzdx(String lxdh, Long yhId, Integer yhlx, String dxnr) {
        if (StrUtil.isBlank(lxdh)) {
            throw exception(USER_NOT_EXISTS);
        }
        SmsSendSingleToUserReqDTO reqDTO = new SmsSendSingleToUserReqDTO();
        reqDTO.setMobile(lxdh);
        reqDTO.setUserId(yhId);
        reqDTO.setTemplateCode(XXTX_DX_MB);
        reqDTO.setTemplateParams(Map.of("dxnr", dxnr));
        if (UserTypeEnum.MEMBER.getValue().equals(yhlx)) {
            return smsSendApi.sendSingleSmsToMember(reqDTO);
        }
        return smsSendApi.sendSingleSmsToAdmin(reqDTO);
    }

    private void yzdlyh(String lxdh, Integer yhlx) {
        GhQxDlzhDO dlzhDO = ghQxDlzhMapper.selectOne(lxdh, null, null, null);
        if (dlzhDO != null) {
            return;
        }
        AdminUserDO userDO = adminUserMapper.selectByMobile(lxdh);
        if (userDO != null) {
            return;
        }
        throw exception(USER_NOT_EXISTS);
    }

    private Integer hqdlyzmScene(Integer yhlx) {
        if (UserTypeEnum.MEMBER.getValue().equals(yhlx)) {
            return SmsSceneEnum.MEMBER_LOGIN.getScene();
        }
        return SmsSceneEnum.ADMIN_MEMBER_LOGIN.getScene();
    }
}
