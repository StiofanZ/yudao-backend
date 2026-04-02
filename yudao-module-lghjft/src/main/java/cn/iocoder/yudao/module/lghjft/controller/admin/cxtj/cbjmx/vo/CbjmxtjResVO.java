package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.cbjmx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 筹备金统计汇总 Response VO")
@Data
public class CbjmxtjResVO {

    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "年度")
    private String nd;

    @Schema(description = "笔数")
    private Long bs;

    @Schema(description = "其他金额合计")
    private BigDecimal qtje;

    @Schema(description = "经费金额合计")
    private BigDecimal jfje;

    @Schema(description = "筹备金金额合计")
    private BigDecimal cbjje;

    @Schema(description = "实际拨付金额合计")
    private BigDecimal sfbje;
}
