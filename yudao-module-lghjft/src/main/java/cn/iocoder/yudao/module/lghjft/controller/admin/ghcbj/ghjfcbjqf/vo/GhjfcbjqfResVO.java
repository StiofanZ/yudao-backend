package cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.ghjfcbjqf.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 筹备金全返 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GhjfcbjqfResVO {

    @Schema(description = "工会经费ID")
    private Long ghjfId;

    @Schema(description = "登记序号")
    @ExcelProperty("登记序号")
    private String djxh;

    @Schema(description = "社会信用代码")
    @ExcelProperty("社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人名称")
    @ExcelProperty("纳税人名称")
    private String nsrmc;

    @Schema(description = "缴费账号")
    @ExcelProperty("缴费账号")
    private String jfzh;

    @Schema(description = "缴费户名")
    @ExcelProperty("缴费户名")
    private String jfhm;

    @Schema(description = "工会机构代码")
    @ExcelProperty("工会机构代码")
    private String deptId;

    @Schema(description = "缴费金额")
    @ExcelProperty("缴费金额")
    private BigDecimal ybtse;

    @Schema(description = "筹备金金额")
    @ExcelProperty("筹备金金额")
    private BigDecimal cbjje;
}
