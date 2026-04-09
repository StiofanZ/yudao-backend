package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.top.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;

@Schema(description = "管理后台 - 缴费排行 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TopResVO {

    @Schema(description = "登记序号")
    @ExcelProperty("登记序号")
    private String djxh;

    @Schema(description = "社会信用代码")
    @ExcelProperty("社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人名称")
    @ExcelProperty("纳税人名称")
    private String nsrmc;

    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "主管工会")
    @ExcelProperty(value = "主管工会", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    private String dwmc;

    @Schema(description = "当年缴费笔数")
    @ExcelProperty("当年缴费笔数")
    private Long bsdn;

    @Schema(description = "去年缴费笔数")
    @ExcelProperty("去年缴费笔数")
    private Long bswn;

    @Schema(description = "笔数差异")
    @ExcelProperty("笔数差异")
    private Long bscy;

    @Schema(description = "当年缴费金额")
    @ExcelProperty("当年缴费金额")
    private BigDecimal jfjedn;

    @Schema(description = "去年缴费金额")
    @ExcelProperty("去年缴费金额")
    private BigDecimal jfjewn;

    @Schema(description = "缴费金额差异")
    @ExcelProperty("缴费金额差异")
    private BigDecimal jfjecy;

    @Schema(description = "缴费金额增减率")
    @ExcelProperty("缴费金额增减率")
    private String jfbl;

    @Schema(description = "今年上缴金额")
    @ExcelProperty("今年上缴金额")
    private BigDecimal sjjedn;

    @Schema(description = "去年上缴金额")
    @ExcelProperty("去年上缴金额")
    private BigDecimal sjjewn;

    @Schema(description = "上缴金额差异")
    @ExcelProperty("上缴金额差异")
    private BigDecimal sjjecy;

    @Schema(description = "上缴金额增减率")
    @ExcelProperty("上缴金额增减率")
    private String sjbl;
}
