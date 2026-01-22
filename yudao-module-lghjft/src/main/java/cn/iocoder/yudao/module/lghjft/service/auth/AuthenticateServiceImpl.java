package cn.iocoder.yudao.module.lghjft.service.auth;

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
import cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo.AuthorizeReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo.AuthorizeResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.auth.GhCsSsoDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.auth.GhQxDlzhxxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.auth.DwQxSfMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.auth.GhCsSsoMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.auth.GhQxDlzhxxMapper;
import cn.iocoder.yudao.module.lghjft.enums.logger.LoginTypeEnum;
import cn.iocoder.yudao.module.lghjft.framework.auth.config.LghJftAuthProperties;
import cn.iocoder.yudao.module.system.api.logger.dto.LoginLogCreateReqDTO;
import cn.iocoder.yudao.module.system.controller.admin.auth.vo.AuthLoginReqVO;
import cn.iocoder.yudao.module.system.controller.admin.auth.vo.AuthLoginRespVO;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.enums.logger.LoginResultEnum;
import cn.iocoder.yudao.module.system.enums.oauth2.OAuth2ClientConstants;
import cn.iocoder.yudao.module.system.service.auth.AdminAuthService;
import cn.iocoder.yudao.module.system.service.dept.DeptService;
import cn.iocoder.yudao.module.system.service.logger.LoginLogService;
import cn.iocoder.yudao.module.system.service.oauth2.OAuth2ClientService;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

@Service
@Slf4j
public class AuthenticateServiceImpl implements AuthenticateService {

    @Resource
    private LghJftAuthProperties lghJftAuthProperties;
    @Resource
    private GhCsSsoMapper ghCsSsoMapper;
    @Resource
    private GhQxDlzhxxMapper ghQxDlzhxxMapper;
    @Resource
    private DwQxSfMapper dwQxSfMapper;
    @Resource
    private AdminUserService userService;
    @Resource
    private AdminAuthService authService;
    @Resource
    private DeptService deptService;
    @Resource
    private LoginLogService loginLogService;
    @Resource
    private LghOAuth2TokenService lghOAuth2TokenService;
    @Resource
    private OAuth2ClientService oauth2ClientService;

    @Override
    public AuthorizeResVO login(AuthorizeReqVO reqVO) {
        // 1. 针对 username 登录形式仅使用系统默认验证
        AuthorizeResVO authorizeResVO = new AuthorizeResVO();
        if (StringUtils.isNotBlank(reqVO.getYhzh())) {
            AuthLoginReqVO systemLoginReq = AuthLoginReqVO.builder()
                    .username(reqVO.getYhzh())
                    .password(reqVO.getPassword())
                    .build();
            AuthLoginRespVO authLoginRespVO = authService.login(systemLoginReq);
            BeanUtils.copyProperties(authLoginRespVO, authorizeResVO);
            authorizeResVO.setYhzh(authLoginRespVO.getUserName()); // 将系统用户名设置到新业务字段
            authorizeResVO.setYhnc(authLoginRespVO.getNickName());
            authorizeResVO.setTxdz(authLoginRespVO.getAvatar());
            authorizeResVO.setDlzh(reqVO.getYhzh());
            DeptDO deptDO = deptService.getDept(authLoginRespVO.getDeptId());
            authorizeResVO.setQxbmId(deptDO.getId());
            authorizeResVO.setQxbmMc(deptDO.getName());
            DeptDO sjDeptDO = deptService.getDept(deptDO.getParentId());
            if (!Objects.isNull(sjDeptDO)) {
                authorizeResVO.setSjQxbmId(sjDeptDO.getId());
                authorizeResVO.setSjQxbmMc(sjDeptDO.getName());
            }

        } else {

            // 2. 手机、邮箱、社会信用代码采用新逻辑登录
            GhQxDlzhxxDO userDO = null;
            if (StringUtils.isNotBlank(reqVO.getLxdh())) {
                userDO = ghQxDlzhxxMapper.selectByLxdh(reqVO.getLxdh());
                authorizeResVO.setDlzh(reqVO.getLxdh());
                authorizeResVO.setLoginType(LoginTypeEnum.LOGIN_MOBILE.getType());
            } else if (StringUtils.isNotBlank(reqVO.getYhyx())) {
                userDO = ghQxDlzhxxMapper.selectByYhyx(reqVO.getYhyx());
                authorizeResVO.setDlzh(reqVO.getYhyx());
                authorizeResVO.setLoginType(LoginTypeEnum.LOGIN_EMAIL.getType());
            } else if (StringUtils.isNotBlank(reqVO.getShxydm())) {
                userDO = ghQxDlzhxxMapper.selectByShxydm(reqVO.getShxydm());
                authorizeResVO.setDlzh(reqVO.getShxydm());
                authorizeResVO.setLoginType(LoginTypeEnum.LOGIN_SHXYDM.getType());
            }

            // 3. 如果 gh_qx_dlzhxx 找到用户，则尝试使用该表信息登录
            if (userDO != null) {
                // 3.1 校验密码
                if (!userService.isPasswordMatch(reqVO.getPassword(), userDO.getPassword())) {
                    throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
                }

                // 3.2 校验状态
                if (Objects.equals(userDO.getStatus(), 1)) { // 1 是停用
                    throw exception(AUTH_LOGIN_USER_DISABLED);
                }

                // 3.3 更新登录信息到业务表（最后登录时间/IP）
                userDO.setLoginIp(ServletUtils.getClientIP());
                userDO.setLoginDate(LocalDateTime.now());
                BeanUtils.copyProperties(userDO, authorizeResVO);
                // 3.4 创建 Token 令牌，记录登录日志
                authorizeResVO = createTokenAfterLoginSuccess(userDO, authorizeResVO);
            } else {
                // 如果既没有在 gh_qx_dlzhxx 找到，或者所有方式都失败
                throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
            }
        }

        // 获取单位权限身份列表
        authorizeResVO.setDwQxSf(getDwQxSfList(authorizeResVO.getDlzh()));

        return authorizeResVO;
    }

