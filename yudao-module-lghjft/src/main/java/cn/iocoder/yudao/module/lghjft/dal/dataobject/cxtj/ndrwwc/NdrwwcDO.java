package cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.ndrwwc;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * Ndrwwc DO
 */
@TableName("ndrwwc")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class NdrwwcDO {

    @TableId(type = IdType.INPUT)
    private String nd;
    private String dwdm;
    private String dwmc;
    private Long bs;
    private Long hs;
    private BigDecimal jfje;
    private BigDecimal cbjje;
    private BigDecimal jcghje;
    private BigDecimal hyghje;
    private BigDecimal sdghje;
    private BigDecimal xjghje;
    private BigDecimal sjghje;
    private BigDecimal szghje;
    private BigDecimal qgghje;
    private BigDecimal szqzhj;
    private BigDecimal sdsje;
    private BigDecimal swjgje;
}
