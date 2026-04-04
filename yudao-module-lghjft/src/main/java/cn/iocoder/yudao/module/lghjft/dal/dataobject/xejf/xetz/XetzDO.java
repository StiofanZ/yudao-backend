package cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xetz;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.iocoder.yudao.module.lghjft.dal.typehandler.CommaSeparatedStringArrayTypeHandler;
import lombok.*;

import java.math.BigDecimal;

/**
 * 小额台账 DO
 */
@TableName(value = "xetz", autoResultMap = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class XetzDO {

    @TableId
    private String jfqj;
    @TableField(typeHandler = CommaSeparatedStringArrayTypeHandler.class)
    private String[] xelx23;
    @TableField(typeHandler = CommaSeparatedStringArrayTypeHandler.class)
    private String[] xelx24;
    @TableField(typeHandler = CommaSeparatedStringArrayTypeHandler.class)
    private String[] xelx25;
    private String xwlx;
    private String ghlbDm;
    private String xtlbDm;
    private String djxh;
    private String shxydm;
    private String nsrmc;
    private String deptId;
    private String sdghjgDm;
    private BigDecimal gzze;
    private Long bs;
    private Long yf;
    private BigDecimal ybtse;
    private BigDecimal sjje;
    private BigDecimal jcghje;
    private BigDecimal cbjje;
    private BigDecimal hyghje;
    private BigDecimal xjghje;
    private BigDecimal sjghje;
    private BigDecimal szghje;
    private BigDecimal qgghje;
    private BigDecimal sdsje;
    private BigDecimal swjgje;
    private BigDecimal sdghje;
}