    @Override
    public AuthorizeResVO loginAuthCode(AuthorizeLghReqVO reqVO) {
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

        Map<String, String> headers = Map.of(
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

        Map<String, Object> decryptMap1 = (Map<String, Object>) JsonUtils.parseObject(decrypt1, Map.class);
        if (decryptMap1 == null || !Integer.valueOf(0).equals(decryptMap1.get("code"))) {
            log.warn("SSO login failed. response code: {}", decryptMap1 != null ? decryptMap1.get("code") : "null");
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }

        String username;
        try {
            String decrypt2 = aes.decryptStr(decryptMap1.get("data").toString());
            Map<String, Object> decryptMap2 = (Map<String, Object>) JsonUtils.parseObject(decrypt2, Map.class);
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

        // 7. 获取新表用户信息
        GhQxDlzhxxDO userDO = ghQxDlzhxxMapper.selectByYhzh(user.getUsername());
        if (userDO == null) {
            throw exception(USER_NOT_EXISTS);
        }
        AuthorizeResVO authorizeResVO = new AuthorizeResVO();
        BeanUtils.copyProperties(userDO, authorizeResVO);
        authorizeResVO.setDlzh(String.valueOf(userDO.getId()));
        authorizeResVO.setLoginType(LoginTypeEnum.LOGIN_SOCIAL.getType());

        // 8. 创建 Token 令牌，记录登录日志
        authorizeResVO = createTokenAfterLoginSuccess(userDO, authorizeResVO);

        // 获取单位权限身份列表
        authorizeResVO.setDwQxSf(getDwQxSfList(authorizeResVO.getDlzh()));

        return authorizeResVO;
    }

    @Override
    public List<AuthorizeResVO.DwQxSf> getDwQxSfList(String dlzh) {
        List<AuthorizeResVO.DwQxSf> dwQxSfList = dwQxSfMapper.selectDwQxSfListByDlzh(dlzh);
        dwQxSfList.forEach(dwQxSf -> {
            DeptDO deptDO = deptService.getDept(dwQxSf.getZgghId());
            dwQxSf.setZgghMc(deptDO.getName());
            DeptDO sjDeptDO = deptService.getDept(deptDO.getParentId());
            dwQxSf.setSjZgghId(sjDeptDO.getId());
            dwQxSf.setSjZgghMc(sjDeptDO.getName());
        });
        return dwQxSfList;
    }

    private AuthorizeResVO createTokenAfterLoginSuccess(GhQxDlzhxxDO user, AuthorizeResVO authorizeResVO) {
        // 插入登陆日志
        createLoginLog(user.getId(), authorizeResVO, LoginResultEnum.SUCCESS);
        // 创建访问令牌
        OAuth2AccessTokenDO accessTokenDO = lghOAuth2TokenService.createAccessToken(user, getUserType().getValue(),
                OAuth2ClientConstants.CLIENT_ID_DEFAULT, null);
        // 构建返回结果
        BeanUtils.copyProperties(accessTokenDO, authorizeResVO);
        return authorizeResVO;
    }

    private void createLoginLog(Long userId, AuthorizeResVO authorizeResVO, LoginResultEnum loginResult) {
        // 插入登录日志
        LoginLogCreateReqDTO reqDTO = new LoginLogCreateReqDTO();
        reqDTO.setLogType(authorizeResVO.getLoginType());
        reqDTO.setTraceId(TracerUtils.getTraceId());
        reqDTO.setUserId(userId);
        reqDTO.setUserType(getUserType().getValue());
        reqDTO.setUsername(authorizeResVO.getDlzh());
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

    @Override
    public OAuth2AccessTokenDO createAccessToken(GhQxDlzhxxDO user, Integer userType, String clientId, List<String> scopes) {
        return lghOAuth2TokenService.createAccessToken(user, userType, clientId, scopes);
    }

}
