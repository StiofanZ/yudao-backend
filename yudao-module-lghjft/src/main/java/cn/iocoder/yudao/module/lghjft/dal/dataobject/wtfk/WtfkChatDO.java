package cn.iocoder.yudao.module.lghjft.dal.dataobject.wtfk;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;
import java.time.LocalDateTime;

/**
 * 问题反馈对话记录 DO
 *
 * @author 管理员
 */
@TableName("lghjft_wtfk_chat")
@KeySequence("lghjft_wtfk_chat_seq") // 用于 Oracle、PostgreSQL 等数据库
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WtfkChatDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 关联反馈主表ID
     */
    private Long feedbackId;
    /**
     * 发送人ID
     */
    private Long userId;
    /**
     * 发送人类型：1-用户 2-管理员
     */
    private Integer userType;
    /**
     * 内容
     */
    private String content;

}