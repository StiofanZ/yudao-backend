package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.syhzxx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 首页汇总信息 Response VO")
@Data
public class SyhzxxResVO {

    @Schema(description = "deptId")
    @ExcelProperty("工会机构代码")
    private String deptId;
    @Schema(description = "bzhs")
    @ExcelProperty("本周户数")
    private Long bzhs;
    @Schema(description = "bzje")
    @ExcelProperty("本周金额")
    private BigDecimal bzje;
    @Schema(description = "byhs")
    @ExcelProperty("本月户数")
    private Long byhs;
    @Schema(description = "byje")
    @ExcelProperty("本月金额")
    private BigDecimal byje;
    @Schema(description = "bnhs")
    @ExcelProperty("本年户数")
    private Long bnhs;
    @Schema(description = "bnje")
    @ExcelProperty("本年金额")
    private BigDecimal bnje;
    @Schema(description = "rkwwhhs")
    @ExcelProperty("入库未维护户数")
    private Long rkwwhhs;
    @Schema(description = "rkwwhje")
    @ExcelProperty("入库未维护金额")
    private BigDecimal rkwwhje;
    @Schema(description = "jczhkhs")
    @ExcelProperty("基层账户空户数")
    private Long jczhkhs;
    @Schema(description = "jczhkje")
    @ExcelProperty("基层账户空金额")
    private BigDecimal jczhkje;
    @Schema(description = "bfsbbs")
    @ExcelProperty("拨付失败笔数")
    private Long bfsbbs;
    @Schema(description = "bfsbje")
    @ExcelProperty("拨付失败金额")
    private BigDecimal bfsbje;
}
