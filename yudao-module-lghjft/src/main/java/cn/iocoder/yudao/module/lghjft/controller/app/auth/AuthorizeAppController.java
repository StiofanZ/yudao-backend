package cn.iocoder.yudao.module.lghjft.controller.app.auth;

import cn.iocoder.yudao.framework.common.enums.UserTypeEnum;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo.*;
import cn.iocoder.yudao.module.lghjft.controller.app.auth.vo.AuthorizeAppResVO;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "用户 App - 登录认证")
@RestController
@RequestMapping("/lghjft")
@Validated
@Slf4j
public class AuthorizeAppController {
    @Resource
    private AuthenticateService authenticateService;
    @Resource
    private AppAuthenticateService appAuthenticateService;
    @Resource
    private OAuth2TokenService oauth2TokenService;

    @PostMapping("/auth/login")
    @PermitAll
    @Operation(summary = "授权登录")
    public CommonResult<AuthorizeAppResVO> login(@RequestBody @Valid AuthorizeReqVO reqVO) {
        reqVO.setYhlx(UserTypeEnum.MEMBER.getValue());
        AuthorizeResVO resVO = authenticateService.login(reqVO);
        return success(BeanUtils.toBean(resVO, AuthorizeAppResVO.class));
    }

    @PostMapping("/auth/send-sms-code")
    @PermitAll
    @Operation(summary = "发送短信验证码")
    public CommonResult<Boolean> sendSmsCode(@RequestBody @Valid DxfsReqVO reqVO) {
        authenticateService.sendSmsCode(reqVO.getLxdh(), UserTypeEnum.MEMBER.getValue());
        return success(true);
    }

    @PostMapping("/auth/sms-login")
    @PermitAll
    @Operation(summary = "短信登录")
    public CommonResult<AuthorizeAppResVO> smsLogin(@RequestBody @Valid DxdlReqVO reqVO) {
        reqVO.setYhlx(UserTypeEnum.MEMBER.getValue());
        return success(BeanUtils.toBean(authenticateService.smsLogin(reqVO), AuthorizeAppResVO.class));
    }

    @PostMapping("/auth/login-by-lgh")
    @PermitAll
    @Operation(summary = "LGH 授权登录")
    public CommonResult<AuthorizeAppResVO> loginByLgh(@RequestBody @Valid AuthorizeLghReqVO reqVO) {
        AuthorizeResVO resVO;
        if (StringUtils.isNotBlank(reqVO.getLoginSign()) && !"app".equals(reqVO.getLoginSign())) {
            log.info("检测到loginSign参数，使用App端登录: {}", reqVO.getLoginSign());
            resVO = appAuthenticateService.appLoginAuthCode(reqVO);
        } else {
            log.info("未检测到loginSign参数，使用PC端登录");
            resVO = authenticateService.loginAuthCode(reqVO);
        }
        return success(BeanUtils.toBean(resVO, AuthorizeAppResVO.class));
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

    @GetMapping("/auth/get-dwqxsf")
    @Operation(summary = "获取单位权限身份")
    @PreAuthorize("isAuthenticated()")
    public CommonResult<List<AuthorizeAppResVO.DwQxSf>> getDwQxSfList() {
        Long dlzhId = SecurityFrameworkUtils.getLoginUserId();
        return success(BeanUtils.toBean(authenticateService.getDwQxSfList(dlzhId), AuthorizeAppResVO.DwQxSf.class));
    }
}
