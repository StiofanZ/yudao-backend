package cn.iocoder.yudao.module.lghjft.service.auth.app;

import cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo.AuthenticateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.auth.vo.AuthLoginRespVO;
import jakarta.validation.Valid;

public interface AppAuthenticateService {
    /**
     * 授权码登录
     *
     * @param reqVO 登录信息
     * @return 登录结果
     */
    AuthLoginRespVO appLoginAuthCode(@Valid AuthenticateReqVO reqVO);
}
