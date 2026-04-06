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
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.auth.GhCsSsoDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.jhdwyds.JhdwydsDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.auth.GhCsSsoMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.qx.sfxx.SystemUserSfxxMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.jhdwyds.JhdwydsMapper;
import cn.iocoder.yudao.module.lghjft.enums.logger.LoginTypeEnum;
import cn.iocoder.yudao.module.lghjft.framework.auth.config.LghJftAuthProperties;
import cn.iocoder.yudao.module.lghjft.service.dx.DxfwService;
import cn.iocoder.yudao.module.lghjft.service.hjgl.Xwgl.GhHjService;
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
import cn.iocoder.yudao.module.system.service.dept.DeptService;
import cn.iocoder.yudao.module.system.service.logger.LoginLogService;
import cn.iocoder.yudao.module.system.service.oauth2.OAuth2ClientService;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private AdminUserMapper adminUserMapper;
    @Resource
    private SystemUserSfxxMapper systemUserSfxxMapper;
    @Resource
    private AdminUserService userService;
    @Resource
    private DeptService deptService;
    @Resource
    private LoginLogService loginLogService;
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
    private NsrxxService nsrxxService;
    @Resource
    private DxfwService dxfwService;
    @Resource
    private GhHjService ghHjService;
    @Resource
    private JhdwydsMapper jhdwydsMapper;

    private static String generateAccessToken() {
        return IdUtil.fastSimpleUUID();
    }

    private static String generateRefreshToken() {
        return IdUtil.fastSimpleUUID();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AuthorizeResVO login(AuthorizeReqVO reqVO) {
        // 统一登录查询 system_users（匹配 username / mobile / email / shxydm）
        AdminUserDO userDO = adminUserMapper.selectByLogin(reqVO.getDlzh());
        if (userDO == null) {
            throw exception(USER_NOT_EXISTS);
        }

        // 验证密码
        if (!userService.isPasswordMatch(reqVO.getPassword(), userDO.getPassword())) {
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        // 验证状态（0=正常，1=停用）
        if (Objects.equals(userDO.getStatus(), 1)) {
            throw exception(AUTH_LOGIN_USER_DISABLED);
        }

        // 判断实际登录方式
        String dlzh = reqVO.getDlzh();
        Integer dlfs;
        if (dlzh.equals(userDO.getUsername())) {
            dlfs = LoginTypeEnum.LOGIN_USERNAME.getType();
        } else if (dlzh.equals(userDO.getMobile())) {
            dlfs = LoginTypeEnum.LOGIN_MOBILE.getType();
        } else if (dlzh.equals(userDO.getEmail())) {
            dlfs = LoginTypeEnum.LOGIN_EMAIL.getType();
        } else if (dlzh.equals(userDO.getShxydm())) {
            dlfs = LoginTypeEnum.LOGIN_SHXYDM.getType();
        } else {
            dlfs = LoginTypeEnum.LOGIN_USERNAME.getType();
        }

        AuthorizeResVO resVO = buildAdminUserResVO(userDO, dlfs, dlzh, reqVO.getYhlx());
        createTokenAfterLoginSuccess(resVO);
        return resVO;
    }

    @Override
    public void sendSmsCode(String lxdh, Integer yhlx) {
        dxfwService.fsdlyzm(lxdh, yhlx);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AuthorizeResVO smsLogin(DxdlReqVO reqVO) {
        dxfwService.jydlyzm(reqVO.getLxdh(), reqVO.getYzm(), reqVO.getYhlx());

        AdminUserDO userDO = adminUserMapper.selectByMobile(reqVO.getLxdh());
        if (userDO == null) {
            throw exception(USER_NOT_EXISTS);
        }
        if (Objects.equals(userDO.getStatus(), 1)) {
            throw exception(AUTH_LOGIN_USER_DISABLED);
        }

        AuthorizeResVO resVO = buildAdminUserResVO(
                userDO, LoginTypeEnum.LOGIN_SMS.getType(), reqVO.getLxdh(), reqVO.getYhlx());
        return createTokenAfterLoginSuccess(resVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AuthorizeResVO loginAuthCode(AuthorizeLghReqVO reqVO) {
        // 1. 解密 authCode
        AES aes = new AES(Mode.ECB, Padding.PKCS5Padding,
                lghJftAuthProperties.getDecryptKey().getBytes(StandardCharsets.UTF_8));
        String token;
        try {
            token = aes.decryptStr(Base64.getUrlDecoder().decode(reqVO.getAuthCode()));
            log.info("decrypt auth code success. authCode: {}", reqVO.getAuthCode());
        } catch (Exception e) {
            log.error("Decrypt auth code failed. authCode: {}", reqVO.getAuthCode(), e);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }

        // 2. 调用第三方 SSO 网关获取用户信息
        String requestId = UUID.randomUUID().toString(true);
        String timestamp = String.valueOf(System.currentTimeMillis());
        String sign = MD5.create().digestHex(
                "appId=" + lghJftAuthProperties.getAppId()
                        + "&requestId=" + requestId
                        + "&timestamp=" + timestamp
                        + "&secret=" + lghJftAuthProperties.getAppSecret());

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
                "token", Base64.getUrlEncoder().encodeToString(
                        aes.encrypt(JsonUtils.toJsonString(params).getBytes(StandardCharsets.UTF_8)))
        );

        String response;
        try {
            response = HttpUtils.post(lghJftAuthProperties.getGatewayUrl(), headers, null);
        } catch (Exception e) {
            log.error("Call SSO gateway failed. url: {}", lghJftAuthProperties.getGatewayUrl(), e);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }

        // 3. 解析响应（第一层解密）
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

        // 4. 第二层解密，提取 username
        String username;
        try {
            String decrypt2 = aes.decryptStr(decryptMap1.get("data").toString());
            Map<String, Object> decryptMap2 = (Map<String, Object>) JsonUtils.parseObject(decrypt2, Map.class);
            username = decryptMap2.get("username").toString();
        } catch (Exception e) {
            log.error("Parse user info failed.", e);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }

        // 5. 通过 SSO 映射表找到本系统用户
        GhCsSsoDO ssoUser = ghCsSsoMapper.selectByLghUser(username);
        if (ssoUser == null) {
            log.error("SSO user not found for lgh_user: {}", username);
            throw exception(USER_NOT_EXISTS);
        }

        // 6. 查询 system_users
        AdminUserDO user = userService.getUser(ssoUser.getUserId());
        if (user == null) {
            log.error("Admin user not found for id: {}", ssoUser.getUserId());
            throw exception(USER_NOT_EXISTS);
        }

        // 7. 构建响应并创建 Token
        AuthorizeResVO authorizeResVO = buildAdminUserResVO(
                user, LoginTypeEnum.LOGIN_SOCIAL.getType(), user.getUsername(), UserTypeEnum.ADMIN.getValue());
        return createTokenAfterLoginSuccess(authorizeResVO);
    }

    @Override
    public List<AuthorizeResVO.DwQxSf> getDwQxSfList(Long dlzhId) {
        SfxxPageReqVO sfxxPageReqVO = new SfxxPageReqVO();
        sfxxPageReqVO.setDlzhId(dlzhId);
        sfxxPageReqVO.setPageNo(0);
        sfxxPageReqVO.setPageSize(Integer.MAX_VALUE);

        AdminUserDO userDO = adminUserMapper.selectById(dlzhId);
        return systemUserSfxxMapper.selectPage(sfxxPageReqVO).getList().stream()
                .filter(sfxx -> !Boolean.TRUE.equals(sfxx.getDeleted()) && sfxx.getStatus() == 1)
                .map(sfxx -> {
                    AuthorizeResVO.DwQxSf dwQxSf = new AuthorizeResVO.DwQxSf();
                    dwQxSf.setDjxh(sfxx.getDjxh());
                    dwQxSf.setSflx(sfxx.getSflx());
                    dwQxSf.setQxlx(sfxx.getQxlx());
                    dwQxSf.setSfxxId(sfxx.getId());

                    // 填充人员信息
                    if (userDO != null) {
                        dwQxSf.setRyxm(userDO.getNickname());
                        dwQxSf.setLxdh(userDO.getMobile());
                    }

                    // 单位名称 + dept_id 以 gh_hj / jhdwyds 为准
                    Long sourceDeptId = null;
                    GhHjVO ghHjVO = ghHjService.selectGhHjBydjxh(sfxx.getDjxh());
                    if (ghHjVO != null) {
                        dwQxSf.setDwmc(ghHjVO.getNsrmc());
                        if (StringUtils.isNotBlank(ghHjVO.getDeptId())) {
                            try {
                                sourceDeptId = Long.valueOf(ghHjVO.getDeptId());
                            } catch (NumberFormatException ignored) {
                            }
                        }
                    } else {
                        // gh_hj 查不到 → 基层工会，从 jhdwyds 查（djxh = nvl(ghshxydm, shxydm)）
                        JhdwydsDO jhdwyds = jhdwydsMapper.selectOne(new LambdaQueryWrapper<JhdwydsDO>()
                                .eq(JhdwydsDO::getGhshxydm, sfxx.getDjxh())
                                .last("LIMIT 1"));
                        if (jhdwyds == null) {
                            jhdwyds = jhdwydsMapper.selectOne(new LambdaQueryWrapper<JhdwydsDO>()
                                    .eq(JhdwydsDO::getShxydm, sfxx.getDjxh())
                                    .last("LIMIT 1"));
                        }
                        if (jhdwyds != null) {
                            dwQxSf.setDwmc(jhdwyds.getGhmc());
                            if (StringUtils.isNotBlank(jhdwyds.getDeptId())) {
                                try {
                                    sourceDeptId = Long.valueOf(jhdwyds.getDeptId());
                                } catch (NumberFormatException ignored) {
                                }
                            }
                        }
                    }

                    // 部门信息以来源表 dept_id 为准
                    if (sourceDeptId != null) {
                        DeptDO deptDO = deptMapper.selectById(sourceDeptId);
                        if (deptDO != null) {
                            dwQxSf.setZgghId(deptDO.getId());
                            dwQxSf.setZgghMc(deptDO.getName());
                            DeptDO sjDeptDO = deptMapper.selectById(deptDO.getParentId());
                            if (sjDeptDO != null) {
                                dwQxSf.setSjZgghId(sjDeptDO.getId());
                                dwQxSf.setSjZgghMc(sjDeptDO.getName());
                            }
                        }
                    }

                    return dwQxSf;
                }).toList();
    }

    private AuthorizeResVO createTokenAfterLoginSuccess(AuthorizeResVO resVO) {
        createLoginLog(resVO.getUserId(), resVO, LoginResultEnum.SUCCESS);
        OAuth2AccessTokenDO accessTokenDO = createAccessToken(
                resVO, resVO.getYhlx(), OAuth2ClientConstants.CLIENT_ID_DEFAULT, null);
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
        resVO.setDwQxSf(getDwQxSfList(user.getId()));
        return resVO;
    }

    private void createLoginLog(Long userId, AuthorizeResVO authorizeResVO, LoginResultEnum loginResult) {
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
        // 更新 system_users 最后登录时间
        if (userId != null && Objects.equals(LoginResultEnum.SUCCESS.getResult(), loginResult.getResult())) {
            AdminUserDO update = new AdminUserDO();
            update.setId(userId);
            update.setLoginIp(ServletUtils.getClientIP());
            update.setLoginDate(LocalDateTime.now());
            adminUserMapper.updateById(update);
        }
    }

    private UserTypeEnum getUserType() {
        return UserTypeEnum.ADMIN;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OAuth2AccessTokenDO createAccessToken(AuthorizeResVO resVO, Integer userType, String clientId, List<String> scopes) {
        OAuth2ClientDO clientDO = oauth2ClientService.validOAuthClientFromCache(clientId);
        OAuth2RefreshTokenDO refreshTokenDO = createOAuth2RefreshToken(resVO.getUserId(), userType, clientDO, scopes);
        return createOAuth2AccessToken(refreshTokenDO, clientDO, resVO);
    }

    private OAuth2AccessTokenDO createOAuth2AccessToken(OAuth2RefreshTokenDO refreshTokenDO,
                                                        OAuth2ClientDO clientDO, AuthorizeResVO resVO) {
        OAuth2AccessTokenDO accessTokenDO = new OAuth2AccessTokenDO()
                .setAccessToken(generateAccessToken())
                .setUserId(refreshTokenDO.getUserId())
                .setUserType(refreshTokenDO.getUserType())
                .setUserInfo(buildUserInfo(resVO))
                .setClientId(clientDO.getClientId())
                .setScopes(refreshTokenDO.getScopes())
                .setRefreshToken(refreshTokenDO.getRefreshToken())
                .setExpiresTime(LocalDateTime.now().plusSeconds(clientDO.getAccessTokenValiditySeconds()));

        Long tenantId = refreshTokenDO.getTenantId();
        if (tenantId == null) {
            tenantId = TenantContextHolder.getTenantId();
        }
        accessTokenDO.setTenantId(tenantId);
        oauth2AccessTokenMapper.insert(accessTokenDO);
        oauth2AccessTokenRedisDAO.set(accessTokenDO);
        return accessTokenDO;
    }

    private OAuth2RefreshTokenDO createOAuth2RefreshToken(Long userId, Integer userType,
                                                          OAuth2ClientDO clientDO, List<String> scopes) {
        OAuth2RefreshTokenDO refreshToken = new OAuth2RefreshTokenDO()
                .setRefreshToken(generateRefreshToken())
                .setUserId(userId)
                .setUserType(userType)
                .setClientId(clientDO.getClientId())
                .setScopes(scopes)
                .setExpiresTime(LocalDateTime.now().plusSeconds(clientDO.getRefreshTokenValiditySeconds()));
        refreshToken.setTenantId(TenantContextHolder.getTenantId());
        oauth2RefreshTokenMapper.insert(refreshToken);
        return refreshToken;
    }

    private Map<String, String> buildUserInfo(AuthorizeResVO resVO) {
        return MapUtil.builder(LoginUser.INFO_KEY_NICKNAME, resVO.getYhnc())
                .put(LoginUser.INFO_KEY_DEPT_ID, String.valueOf(resVO.getQxbmId()))
                .build();
    }
}
