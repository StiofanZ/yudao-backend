package cn.iocoder.yudao.module.lghjft.dal.dataobject.wtfk;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 工会经费通-问题反馈 DO
 *
 * @author 李文军
 */
@TableName(value = "lghjft_wtfk", autoResultMap = true) //
@KeySequence("lghjft_wtfk_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WtfkDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 反馈ID
     */
    private String feedbackId;
    /**
     * 用户ID（关联系统用户表）
     */
    private Long userId;
    /**
     * 用户名（冗余存储）
     */
    private String userName;
    /**
     * 反馈类型
     */
    private String type;
    /**
     * 平台名称
     */
    private String platformName;
    /**
     * 反馈内容
     */
    private String content;
    /**
     * 联系电话
     */
    private String contactPhone;
    /**
     * 联系邮箱
     */
    private String contactEmail;

    /**
     * 附件列表 (JSON存储)
     * 自动处理：存入时 List -> JSON字符串，取出时 JSON字符串 -> List
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<FileItem> files;

    /**
     * 处理状态
     */
    private Integer status;
    /**
     * 处理人ID
     */
    private Long processorId;
    /**
     * 处理时间
     */
    private LocalDateTime processTime;
    /**
     * 处理备注：管理员处理说明
     */
    private String processNotes;
    /**
     * 是否删除
     */
    @TableLogic
    private Boolean deleted;

    /**
     * 附件对象结构（对应 JSON 中的对象）
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FileItem {
        private String fileUrl;
        private String fileName;
        private String fileOriginName;
    }

}