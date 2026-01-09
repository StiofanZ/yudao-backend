package cn.iocoder.yudao.module.lghjft.service.auth;

import cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo.AuthenticateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.auth.vo.AuthLoginRespVO;
import jakarta.validation.Valid;

public interface AuthenticateService {
    /**
     * 授权码登录
     *
     * @param reqVO 登录信息
     * @return 登录结果
     */
    AuthLoginRespVO loginAuthCode(@Valid AuthenticateReqVO reqVO);
}
