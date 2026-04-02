package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jfbfmx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 经费拨付统计(按结算标记) Response VO")
@Data
public class JfbfmxjsbjResVO {

    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "结算标记")
    private String jsbj;

    @Schema(description = "笔数")
    private Long bs;

    @Schema(description = "户数")
    private Long hs;

    @Schema(description = "应补退税额合计")
    private BigDecimal ybtse;

    @Schema(description = "基层工会金额合计")
    private BigDecimal jcghje;

    @Schema(description = "筹备金金额合计")
    private BigDecimal cbjje;

    @Schema(description = "行业工会金额合计")
    private BigDecimal hyghje;

    @Schema(description = "县级工会金额合计")
    private BigDecimal xjghje;

    @Schema(description = "市级工会金额合计")
    private BigDecimal sjghje;

    @Schema(description = "省级工会金额合计")
    private BigDecimal szghje;

    @Schema(description = "全国工会金额合计")
    private BigDecimal qgghje;

    @Schema(description = "所得税金额合计")
    private BigDecimal sdsje;

    @Schema(description = "税务机关金额合计")
    private BigDecimal swjgje;
}
