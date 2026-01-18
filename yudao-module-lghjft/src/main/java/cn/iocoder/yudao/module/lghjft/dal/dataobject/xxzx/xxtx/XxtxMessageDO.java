package cn.iocoder.yudao.module.lghjft.dal.dataobject.xxzx.xxtx;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 消息表
 *
 * @author ruoyi
 */
@TableName(value = "gh_xxzx_xxtx_message", autoResultMap = true)
@KeySequence("gh_xxzx_xxtx_message_seq")
@Data
@EqualsAndHashCode(callSuper = true)
public class XxtxMessageDO extends BaseDO {

    /**
     * 消息主键
     */
    private Long id;
    
    /**
     * 消息标题
     */
    private String title;
    
    /**
     * 消息内容
     */
    private String content;
    
    /**
     * 消息类型（0：系统消息，1：业务消息）
     */
    private Integer messageType;
    
    /**
     * 发送者ID
     */
    private Long senderId;
    
    /**
     * 发送者姓名
     */
    private String senderName;
    
    /**
     * 发送时间
     */
    private String sendTime;
    
    /**
     * 消息状态（0：草稿，1：已发送，2：已撤回）
     */
    private Integer status;

    /**
     * 部门ID列表
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Long> deptIds;

    /**
     * 用户ID列表
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Long> userIds;
    
}