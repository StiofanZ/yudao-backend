package cn.iocoder.yudao.module.lghjft.service.auth;

import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.crypto.symmetric.AES;
import cn.iocoder.yudao.framework.common.util.http.HttpUtils;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.framework.common.util.monitor.TracerUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.common.util.servlet.ServletUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo.AuthenticateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.auth.GhCsSsoDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.auth.GhCsSsoMapper;
import cn.iocoder.yudao.module.system.api.logger.dto.LoginLogCreateReqDTO;
import cn.iocoder.yudao.module.system.controller.admin.auth.vo.AuthLoginRespVO;
import cn.iocoder.yudao.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.enums.logger.LoginLogTypeEnum;
import cn.iocoder.yudao.module.system.enums.logger.LoginResultEnum;
import cn.iocoder.yudao.module.system.enums.oauth2.OAuth2ClientConstants;
import cn.iocoder.yudao.framework.common.enums.UserTypeEnum;
import cn.iocoder.yudao.module.system.service.logger.LoginLogService;
import cn.iocoder.yudao.module.system.service.oauth2.OAuth2TokenService;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

import cn.iocoder.yudao.module.lghjft.framework.auth.config.LghJftAuthProperties;

@Service
@Slf4j
public class AuthenticateServiceImpl implements AuthenticateService {

    @Resource
    private LghJftAuthProperties lghJftAuthProperties;
    @Resource
    private GhCsSsoMapper ghCsSsoMapper;
    @Resource
    private AdminUserService userService;
    @Resource
    private OAuth2TokenService oauth2TokenService;
    @Resource
    private LoginLogService loginLogService;

    @Override
    public AuthLoginRespVO loginAuthCode(AuthenticateReqVO reqVO) {
        // 1. 解密 authCode
        String token;
        AES aes = new AES(Mode.ECB, Padding.PKCS5Padding, lghJftAuthProperties.getDecryptKey().getBytes(StandardCharsets.UTF_8));
        try {
            token = aes.decryptStr(Base64.getUrlDecoder().decode(reqVO.getAuthCode()));
            log.info("decrypt auth code success. authCode: {}", reqVO.getAuthCode());
        } catch (Exception e) {
            log.error("Decrypt auth code failed. authCode: {}", reqVO.getAuthCode(), e);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }

        // 2. 调用第三方接口获取用户信息
        String requestId = UUID.randomUUID().toString(true);
        String timestamp = String.valueOf(System.currentTimeMillis());
        String sign = MD5.create().digestHex("appId=" + lghJftAuthProperties.getAppId() + "&requestId=" + requestId + "&timestamp=" + timestamp + "&secret=" + lghJftAuthProperties.getAppSecret());
        
        Map<String, String> params = new LinkedHashMap<>();
        params.put("appId", lghJftAuthProperties.getAppId());
        params.put("platform", lghJftAuthProperties.getPlatform());
        params.put("requestId", requestId);
        params.put("sign", sign);
        params.put("timestamp", timestamp);
        params.put("token", token);
        
        Map<String,String> headers = Map.of(
                "Content-Type", "application/json",
                "appId", lghJftAuthProperties.getAppId(),
                "token", Base64.getUrlEncoder().encodeToString(aes.encrypt(JsonUtils.toJsonString(params).getBytes(StandardCharsets.UTF_8)))
        );

        String response;
        try {
            response = HttpUtils.post(lghJftAuthProperties.getGatewayUrl(), headers, null);
        } catch (Exception e) {
            log.error("Call SSO gateway failed. url: {}", lghJftAuthProperties.getGatewayUrl(), e);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }

        // 3. 解析响应
        String decrypt1;
        try {
            decrypt1 = aes.decryptStr(Base64.getUrlDecoder().decode(response));
            log.info("SSO response decrypted: {}", decrypt1);
        } catch (Exception e) {
            log.error("Decrypt SSO response failed. response: {}", response, e);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }

        Map<String,Object> decryptMap1 = (Map<String, Object>) JsonUtils.parseObject(decrypt1, Map.class);
        if (decryptMap1 == null || !Integer.valueOf(0).equals(decryptMap1.get("code"))) {
            log.warn("SSO login failed. response code: {}", decryptMap1 != null ? decryptMap1.get("code") : "null");
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }

        String username;
        try {
            String decrypt2 = aes.decryptStr(decryptMap1.get("data").toString());
            Map<String,Object> decryptMap2 = (Map<String, Object>) JsonUtils.parseObject(decrypt2, Map.class);
            username = decryptMap2.get("username").toString();
        } catch (Exception e) {
            log.error("Parse user info failed.", e);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
            
        // 4. 根据 lgh_user (username) 查询 user_id
        GhCsSsoDO ssoUser = ghCsSsoMapper.selectByLghUser(username);
        if (ssoUser == null) {
            log.error("SSO user not found for lgh_user: {}", username);
            throw exception(USER_NOT_EXISTS);
        }
        
        // 5. 获取用户信息
        AdminUserDO user = userService.getUser(ssoUser.getUserId());
        if (user == null) {
            log.error("Admin user not found for id: {}", ssoUser.getUserId());
            throw exception(USER_NOT_EXISTS);
        }

        // 6. 创建 Token 令牌，记录登录日志
        return createTokenAfterLoginSuccess(user.getId(), user.getUsername(), LoginLogTypeEnum.LOGIN_SOCIAL);
    }

    private AuthLoginRespVO createTokenAfterLoginSuccess(Long userId, String username, LoginLogTypeEnum logType) {
        // 插入登陆日志
        createLoginLog(userId, username, logType, LoginResultEnum.SUCCESS);
        // 创建访问令牌
        OAuth2AccessTokenDO accessTokenDO = oauth2TokenService.createAccessToken(userId, getUserType().getValue(),
                OAuth2ClientConstants.CLIENT_ID_DEFAULT, null);
        // 构建返回结果
        return BeanUtils.toBean(accessTokenDO, AuthLoginRespVO.class);
    }

    private void createLoginLog(Long userId, String username,
                                LoginLogTypeEnum logTypeEnum, LoginResultEnum loginResult) {
        // 插入登录日志
        LoginLogCreateReqDTO reqDTO = new LoginLogCreateReqDTO();
        reqDTO.setLogType(logTypeEnum.getType());
        reqDTO.setTraceId(TracerUtils.getTraceId());
        reqDTO.setUserId(userId);
        reqDTO.setUserType(getUserType().getValue());
        reqDTO.setUsername(username);
        reqDTO.setUserAgent(ServletUtils.getUserAgent());
        reqDTO.setUserIp(ServletUtils.getClientIP());
        reqDTO.setResult(loginResult.getResult());
        loginLogService.createLoginLog(reqDTO);
        // 更新最后登录时间
        if (userId != null && Objects.equals(LoginResultEnum.SUCCESS.getResult(), loginResult.getResult())) {
            userService.updateUserLogin(userId, ServletUtils.getClientIP());
        }
    }

    private UserTypeEnum getUserType() {
        return UserTypeEnum.ADMIN;
    }
}
