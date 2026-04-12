package cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjhztj.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 筹备金分户统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CbjhztjtjResVO {

    @Schema(description = "上级工会")
    @ExcelProperty(value = "上级工会", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    private String dwdm;

    @Schema(description = "主管工会")
    @ExcelProperty(value = "主管工会", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    private String deptId;

    @Schema(description = "年度")
    private String nd;

    @Schema(description = "社会信用代码")
    @ExcelProperty("社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人名称")
    @ExcelProperty("纳税人名称")
    private String nsrmc;

    @Schema(description = "结算标记")
    @ExcelProperty(value = "结算标记", converter = DictConvert.class)
    @DictFormat("sys_jsbj_type")
    private String jsbj;

    @Schema(description = "筹备金拨付标记")
    @ExcelProperty(value = "筹备金拨付标记", converter = DictConvert.class)
    @DictFormat("sys_cbjcl_type")
    private String cbjthbj;

    @Schema(description = "返拨标记")
    @ExcelProperty(value = "返拨标记", converter = DictConvert.class)
    @DictFormat("cbj_fbbj")
    private String fbbj;

    @Schema(description = "笔数")
    @ExcelProperty("笔数")
    private BigDecimal bs;

    @Schema(description = "户数")
    @ExcelProperty("户数")
    private BigDecimal hs;

    @Schema(description = "筹备金全退金额")
    @ExcelProperty("筹备金全退金额")
    private BigDecimal qtje;

    @Schema(description = "缴费金额")
    @ExcelProperty("缴费金额")
    private BigDecimal jfje;

    @Schema(description = "筹备金金额")
    @ExcelProperty("筹备金金额")
    private BigDecimal cbjje;

    @Schema(description = "实返拨金额")
    @ExcelProperty("实返拨金额")
    private BigDecimal sfbje;
}
