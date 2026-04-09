package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.cbjmx.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;

@Schema(description = "管理后台 - 筹备金统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CbjmxResVO {

    @Schema(description = "工会经费ID")
    private Long ghjfId;

    @Schema(description = "征收品目代码")
    @ExcelProperty(value = "征收品目代码", converter = DictConvert.class)
    @DictFormat("sys_zspm_type")
    private String zspmDm;

    @Schema(description = "年度")
    @ExcelProperty("缴费年度")
    private String nd;

    @Schema(description = "工会机构代码")
    @ExcelProperty(value = "主管工会", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    private String deptId;

    @Schema(description = "社会信用代码")
    @ExcelProperty("社会信用代码")
    private String shxydm;

    @Schema(description = "登记序号")
    @ExcelProperty("登记序号")
    private String djxh;

    @Schema(description = "纳税人名称")
    @ExcelProperty("纳税人名称")
    private String nsrmc;

    @Schema(description = "入库日期")
    private LocalDateTime rkrq;

    @Schema(description = "结算日期")
    private LocalDateTime jsrq;

    @Schema(description = "所属期起")
    private LocalDateTime skssqq;

    @Schema(description = "所属期止")
    private LocalDateTime skssqz;

    @Schema(description = "结算标记")
    @ExcelProperty(value = "结算标记", converter = DictConvert.class)
    @DictFormat("sys_jsbj_type")
    private String jsbj;

    @Schema(description = "筹备金拨付标记")
    @ExcelProperty(value = "筹备金拨付标记", converter = DictConvert.class)
    @DictFormat("sys_cbjcl_type")
    private String cbjthbj;

    @Schema(description = "筹备金全退金额")
    @ExcelProperty("筹备金全退金额")
    private BigDecimal qtje;

    @Schema(description = "缴费金额")
    @ExcelProperty("缴费金额")
    private BigDecimal jfje;

    @Schema(description = "筹备金金额")
    @ExcelProperty("筹备金金额")
    private BigDecimal cbjje;

    @Schema(description = "资金下拨日期")
    private LocalDateTime zjxcrq;

    @Schema(description = "返拨标记")
    @ExcelProperty(value = "返拨标记", converter = DictConvert.class)
    @DictFormat("cbj_fbbj")
    private String fbbj;

    @Schema(description = "返拨日期")
    private LocalDateTime fbrq;

    @Schema(description = "实返拨金额")
    @ExcelProperty("实返拨金额")
    private BigDecimal sfbje;
}
