package cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.cbjmx;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 筹备金统计 DO - 映射 v1 表 cbjmx
 * <p>
 * 注意：v1 表结构，禁止继承 BaseDO / TenantBaseDO，禁止 @TableLogic
 */
@TableName("cbjmx")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class CbjmxDO {

    @TableId(type = IdType.AUTO)
    private Long ghjfId;
    private String spuuid;
    private String zspmDm;
    private String nd;
    private String deptId;
    private String shxydm;
    private String djxh;
    private String nsrmc;
    private String zgswjDm;
    private LocalDateTime rkrq;
    private LocalDateTime jsrq;
    private LocalDateTime skssqq;
    private LocalDateTime skssqz;
    private String jsbj;
    private String cbjthbj;
    private BigDecimal qtje;
    private BigDecimal jfje;
    private BigDecimal cbjje;
    private LocalDateTime zjxcrq;
    private String fbbj;
    private LocalDateTime fbrq;
    private BigDecimal sfbje;
}
