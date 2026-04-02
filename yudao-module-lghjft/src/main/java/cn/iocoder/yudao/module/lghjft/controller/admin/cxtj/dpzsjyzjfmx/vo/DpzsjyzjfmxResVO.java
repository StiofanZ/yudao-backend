package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dpzsjyzjfmx.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 近一周缴费 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DpzsjyzjfmxResVO {

    @Schema(description = "spuuid")
    private String spuuid;
    @Schema(description = "zsswjgDm")
    private String zsswjgDm;
    @Schema(description = "swjg")
    private String swjg;
    @Schema(description = "ghjg")
    private String ghjg;
    @Schema(description = "djxh")
    private String djxh;
    @Schema(description = "jfdw")
    private String jfdw;
    @Schema(description = "skssqq")
    private LocalDateTime skssqq;
    @Schema(description = "skssqz")
    private LocalDateTime skssqz;
    @Schema(description = "zspm")
    private String zspm;
    @Schema(description = "jfje")
    private BigDecimal jfje;
    @Schema(description = "jfrq")
    private LocalDateTime jfrq;
}
