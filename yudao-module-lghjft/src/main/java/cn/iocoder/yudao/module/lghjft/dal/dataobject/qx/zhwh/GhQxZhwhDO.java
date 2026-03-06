package cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.zhwh;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@TableName(value = "gh_qx_zhwh", autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GhQxZhwhDO extends BaseDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String applyNo;

    private Long dlzhId;

    private Long deptId;

    private String djxh;

    private String shxydm;

    private String dwmc;

    private String oldJcghzh;

    private String oldJcghhm;

    private String oldJcghhh;

    private String oldJcghyh;

    private String newJcghzh;

    private String newJcghhm;

    private String newJcghhh;

    private String newJcghyh;

    private String applyReason;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> materials;

    private Integer status;

    private String auditRemark;

    private Long auditUserId;

    private LocalDateTime auditTime;

    private Integer syncStatus;

    private String syncMessage;

    private LocalDateTime syncTime;
}
