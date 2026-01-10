package cn.iocoder.yudao.module.lghjft.controller.admin.auth;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo.AuthenticateReqVO;
import cn.iocoder.yudao.module.lghjft.service.auth.AuthenticateService;
import cn.iocoder.yudao.module.system.controller.admin.auth.vo.AuthLoginRespVO;
import cn.iocoder.yudao.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import cn.iocoder.yudao.module.system.service.oauth2.OAuth2TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
@Tag(name = "管理后台 - 陇工会经费通认证")
@RestController
@RequestMapping("/lghjft")
@Validated
@Slf4j
public class AuthenticateController {
    @Resource
    private AuthenticateService authenticateService;

    @Resource
    private OAuth2TokenService oauth2TokenService;

    @PostMapping("/login/login-by-lgh")
    @PermitAll
    @Operation(summary = "LGH 授权登录")
    public CommonResult<AuthLoginRespVO> loginByLgh(@RequestBody @Valid AuthenticateReqVO reqVO) {
        return success(authenticateService.loginAuthCode(reqVO));
    }

    @GetMapping("/check-token")
    @PermitAll
    @Operation(summary = "校验令牌")
    @Parameter(name = "token", description = "访问令牌", required = true)
    public CommonResult<Long> checkToken(@RequestParam("token") String token) {
        OAuth2AccessTokenDO accessTokenDO = oauth2TokenService.checkAccessToken(token);
        if (accessTokenDO == null) {
            return success(null);
        }
        return success(accessTokenDO.getUserId());
    }
}
