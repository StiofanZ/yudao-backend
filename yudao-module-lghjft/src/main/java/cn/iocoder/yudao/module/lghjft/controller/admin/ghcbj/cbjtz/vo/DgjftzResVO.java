package cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjtz.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 代管经费台账统计 Response VO")
@Data
public class DgjftzResVO {

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

    @Schema(description = "应补退税额合计")
    private BigDecimal ybtse;

    @Schema(description = "筹备金金额合计")
    private BigDecimal cbjje;

    @Schema(description = "基层工会金额合计")
    private BigDecimal jcghje;
}
