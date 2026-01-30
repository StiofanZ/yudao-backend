package cn.iocoder.yudao.module.lghjft.dal.dataobject.wtfk;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 工会经费通-问题反馈 DO
 *
 * @author 李文军
 */
@TableName("lghjft_wtfk")
@KeySequence("lghjft_wtfk_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
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
     * 处理状态
     */
    private Integer status;
    /**
     * 处理人ID
     */
    private Long processorId;
    /**
     * 处理时间（）
     */
    private LocalDateTime processTime;
    /**
     * 处理备注：管理员处理说明
     */
    private String processNotes;
    /**
     * 是否删除
     */
    @TableLogic  // 必须有这个注解，MyBatis-Plus 才会把 deleteById 识别为逻辑删除
    private Boolean deleted;


}