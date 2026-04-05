package cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejftz2023;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * 小额缴费统计 DO (view on szjfcx)
 */
@TableName("szjfcx")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Xejftz2023DO {

    @TableId
    @TableField("DEPT_ID")
    private String deptId;

    @TableField(exist = false)
    private String sjdm;
    @TableField(exist = false)
    private String xjdm;

    @TableField(exist = false)
    private BigDecimal bs;
    @TableField(exist = false)
    private BigDecimal hs;
    @TableField(exist = false)
    private BigDecimal ybtse;
    @TableField(exist = false)
    private BigDecimal jcghje;
    @TableField(exist = false)
    private BigDecimal cbjje;
    @TableField(exist = false)
    private BigDecimal xjghje;
    @TableField(exist = false)
    private BigDecimal sjghje;
    @TableField(exist = false)
    private BigDecimal szghje;
    @TableField(exist = false)
    private BigDecimal qgghje;
    @TableField(exist = false)
    private BigDecimal swjgje;
    @TableField(exist = false)
    private BigDecimal sdsje;
}
