package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfbfmx.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 经费拨付明细旧版 Response VO")
@Data
@ExcelIgnoreUnannotated
public class JfbfmxDetailResVO {

    @Schema(description = "划款批次号")
    private String hkpch;

    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "工会机构名称")
    @ExcelProperty("工会机构名称")
    private String dwmc;

    @Schema(description = "社会信用代码")
    @ExcelProperty("社会信用代码")
    private String shxydm;

    @Schema(description = "单位名称")
    @ExcelProperty("单位名称")
    private String nsrmc;

    @Schema(description = "工资总额")
    @ExcelProperty("工资总额")
    private BigDecimal gzze;

    @Schema(description = "税率")
    @ExcelProperty("税率")
    private BigDecimal sl;

    @Schema(description = "入库金额")
    @ExcelProperty("入库金额")
    private BigDecimal ybtse;

    @Schema(description = "基层工会户名")
    @ExcelProperty("基层工会户名")
    private String jcghhm;

    @Schema(description = "基层工会比例")
    @ExcelProperty("基层工会比例")
    private BigDecimal jcghbl;

    @Schema(description = "基层工会金额")
    @ExcelProperty("基层工会金额")
    private BigDecimal jcghje;

    @Schema(description = "筹备金比例")
    @ExcelProperty("筹备金比例")
    private BigDecimal cbjbl;

    @Schema(description = "筹备金金额")
    @ExcelProperty("筹备金金额")
    private BigDecimal cbjje;

    @Schema(description = "行业工会比例")
    @ExcelProperty("行业工会比例")
    private BigDecimal hyghbl;

    @Schema(description = "行业工会金额")
    @ExcelProperty("行业工会金额")
    private BigDecimal hyghje;

    @Schema(description = "属地工会比例")
    @ExcelProperty("属地工会比例")
    private BigDecimal sdghbl;

    @Schema(description = "属地工会户名")
    @ExcelProperty("属地工会户名")
    private String sdghhm;

    @Schema(description = "属地工会金额")
    @ExcelProperty("属地工会金额")
    private BigDecimal sdghje;

    @Schema(description = "县级工会比例")
    @ExcelProperty("县级工会比例")
    private BigDecimal xjghbl;

    @Schema(description = "县级工会金额")
    @ExcelProperty("县级工会金额")
    private BigDecimal xjghje;

    @Schema(description = "市级工会比例")
    @ExcelProperty("市级工会比例")
    private BigDecimal sjghbl;

    @Schema(description = "市级工会金额")
    @ExcelProperty("市级工会金额")
    private BigDecimal sjghje;

    @Schema(description = "省总工会比例")
    @ExcelProperty("省总工会比例")
    private BigDecimal szghbl;

    @Schema(description = "省总工会金额")
    @ExcelProperty("省总工会金额")
    private BigDecimal szghje;

    @Schema(description = "全总比例")
    @ExcelProperty("全总比例")
    private BigDecimal qgghbl;

    @Schema(description = "全总金额")
    @ExcelProperty("全总金额")
    private BigDecimal qgghje;

    @Schema(description = "省税局比例")
    @ExcelProperty("省税局比例")
    private BigDecimal sdsbl;

    @Schema(description = "省税局金额")
    @ExcelProperty("省税局金额")
    private BigDecimal sdsje;

    @Schema(description = "税务机关比例")
    @ExcelProperty("税务机关比例")
    private BigDecimal swjgbl;

    @Schema(description = "税务机关金额")
    @ExcelProperty("税务机关金额")
    private BigDecimal swjgje;

    @Schema(description = "所属期起")
    @ExcelProperty("所属期起")
    private LocalDateTime skssqq;

    @Schema(description = "所属期止")
    @ExcelProperty("所属期止")
    private LocalDateTime skssqz;

    @Schema(description = "征收品目")
    @ExcelProperty("征收品目")
    private String zspmDm;

    @Schema(description = "入库日期")
    @ExcelProperty("入库日期")
    private LocalDateTime rkrq;

    @Schema(description = "结算日期")
    @ExcelProperty("结算日期")
    private LocalDateTime jsrq;
}
