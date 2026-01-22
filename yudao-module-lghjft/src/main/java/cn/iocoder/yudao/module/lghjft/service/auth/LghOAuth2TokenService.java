package cn.iocoder.yudao.module.lghjft.service.auth;

import cn.iocoder.yudao.module.lghjft.dal.dataobject.auth.GhQxDlzhxxDO;
import cn.iocoder.yudao.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;

import java.util.List;

/**
 * 陇工会 OAuth2.0 Token Service 接口
 */
public interface LghOAuth2TokenService {

    /**
     * 创建访问令牌
     *
     * @param user     用户信息
     * @param userType 用户类型
     * @param clientId 客户端编号
     * @param scopes   授权范围
     * @return 访问令牌的信息
     */
    OAuth2AccessTokenDO createAccessToken(GhQxDlzhxxDO user, Integer userType, String clientId, List<String> scopes);

}
