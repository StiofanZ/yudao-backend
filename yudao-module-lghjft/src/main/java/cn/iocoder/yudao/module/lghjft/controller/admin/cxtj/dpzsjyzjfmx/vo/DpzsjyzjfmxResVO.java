package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dpzsjyzjfmx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 近一周缴费 Response VO")
@Data
public class DpzsjyzjfmxResVO {

    @Schema(description = "spuuid")
    @ExcelProperty("税票UUID")
    private String spuuid;
    @Schema(description = "zsswjgDm")
    @ExcelProperty("征收税务机关代码")
    private String zsswjgDm;
    @Schema(description = "swjg")
    @ExcelProperty("税务机关")
    private String swjg;
    @Schema(description = "ghjg")
    @ExcelProperty("工会机构")
    private String ghjg;
    @Schema(description = "djxh")
    @ExcelProperty("登记序号")
    private String djxh;
    @Schema(description = "jfdw")
    @ExcelProperty("缴费单位")
    private String jfdw;
    @Schema(description = "skssqq")
    @ExcelProperty("税款所属期起")
    private String skssqq;
    @Schema(description = "skssqz")
    @ExcelProperty("税款所属期止")
    private String skssqz;
    @Schema(description = "zspm")
    @ExcelProperty("征收品目")
    private String zspm;
    @Schema(description = "jfje")
    @ExcelProperty("缴费金额")
    private BigDecimal jfje;
    @Schema(description = "jfrq")
    @ExcelProperty("缴费日期")
    private String jfrq;
}
