package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfmx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "经费聚合查询 Response VO")
@Data
public class JfSummaryResVO {
    private String sjdm;
    private String xjdm;
    private String deptId;
    private String dsyf;      // 月份 (yyyy-MM)
    private String nd;         // 年度 (yyyy)
    private String zspmDm;     // 征收品目
    private String swjgDm;     // 税务机关
    private String sjzq;       // 税款计征期 (yyyy-Qn)
    private Long bs;           // 笔数
    private Long hs;           // 户数
    private BigDecimal ybtse;
    private BigDecimal jcghje;
    private BigDecimal hyghje;
    private BigDecimal sdghje;
    private BigDecimal cbjje;
    private BigDecimal xjghje;
    private BigDecimal sjghje;
    private BigDecimal szghje;
    private BigDecimal qgghje;
    private BigDecimal swjgje;
    private BigDecimal sdsje;
}
