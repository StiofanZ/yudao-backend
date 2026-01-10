package cn.iocoder.yudao.module.lghjft.controller.admin.auth;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo.AuthenticateReqVO;
import cn.iocoder.yudao.module.lghjft.service.auth.AuthenticateService;
import cn.iocoder.yudao.module.system.controller.admin.auth.vo.AuthLoginRespVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
@Tag(name = "管理后台 - 陇工会经费通认证")
@RestController
@RequestMapping("/lghjft")
@Validated
@Slf4j
public class AuthenticateController {
    @Resource
    private AuthenticateService authenticateService;
    @PostMapping("/login/login-by-lgh")
    @PermitAll
    @Operation(summary = "LGH 授权登录")
    public CommonResult<AuthLoginRespVO> loginByLgh(@RequestBody @Valid AuthenticateReqVO reqVO) {
        return success(authenticateService.loginAuthCode(reqVO));
    }
}
