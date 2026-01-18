package cn.iocoder.yudao.module.lghjft.service.xxzx.xxtx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.message.XxtxMessageMyPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.message.XxtxMessagePageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xxzx.xxtx.XxtxMessageDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xxzx.xxtx.XxtxTemplateDO;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 站内信 Service 接口
 *
 * @author xrcoder
 */
public interface XxtxMessageService {

    /**
     * 创建站内信
     *
     * @param userId 用户编号
     * @param userType 用户类型
     * @param template 模版信息
     * @param templateContent 模版内容
     * @param templateParams 模版参数
     * @return 站内信编号
     */
    Long createXxtxMessage(Long userId, Integer userType,
                           XxtxTemplateDO template, String templateContent, Map<String, Object> templateParams);

    /**
     * 获得站内信分页
     *
     * @param pageReqVO 分页查询
     * @return 站内信分页
     */
    PageResult<XxtxMessageDO> getXxtxMessagePage(XxtxMessagePageReqVO pageReqVO);

    /**
     * 获得【我的】站内信分页
     *
     * @param pageReqVO 分页查询
     * @param userId 用户编号
     * @param userType 用户类型
     * @return 站内信分页
     */
    PageResult<XxtxMessageDO> getMyMyXxtxMessagePage(XxtxMessageMyPageReqVO pageReqVO, Long userId, Integer userType);

    /**
     * 获得站内信
     *
     * @param id 编号
     * @return 站内信
     */
    XxtxMessageDO getXxtxMessage(Long id);

    /**
     * 获得【我的】未读站内信列表
     *
     * @param userId   用户编号
     * @param userType 用户类型
     * @param size     数量
     * @return 站内信列表
     */
    List<XxtxMessageDO> getUnreadXxtxMessageList(Long userId, Integer userType, Integer size);

    /**
     * 统计用户未读站内信条数
     *
     * @param userId   用户编号
     * @param userType 用户类型
     * @return 返回未读站内信条数
     */
    Long getUnreadXxtxMessageCount(Long userId, Integer userType);

    /**
     * 标记站内信为已读
     *
     * @param ids    站内信编号集合
     * @param userId 用户编号
     * @param userType 用户类型
     * @return 更新到的条数
     */
    int updateXxtxMessageRead(Collection<Long> ids, Long userId, Integer userType);

    /**
     * 标记所有站内信为已读
     *
     * @param userId   用户编号
     * @param userType 用户类型
     * @return 更新到的条数
     */
    int updateAllXxtxMessageRead(Long userId, Integer userType);

}
