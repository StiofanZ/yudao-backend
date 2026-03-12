package cn.iocoder.yudao.module.lghjft.service.auth;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
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
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.tenant.core.context.TenantContextHolder;
import cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo.AuthorizeLghReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo.AuthorizeReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo.AuthorizeResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo.DxdlReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.auth.GhCsSsoDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nsrxx.NsrxxDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.dlzh.GhQxDlzhDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.auth.DwQxSfMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.auth.GhCsSsoMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.qx.dlzh.GhQxDlzhMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.qx.sfxx.GhQxSfxxMapper;
import cn.iocoder.yudao.module.lghjft.enums.logger.LoginTypeEnum;
import cn.iocoder.yudao.module.lghjft.framework.auth.config.LghJftAuthProperties;
import cn.iocoder.yudao.module.lghjft.service.dx.DxfwService;
import cn.iocoder.yudao.module.lghjft.service.nsrxx.NsrxxService;
import cn.iocoder.yudao.module.system.api.logger.dto.LoginLogCreateReqDTO;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import cn.iocoder.yudao.module.system.dal.dataobject.oauth2.OAuth2ClientDO;
import cn.iocoder.yudao.module.system.dal.dataobject.oauth2.OAuth2RefreshTokenDO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.dal.mysql.dept.DeptMapper;
import cn.iocoder.yudao.module.system.dal.mysql.oauth2.OAuth2AccessTokenMapper;
import cn.iocoder.yudao.module.system.dal.mysql.oauth2.OAuth2RefreshTokenMapper;
import cn.iocoder.yudao.module.system.dal.mysql.user.AdminUserMapper;
import cn.iocoder.yudao.module.system.dal.redis.oauth2.OAuth2AccessTokenRedisDAO;
import cn.iocoder.yudao.module.system.enums.logger.LoginResultEnum;
import cn.iocoder.yudao.module.system.enums.oauth2.OAuth2ClientConstants;
import cn.iocoder.yudao.module.system.service.auth.AdminAuthService;
import cn.iocoder.yudao.module.system.service.dept.DeptService;
import cn.iocoder.yudao.module.system.service.logger.LoginLogService;
import cn.iocoder.yudao.module.system.service.oauth2.OAuth2ClientService;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import cn.iocoder.yudao.module.system.service.user.AdminUserServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
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
    private GhQxDlzhMapper ghQxDlzhMapper;
    @Resource
    private DwQxSfMapper dwQxSfMapper;
    @Resource
    private AdminUserService userService;
    @Resource
    private DeptService deptService;
    @Resource
    private LoginLogService loginLogService;
    @Resource
    private AdminAuthService adminAuthService;
    @Resource
    private AdminUserServiceImpl adminUserService;
    @Resource
    private OAuth2AccessTokenMapper oauth2AccessTokenMapper;
    @Resource
    private OAuth2RefreshTokenMapper oauth2RefreshTokenMapper;
    @Resource
    private OAuth2AccessTokenRedisDAO oauth2AccessTokenRedisDAO;
    @Resource
    private OAuth2ClientService oauth2ClientService;
    @Resource
    private DeptMapper deptMapper;
    @Resource
    private GhQxSfxxMapper ghQxSfxxMapper;
    @Resource
    private NsrxxService nsrxxService;
    @Resource
    private DxfwService dxfwService;
    @Resource
    private AdminUserMapper adminUserMapper;

    private static String generateAccessToken() {
        return IdUtil.fastSimpleUUID();
    }

    private static String generateRefreshToken() {
        return IdUtil.fastSimpleUUID();
    }

    @Override
    public AuthorizeResVO login(AuthorizeReqVO reqVO) {
        AuthorizeResVO resVO = new AuthorizeResVO();
        resVO.setYhlx(reqVO.getYhlx());
        GhQxDlzhDO userDO = ghQxDlzhMapper.selectOne(reqVO.getLxdh(), reqVO.getYhzh(), reqVO.getShxydm(), reqVO.getYhyx());

        if (Objects.isNull(userDO)) {
            // 使用账号密码，兼容原系统登录
            AdminUserDO user = adminAuthService.authenticate(reqVO.getYhzh(), reqVO.getPassword());
            adminUserService.updateUserLogin(user.getId(), ServletUtils.getClientIP());
            resVO = buildAdminUserResVO(user, hqAdminDlfs(reqVO.getYhzh(), user), reqVO.getYhzh(), reqVO.getYhlx());
        } else {
            if (!userService.isPasswordMatch(reqVO.getPassword(), userDO.getPassword())) {
                throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
            }
            // 3.2 校验状态
            if (Objects.equals(userDO.getStatus(), 1)) { // 1 是停用
                throw exception(AUTH_LOGIN_USER_DISABLED);
            }

            if (Objects.nonNull(userDO.getYhzh()) && userDO.getYhzh().equals(reqVO.getYhzh())) {
                resVO = buildDlzhResVO(userDO, LoginTypeEnum.LOGIN_USERNAME.getType(), reqVO.getYhzh(), reqVO.getYhlx());
            } else if (Objects.nonNull(userDO.getLxdh()) && userDO.getLxdh().equals(reqVO.getLxdh())) {
                resVO = buildDlzhResVO(userDO, LoginTypeEnum.LOGIN_MOBILE.getType(), reqVO.getLxdh(), reqVO.getYhlx());
            } else if (Objects.nonNull(userDO.getYhyx()) && userDO.getYhyx().equals(reqVO.getYhyx())) {
                resVO = buildDlzhResVO(userDO, LoginTypeEnum.LOGIN_EMAIL.getType(), reqVO.getYhyx(), reqVO.getYhlx());
            } else if (Objects.nonNull(userDO.getShxydm()) && userDO.getShxydm().equals(reqVO.getShxydm())) {
                resVO = buildDlzhResVO(userDO, LoginTypeEnum.LOGIN_SHXYDM.getType(), reqVO.getShxydm(), reqVO.getYhlx());
            } else {
                throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
            }
        }
        createTokenAfterLoginSuccess(resVO);

        return resVO;
    }

    @Override
    public void sendSmsCode(String lxdh, Integer yhlx) {
        dxfwService.fsdlyzm(lxdh, yhlx);
    }

    @Override
    public AuthorizeResVO smsLogin(DxdlReqVO reqVO) {
        dxfwService.jydlyzm(reqVO.getLxdh(), reqVO.getYzm(), reqVO.getYhlx());
        GhQxDlzhDO userDO = ghQxDlzhMapper.selectOne(reqVO.getLxdh(), null, null, null);
        AuthorizeResVO resVO;
        if (userDO != null) {
            if (Objects.equals(userDO.getStatus(), 1)) {
                throw exception(AUTH_LOGIN_USER_DISABLED);
            }
            resVO = buildDlzhResVO(userDO, LoginTypeEnum.LOGIN_SMS.getType(), reqVO.getLxdh(), reqVO.getYhlx());
        } else {
            AdminUserDO adminUserDO = adminUserMapper.selectByMobile(reqVO.getLxdh());
            if (adminUserDO == null) {
                throw exception(USER_NOT_EXISTS);
            }
            if (Objects.equals(adminUserDO.getStatus(), 1)) {
                throw exception(AUTH_LOGIN_USER_DISABLED);
            }
            adminUserService.updateUserLogin(adminUserDO.getId(), ServletUtils.getClientIP());
            resVO = buildAdminUserResVO(adminUserDO, LoginTypeEnum.LOGIN_SMS.getType(), reqVO.getLxdh(), reqVO.getYhlx());
        }
        return createTokenAfterLoginSuccess(resVO);
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
        GhQxDlzhDO userDO = ghQxDlzhMapper.selectOne(GhQxDlzhDO::getYhzh, user.getUsername());
        if (userDO == null) {
            throw exception(USER_NOT_EXISTS);
        }
        AuthorizeResVO authorizeResVO = new AuthorizeResVO();
        BeanUtils.copyProperties(userDO, authorizeResVO);
        authorizeResVO.setDlzh(userDO.getYhzh());
        authorizeResVO.setDlfs(LoginTypeEnum.LOGIN_SOCIAL.getType());

        // 8. 创建 Token 令牌，记录登录日志
        authorizeResVO = createTokenAfterLoginSuccess(authorizeResVO);

        // 获取单位权限身份列表
        authorizeResVO.setDwQxSf(getDwQxSfList(authorizeResVO.getUserId()));

        return authorizeResVO;
    }

    @Override
    public List<AuthorizeResVO.DwQxSf> getDwQxSfList(Long dlzhId) {
        SfxxPageReqVO sfxxPageReqVO = new SfxxPageReqVO();
        sfxxPageReqVO.setDlzhId(dlzhId);
        sfxxPageReqVO.setPageNo(0);
        sfxxPageReqVO.setPageSize(Integer.MAX_VALUE);
        GhQxDlzhDO ghQxDlzhDO = ghQxDlzhMapper.selectById(dlzhId);
        return ghQxSfxxMapper.selectPage(sfxxPageReqVO).getList().stream()
                .filter(sfxx -> !Boolean.TRUE.equals(sfxx.getDeleted()) && sfxx.getStatus() == 0)
                .map(sfxx -> {
                    AuthorizeResVO.DwQxSf dwQxSf = new AuthorizeResVO.DwQxSf();
                    dwQxSf.setDjxh(sfxx.getDjxh());
                    dwQxSf.setSflx(sfxx.getSflx());
                    dwQxSf.setQxlx(sfxx.getQxlx());
                    NsrxxDO nsrxxDO = nsrxxService.getNsrxx(sfxx.getDjxh());
                    dwQxSf.setRyxm(ghQxDlzhDO.getYhxm());
                    dwQxSf.setDwmc(nsrxxDO.getNsrmc());
                    dwQxSf.setLxdh(ghQxDlzhDO.getLxdh());
                    DeptDO deptDO = deptMapper.selectById(sfxx.getDeptId());
                    dwQxSf.setZgghId(deptDO.getId());
                    dwQxSf.setZgghMc(deptDO.getName());
                    DeptDO sjDeptDO = deptMapper.selectById(deptDO.getParentId());
                    if (sjDeptDO != null) {
                        dwQxSf.setSjZgghId(sjDeptDO.getId());
                        dwQxSf.setSjZgghMc(sjDeptDO.getName());
                    }
                    dwQxSf.setSfxxId(sfxx.getId());
                    return dwQxSf;
                }).toList();
    }

    private AuthorizeResVO createTokenAfterLoginSuccess(AuthorizeResVO resVO) {
        // 插入登陆日志
        createLoginLog(resVO.getUserId(), resVO, LoginResultEnum.SUCCESS);
        // 创建访问令牌
        OAuth2AccessTokenDO accessTokenDO = createAccessToken(resVO, resVO.getYhlx(),
                OAuth2ClientConstants.CLIENT_ID_DEFAULT, null);
        // 构建返回结果
        BeanUtils.copyProperties(accessTokenDO, resVO);
        return resVO;
    }

    private AuthorizeResVO buildAdminUserResVO(AdminUserDO user, Integer dlfs, String dlzh, Integer yhlx) {
        AuthorizeResVO resVO = new AuthorizeResVO();
        resVO.setUserId(user.getId());
        resVO.setYhzh(user.getUsername());
        resVO.setYhyx(user.getEmail());
        resVO.setYhnc(user.getNickname());
        resVO.setTxdz(user.getAvatar());
        resVO.setDlfs(dlfs);
        resVO.setDlzh(dlzh);
        resVO.setYhlx(yhlx);
        DeptDO deptDO = deptService.getDept(user.getDeptId());
        if (deptDO != null) {
            resVO.setQxbmId(deptDO.getId());
            resVO.setQxbmMc(deptDO.getName());
            DeptDO sjDeptDO = deptService.getDept(deptDO.getParentId());
            if (sjDeptDO != null) {
                resVO.setSjQxbmId(sjDeptDO.getId());
                resVO.setSjQxbmMc(sjDeptDO.getName());
            }
        }
        return resVO;
    }

    private Integer hqAdminDlfs(String dlzh, AdminUserDO user) {
        if (user != null && Objects.equals(dlzh, user.getMobile())) {
            return LoginTypeEnum.LOGIN_MOBILE.getType();
        }
        return LoginTypeEnum.LOGIN_USERNAME.getType();
    }

    private AuthorizeResVO buildDlzhResVO(GhQxDlzhDO userDO, Integer dlfs, String dlzh, Integer yhlx) {
        AuthorizeResVO resVO = new AuthorizeResVO();
        BeanUtils.copyProperties(userDO, resVO);
        resVO.setYhnc(userDO.getYhxm());
        resVO.setYhyx(userDO.getYhyx());
        resVO.setTxdz(userDO.getTxdz());
        resVO.setUserId(userDO.getId());
        resVO.setDlfs(dlfs);
        resVO.setDlzh(dlzh);
        resVO.setYhlx(yhlx);
        resVO.setDwQxSf(getDwQxSfList(resVO.getUserId()));
        return resVO;
    }

    private void createLoginLog(Long userId, AuthorizeResVO authorizeResVO, LoginResultEnum loginResult) {
        // 插入登录日志
        LoginLogCreateReqDTO reqDTO = new LoginLogCreateReqDTO();
        reqDTO.setLogType(authorizeResVO.getDlfs());
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
            ghQxDlzhMapper.updateById(GhQxDlzhDO.builder()
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
    public OAuth2AccessTokenDO createAccessToken(AuthorizeResVO resVO, Integer userType, String clientId, List<String> scopes) {
        OAuth2ClientDO clientDO = oauth2ClientService.validOAuthClientFromCache(clientId);
        // 创建刷新令牌
        OAuth2RefreshTokenDO refreshTokenDO = createOAuth2RefreshToken(resVO.getUserId(), userType, clientDO, scopes);
        // 创建访问令牌
        return createOAuth2AccessToken(refreshTokenDO, clientDO, resVO);
    }

    private OAuth2AccessTokenDO createOAuth2AccessToken(OAuth2RefreshTokenDO refreshTokenDO, OAuth2ClientDO clientDO, AuthorizeResVO resVO) {
        OAuth2AccessTokenDO accessTokenDO = new OAuth2AccessTokenDO().setAccessToken(generateAccessToken())
                .setUserId(refreshTokenDO.getUserId()).setUserType(refreshTokenDO.getUserType())
                .setUserInfo(buildUserInfo(resVO))
                .setClientId(clientDO.getClientId()).setScopes(refreshTokenDO.getScopes())
                .setRefreshToken(refreshTokenDO.getRefreshToken())
                .setExpiresTime(LocalDateTime.now().plusSeconds(clientDO.getAccessTokenValiditySeconds()));

        Long tenantId = refreshTokenDO.getTenantId();
        if (tenantId == null) {
            tenantId = TenantContextHolder.getTenantId();
        }
        accessTokenDO.setTenantId(tenantId);
        oauth2AccessTokenMapper.insert(accessTokenDO);
        // 记录到 Redis 中
        oauth2AccessTokenRedisDAO.set(accessTokenDO);
        return accessTokenDO;
    }

    private OAuth2RefreshTokenDO createOAuth2RefreshToken(Long userId, Integer userType, OAuth2ClientDO clientDO, List<String> scopes) {
        OAuth2RefreshTokenDO refreshToken = new OAuth2RefreshTokenDO().setRefreshToken(generateRefreshToken())
                .setUserId(userId).setUserType(userType)
                .setClientId(clientDO.getClientId()).setScopes(scopes)
                .setExpiresTime(LocalDateTime.now().plusSeconds(clientDO.getRefreshTokenValiditySeconds()));

        Long tenantId = TenantContextHolder.getTenantId();
        refreshToken.setTenantId(tenantId);

        oauth2RefreshTokenMapper.insert(refreshToken);
        return refreshToken;
    }

    private Map<String, String> buildUserInfo(AuthorizeResVO resVO) {
        return MapUtil.builder(LoginUser.INFO_KEY_NICKNAME, resVO.getYhnc())
                .put(LoginUser.INFO_KEY_DEPT_ID, String.valueOf(resVO.getQxbmId())) // 陇工会用户暂无部门 ID
                .build();
    }

}
