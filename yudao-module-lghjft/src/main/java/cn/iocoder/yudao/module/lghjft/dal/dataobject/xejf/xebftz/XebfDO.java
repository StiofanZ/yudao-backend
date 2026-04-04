package cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xebftz;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.iocoder.yudao.module.lghjft.dal.typehandler.CommaSeparatedStringArrayTypeHandler;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 小额缴费拨付台账 DO
 */
@TableName(value = "xebf", autoResultMap = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class XebfDO {

    @TableId
    private Long ghjfId;
    private BigDecimal sfbje;
    private String fbbj;
    private LocalDateTime fbrq;
    private LocalDateTime zjxcrq;
    private String hkpch;
    private String hkpzh;
    private String bz;
    private String spuuid;
    private LocalDateTime rkrq;
    private LocalDateTime jsrq;
    private String jfqj;
    private LocalDateTime skssqq;
    private LocalDateTime skssqz;
    private String zspmDm;
    private BigDecimal gzze;
    private BigDecimal jmse;
    private BigDecimal yfbje;
    private BigDecimal ybtse;
    @TableField(typeHandler = CommaSeparatedStringArrayTypeHandler.class)
    private String[] jsbj;
    private String deptId;
    private String djxh;
    private String shxydm;
    private String nsrmc;
    @TableField(typeHandler = CommaSeparatedStringArrayTypeHandler.class)
    private String[] xelx25;
    @TableField(typeHandler = CommaSeparatedStringArrayTypeHandler.class)
    private String[] xelx24;
    @TableField(typeHandler = CommaSeparatedStringArrayTypeHandler.class)
    private String[] xelx23;
    private String xwlx;
    private String djzclxmc;
    private BigDecimal jcghbl;
    private BigDecimal jcghje;
    private String jcghhh;
    private String jcghzh;
    private String jcghhm;
    private BigDecimal cbjbl;
    private BigDecimal cbjje;
    private String cbjzh;
    private String cbjhm;
    private String cbjhh;
    private BigDecimal xjghbl;
    private BigDecimal xjghje;
    private String xjghzh;
    private String xjghhm;
    private String xjghhh;
    private BigDecimal sjghbl;
    private BigDecimal sjghje;
    private String sjghzh;
    private String sjghhm;
    private String sjghhh;
    private BigDecimal hyghbl;
    private BigDecimal hyghje;
    private String hyghzh;
    private String hyghhm;
    private String hyghhh;
    private BigDecimal sdghbl;
    private BigDecimal sdghje;
    private String sdghzh;
    private String sdghhm;
    private String sdghhh;
    private BigDecimal szghbl;
    private BigDecimal szghje;
    private String szghzh;
    private String szghhm;
    private String szghhh;
    private BigDecimal qgghbl;
    private BigDecimal qgghje;
    private String qgghzh;
    private String qgghhm;
    private String qgghhh;
}
