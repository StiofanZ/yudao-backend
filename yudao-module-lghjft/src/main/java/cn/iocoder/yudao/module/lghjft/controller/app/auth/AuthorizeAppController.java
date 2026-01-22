package cn.iocoder.yudao.module.lghjft.controller.app.auth;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo.AuthorizeLghReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo.AuthorizeReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo.AuthorizeResVO;
import cn.iocoder.yudao.module.lghjft.controller.app.auth.vo.AuthorizeAppReqVO;
import cn.iocoder.yudao.module.lghjft.controller.app.auth.vo.AuthorizeAppResVO;
import cn.iocoder.yudao.module.lghjft.enums.logger.LoginTypeEnum;
import cn.iocoder.yudao.module.lghjft.service.auth.AuthenticateService;
import cn.iocoder.yudao.module.lghjft.service.auth.app.AppAuthenticateService;
import cn.iocoder.yudao.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import cn.iocoder.yudao.module.system.service.oauth2.OAuth2TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "用户 App - 陇工会经费通认证")
@RestController
@RequestMapping("/lghjft")
@Validated
@Slf4j
public class AuthorizeAppController {
    @Resource
    private AuthenticateService authenticateService;
    // 新增的App端Service
    @Resource
    private AppAuthenticateService appAuthenticateService;
    @Resource
    private OAuth2TokenService oauth2TokenService;

    @PostMapping("/auth/login")
    @PermitAll
    @Operation(summary = "授权登录")
    public CommonResult<AuthorizeAppResVO> login(@RequestBody @Valid AuthorizeAppReqVO appReqVO) {
        AuthorizeReqVO reqVO = BeanUtils.toBean(appReqVO, AuthorizeReqVO.class);
        reqVO.setLoginType(LoginTypeEnum.LOGIN_SHXYDM);
        AuthorizeResVO resVO = authenticateService.login(reqVO);
        return success(BeanUtils.toBean(resVO, AuthorizeAppResVO.class));
    }


    @PostMapping("/auth/login-by-lgh")
    @PermitAll
    @Operation(summary = "LGH 授权登录")
    public CommonResult<AuthorizeResVO> loginByLgh(@RequestBody @Valid AuthorizeLghReqVO reqVO) {

        // 判断逻辑：App端会传loginSign参数，PC端不传
        if (StringUtils.isNotBlank(reqVO.getLoginSign()) && !reqVO.getLoginSign().equals("app")) {
            log.info("检测到loginSign参数，使用App端登录: {}", reqVO.getLoginSign());
            return success(appAuthenticateService.appLoginAuthCode(reqVO));
        } else {
            log.info("未检测到loginSign参数，使用PC端登录");
            return success(authenticateService.loginAuthCode(reqVO));
        }
    }

    @GetMapping("/auth/check-token")
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
