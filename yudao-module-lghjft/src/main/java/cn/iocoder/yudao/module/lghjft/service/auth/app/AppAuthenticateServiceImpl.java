package cn.iocoder.yudao.module.lghjft.service.auth.app;

import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.crypto.symmetric.AES;
import cn.iocoder.yudao.framework.common.enums.UserTypeEnum;
import cn.iocoder.yudao.framework.common.util.http.HttpUtils;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.framework.common.util.monitor.TracerUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.common.util.servlet.ServletUtils;
import cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo.AuthorizeLghReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo.AuthorizeResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.auth.GhCsSsoDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.auth.GhQxDlzhxxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.auth.DwQxSfMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.auth.GhCsSsoMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.auth.GhQxDlzhxxMapper;
import cn.iocoder.yudao.module.lghjft.framework.auth.config.LghJftAppAuthProperties;
import cn.iocoder.yudao.module.lghjft.service.auth.LghOAuth2TokenService;
import cn.iocoder.yudao.module.system.api.logger.dto.LoginLogCreateReqDTO;
import cn.iocoder.yudao.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.enums.logger.LoginLogTypeEnum;
import cn.iocoder.yudao.module.system.enums.logger.LoginResultEnum;
import cn.iocoder.yudao.module.system.enums.oauth2.OAuth2ClientConstants;
import cn.iocoder.yudao.module.system.service.logger.LoginLogService;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.AUTH_LOGIN_BAD_CREDENTIALS;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.USER_NOT_EXISTS;

@Service
@Slf4j
public class AppAuthenticateServiceImpl implements  AppAuthenticateService{
    @Resource
    private GhCsSsoMapper ghCsSsoMapper;
    @Resource
    private GhQxDlzhxxMapper ghQxDlzhxxMapper;
    @Resource
    private DwQxSfMapper dwQxSfMapper;
    @Resource
    private LghJftAppAuthProperties lghJftAppAuthProperties;
    @Resource
    private AdminUserService userService;
    @Resource
    private LghOAuth2TokenService lghOAuth2TokenService;
    @Resource
    private LoginLogService loginLogService;

    @Override
    public AuthorizeResVO appLoginAuthCode(AuthorizeLghReqVO reqVO) {
        // 1. 解密 authCode
        String token;
        AES aes = new AES(Mode.ECB, Padding.PKCS5Padding, lghJftAppAuthProperties.getDecryptKey().getBytes(StandardCharsets.UTF_8));
        try {
            token = aes.decryptStr(Base64.getUrlDecoder().decode(reqVO.getAuthCode()));
            log.info("decrypt auth code success. authCode: {}", reqVO.getAuthCode());
        } catch (Exception e) {
            log.error("Decrypt auth code failed. authCode: {}", reqVO.getAuthCode(), e);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }

        // 2. 用第三方接口获取用户信息调

        //进行对参数的加密和封装
        String requestId = UUID.randomUUID().toString(true);
        String timestamp = String.valueOf(System.currentTimeMillis());

        String sign = MD5.create().digestHex("appId=" + lghJftAppAuthProperties.getAppId() +
                "&requestId=" + requestId + "&timestamp=" + timestamp + "&secret=" + lghJftAppAuthProperties.getAppSecret());

        Map<String, String> params = new LinkedHashMap<>();
        params.put("appId", lghJftAppAuthProperties.getAppId());
        params.put("platform", lghJftAppAuthProperties.getPlatform());
        params.put("requestId", requestId);
        params.put("timestamp", timestamp);
        params.put("sign", sign);
        params.put("token", token);

        //获取数据的请求头
        Map<String,String> headers = Map.of(
                "Content-Type", "application/json",
                "appId", lghJftAppAuthProperties.getAppId(),
                "token", Base64.getUrlEncoder().encodeToString(aes.encrypt(JsonUtils.toJsonString(params).getBytes(StandardCharsets.UTF_8)))
        );
//http请求获得加密的响应数据
        String response;
        try {
            response = HttpUtils.post(lghJftAppAuthProperties.getGatewayUrl(), headers, null);
        } catch (Exception e) {
            log.error("Call SSO gateway failed. url: {}", lghJftAppAuthProperties.getGatewayUrl(), e);
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

        // 4. 提取APP端用户信息（字段不同！）
       String auid;
        try {

            Map<String, Object> dataMap = (Map<String, Object>) decryptMap1.get("data");
            if (dataMap == null) {
                throw new RuntimeException("SSO返回data为空");
            }
            // APP端返回的是 auid，不是 username
            auid = dataMap.get("auid").toString();
//            name = dataMap.get("name").toString();
//            mobile = dataMap.get("mobile").toString();
            log.info("APP端解析用户信息成功.  auid: {}", auid);
        } catch (Exception e) {
            log.error("APP端解析用户信息失败.", e);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }


        // auid对应lgh_user字段
        GhCsSsoDO ssoUser = ghCsSsoMapper.selectByLghUser(auid);
        if (ssoUser == null) {
            log.error("APP端SSO用户未找到. name: {}", auid);

            throw exception(USER_NOT_EXISTS);
        }

        // 6. 获取用户信息
        AdminUserDO user = userService.getUser(ssoUser.getUserId());
        if (user == null) {
            log.error("APP端管理员用户未找到. id: {}", ssoUser.getUserId());
            throw exception(USER_NOT_EXISTS);
        }

        // 8. 获取新表用户信息
        GhQxDlzhxxDO userDO = ghQxDlzhxxMapper.selectByYhzh(user.getUsername());
        if (userDO == null) {
            throw exception(USER_NOT_EXISTS);
        }

        // 9. 创建 Token 令牌，记录登录日志
//        AuthorizeResVO authorizeResVO = createTokenAfterLoginSuccess(res, auid, LoginLogTypeEnum.LOGIN_SOCIAL);
        AuthorizeResVO authorizeResVO = new AuthorizeResVO();

        // 10. 获取单位权限身份列表
        authorizeResVO.setDwQxSf(dwQxSfMapper.selectDwQxSfListByDlzh(authorizeResVO.getDlzh()));
        return authorizeResVO;
    }

    private AuthorizeResVO createTokenAfterLoginSuccess(AuthorizeResVO resVO, String loginUsername, LoginLogTypeEnum logType) {
        // 插入登陆日志
        createLoginLog(resVO.getUserId(), loginUsername, logType, LoginResultEnum.SUCCESS);
        // 创建访问令牌
        OAuth2AccessTokenDO accessTokenDO = lghOAuth2TokenService.createAccessToken(resVO, getUserType().getValue(),
                OAuth2ClientConstants.CLIENT_ID_DEFAULT, null);
        // 构建返回结果
        BeanUtils.copyProperties(accessTokenDO, resVO);
        return resVO;
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
        // 更新最后登录时间 (更新新表)
        if (userId != null && Objects.equals(LoginResultEnum.SUCCESS.getResult(), loginResult.getResult())) {
            ghQxDlzhxxMapper.updateById(GhQxDlzhxxDO.builder()
                    .id(userId)
                    .loginIp(ServletUtils.getClientIP())
                    .loginDate(LocalDateTime.now())
                    .build());
        }
    }

    private UserTypeEnum getUserType() {
        return UserTypeEnum.ADMIN;
    }


}
