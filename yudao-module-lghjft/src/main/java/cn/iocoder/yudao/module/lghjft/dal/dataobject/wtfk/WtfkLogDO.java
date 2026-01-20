package cn.iocoder.yudao.module.lghjft.dal.dataobject.wtfk;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 问题反馈处理日志 DO
 * 用于记录管理员对某一反馈问题的多次处理记录，实现处理历史追踪。
 *
 * @author 管理员
 */
@TableName("lghjft_wtfk_log")
@KeySequence("lghjft_wtfk_log_seq") // 匹配 yudao 框架的序列规范
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WtfkLogDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 关联反馈主表ID
     * 对应 lghjft_wtfk 表的 id
     */
    private Long feedbackId;

    /**
     * 处理人ID
     * 对应 system_users 表的 id
     */
    private Long operatorId;

    /**
     * 处理人姓名
     */
    private String operatorName;

    /**
     * 处理意见/备注
     */
    private String content;

    /**
     * 处理后的状态
     * 对应业务字典，如：0-未处理，1-已处理，2-已关闭
     */
    private Integer status;

}