package cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.cbjmx;

import lombok.*;

import java.math.BigDecimal;

/**
 * 筹备金统计汇总 DO - 对应 v1 Cbjmxtj，GROUP BY 聚合结果
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class CbjmxtjDO {

    private String dwdm;
    private String deptId;
    private String shxydm;
    private String nsrmc;
    private String jsbj;
    private String cbjthbj;
    private String fbbj;
    private BigDecimal bs;
    private BigDecimal hs;
    private BigDecimal qtje;
    private BigDecimal jfje;
    private BigDecimal cbjje;
    private BigDecimal sfbje;
}
