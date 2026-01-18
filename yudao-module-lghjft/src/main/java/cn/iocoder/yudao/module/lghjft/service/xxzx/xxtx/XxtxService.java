package cn.iocoder.yudao.module.lghjft.service.xxzx.xxtx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.XxtxMessagePageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.XxtxMessageSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.XxtxMessageSendReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.XxtxMessageRespVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xxzx.xxtx.XxtxMessageDO;

import java.util.List;

/**
 * 消息提醒 Service 接口
 */
public interface XxtxService {

    /**
     * 创建消息
     *
     * @param createReqVO 消息信息
     * @return 消息ID
     */
    Long createMessage(XxtxMessageSaveReqVO createReqVO);

    /**
     * 更新消息
     *
     * @param updateReqVO 消息信息
     */
    void updateMessage(XxtxMessageSaveReqVO updateReqVO);

    /**
     * 删除消息
     *
     * @param id 消息ID
     */
    void deleteMessage(Long id);

    /**
     * 批量删除消息
     *
     * @param ids 消息ID列表
     */
    void deleteMessageList(List<Long> ids);

    /**
     * 发送消息
     *
     * @param sendReqVO 发送信息
     */
    void sendMessage(XxtxMessageSendReqVO sendReqVO);

    /**
     * 撤回消息
     *
     * @param id 消息ID
     */
    void recallMessage(Long id);

    /**
     * 获取消息分页列表
     *
     * @param reqVO 分页条件
     * @return 消息分页列表
     */
    PageResult<XxtxMessageDO> getMessagePage(XxtxMessagePageReqVO reqVO);

    /**
     * 获取消息详情（包含部门和用户名称）
     *
     * @param id 消息ID
     * @return 消息详情
     */
    XxtxMessageRespVO getMessageDetail(Long id);

    /**
     * 获取消息详情
     *
     * @param id 消息ID
     * @return 消息详情
     */
    XxtxMessageDO getMessage(Long id);

    /**
     * 标记消息为已读
     *
     * @param messageId 消息ID
     * @param userId 用户ID
     */
    void markMessageAsRead(Long messageId, Long userId);

}
