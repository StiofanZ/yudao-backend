package cn.iocoder.yudao.module.lghjft.service.auth;

import cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo.AuthorizeLghReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo.AuthorizeReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo.AuthorizeResVO;
import cn.iocoder.yudao.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import jakarta.validation.Valid;

import java.util.List;

public interface AuthenticateService {
    /**
     * 授权码登录
     *
     * @param reqVO 登录信息
     * @return 登录结果
     */
    AuthorizeResVO loginAuthCode(@Valid AuthorizeLghReqVO reqVO);

    /**
     * 账号登录
     *
     * @param reqVO 登录信息
     * @return 登录结果
     */
    AuthorizeResVO login(@Valid AuthorizeReqVO reqVO);

    /**
     * 获取单位权限身份列表
     *
     * @param dlzh 登录账号
     * @return 单位权限身份列表
     */
    List<AuthorizeResVO.DwQxSf> getDwQxSfList(Long dlzh);

    OAuth2AccessTokenDO createAccessToken(AuthorizeResVO resVO, Integer userType, String clientId, List<String> scopes);

}
