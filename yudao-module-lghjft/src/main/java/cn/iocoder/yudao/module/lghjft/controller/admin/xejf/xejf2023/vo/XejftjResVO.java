package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf2023.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 小额缴费统计 Response VO")
@Data
public class XejftjResVO {

    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "结算标记")
    private String jsbj;

    @Schema(description = "筹备金退回标记")
    private String cbjthbj;

    @Schema(description = "笔数")
    private Long bs;

    @Schema(description = "户数")
    private Long hs;

    @Schema(description = "应补退税额")
    private BigDecimal ybtse;

    @Schema(description = "应缴费金额")
    private BigDecimal yfje;

    @Schema(description = "工会经费发放金额")
    private BigDecimal gkzfje;

    @Schema(description = "国际退回金额")
    private BigDecimal gjthje;

    @Schema(description = "基层工会金额")
    private BigDecimal jcghje;

    @Schema(description = "筹备金金额")
    private BigDecimal cbjje;

    @Schema(description = "行业工会金额")
    private BigDecimal hyghje;

    @Schema(description = "县级工会金额")
    private BigDecimal xjghje;

    @Schema(description = "市级工会金额")
    private BigDecimal sjghje;

    @Schema(description = "省级工会金额")
    private BigDecimal szghje;

    @Schema(description = "全国工会金额")
    private BigDecimal qgghje;

    @Schema(description = "所得税金额")
    private BigDecimal sdsje;

    @Schema(description = "税务机关金额")
    private BigDecimal swjgje;
}
