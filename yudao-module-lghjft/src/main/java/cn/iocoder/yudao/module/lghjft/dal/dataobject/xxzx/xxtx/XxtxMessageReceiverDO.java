package cn.iocoder.yudao.module.lghjft.dal.dataobject.xxzx.xxtx;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 消息接收表
 *
 * @author ruoyi
 */
@TableName("gh_xxzx_xxtx_message_receiver")
@KeySequence("gh_xxzx_xxtx_message_receiver_seq")
@Data
@EqualsAndHashCode(callSuper = true)
public class XxtxMessageReceiverDO extends BaseDO {

    /**
     * 主键
     */
    private Long id;
    
    /**
     * 消息ID
     */
    private Long messageId;
    
    /**
     * 接收者类型（0：部门，1：用户）
     */
    private Integer receiverType;
    
    /**
     * 接收者ID（部门ID或用户ID）
     */
    private Long receiverId;
    
    /**
     * 阅读状态（0：未读，1：已读）
     */
    private Integer readStatus;
    
    /**
     * 阅读时间
     */
    private String readTime;
    
}