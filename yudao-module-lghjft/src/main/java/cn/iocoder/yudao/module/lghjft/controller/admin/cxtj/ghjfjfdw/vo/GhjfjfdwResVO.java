package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ghjfjfdw.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 近三年缴费情况 Response VO")
@Data
public class GhjfjfdwResVO {

    @Schema(description = "djxh")
    @ExcelProperty("登记序号")
    private String djxh;
    @Schema(description = "shxydm")
    @ExcelProperty("社会信用代码")
    private String shxydm;
    @Schema(description = "nsrmc")
    @ExcelProperty("纳税人名称")
    private String nsrmc;
    @Schema(description = "deptId")
    @ExcelProperty(value = "工会机构", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    private String deptId;
    @Schema(description = "zsswjgDm")
    @ExcelProperty(value = "税务机关", converter = DictConvert.class)
    @DictFormat("sys_swjg_type")
    private String zsswjgDm;
    @Schema(description = "djxhbn")
    private String djxhbn;
    @Schema(description = "bsbn")
    @ExcelProperty("本年笔数")
    private Long bsbn;
    @Schema(description = "ysbn")
    @ExcelProperty("本年月数")
    private Long ysbn;
    @Schema(description = "jfjebn")
    @ExcelProperty("本年金额")
    private BigDecimal jfjebn;
    @Schema(description = "djxhsn")
    private String djxhsn;
    @Schema(description = "bssn")
    @ExcelProperty("上年笔数")
    private Long bssn;
    @Schema(description = "yssn")
    @ExcelProperty("上年月数")
    private Long yssn;
    @Schema(description = "jfjesn")
    @ExcelProperty("上年金额")
    private BigDecimal jfjesn;
    @Schema(description = "djxhqn")
    private String djxhqn;
    @Schema(description = "bsqn")
    @ExcelProperty("前年笔数")
    private Long bsqn;
    @Schema(description = "ysqn")
    @ExcelProperty("前年月数")
    private Long ysqn;
    @Schema(description = "jfjeqn")
    @ExcelProperty("前年金额")
    private BigDecimal jfjeqn;
}
