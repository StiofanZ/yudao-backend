package cn.iocoder.yudao.module.lghjft.service.auth.app;

import cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo.AuthorizeLghReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo.AuthorizeResVO;
import jakarta.validation.Valid;

public interface AppAuthenticateService {
    /**
     * 授权码登录
     *
     * @param reqVO 登录信息
     * @return 登录结果
     */
    AuthorizeResVO appLoginAuthCode(@Valid AuthorizeLghReqVO reqVO);
}
