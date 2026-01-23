package cn.iocoder.yudao.module.lghjft.service.auth;

import cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo.AuthorizeResVO;
import cn.iocoder.yudao.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;

import java.util.List;

/**
 * 陇工会 OAuth2.0 Token Service 接口
 */
public interface LghOAuth2TokenService {

    /**
     * 创建访问令牌
     *
     * @param resVO     用户信息
     * @param userType 用户类型
     * @param clientId 客户端编号
     * @param scopes   授权范围
     * @return 访问令牌的信息
     */
    OAuth2AccessTokenDO createAccessToken(AuthorizeResVO resVO, Integer userType, String clientId, List<String> scopes);

}
