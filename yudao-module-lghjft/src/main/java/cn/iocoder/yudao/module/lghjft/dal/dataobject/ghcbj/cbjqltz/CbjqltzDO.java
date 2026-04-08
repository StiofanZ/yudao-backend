package cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.cbjqltz;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 筹备金清理台账 DO - 映射 v1 表 cbjqltz
 * <p>
 * 注意：v1 表结构，禁止继承 BaseDO / TenantBaseDO，禁止 @TableLogic
 * sub_* 字段来自 left join cbjqltz_qr，非本表字段
 */
@TableName("cbjqltz")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class CbjqltzDO {

    @TableId(type = IdType.INPUT)
    private String djxh;
    private String deptId;
    private String zgswjDm;
    private String zgswskfjDm;
    private String jdxzDm;
    private String shxydm;
    private String nsrmc;
    private String nsrztDm;
    private String nsrztmc;
    private LocalDateTime sjtbSj;
    private String jsbj;
    private String cbjthbj;
    private String fbbj;
    private Long jfbs;
    private BigDecimal jfje;
    private BigDecimal cbjje;
    private Long fbbs;
    private BigDecimal sfbje;
    private LocalDateTime rkrq;
    private Long yf;
    private LocalDateTime jfrkrq;
    private Long jfyf;

    // --- 来自 left join cbjqltz_qr 的字段 ---
    @TableField(exist = false)
    private String subNsrztDm;
    @TableField(exist = false)
    private String subJfztDm;
    @TableField(exist = false)
    private String subGzqrztDm;
    @TableField(exist = false)
    private String subUpdateBy;
    @TableField(exist = false)
    private LocalDateTime subUpdateTime;
}
