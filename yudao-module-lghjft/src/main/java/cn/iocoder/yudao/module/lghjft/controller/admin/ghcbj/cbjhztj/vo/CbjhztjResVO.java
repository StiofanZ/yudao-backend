package cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjhztj.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 筹备金汇总统计 Response VO")
@Data
public class CbjhztjResVO {

    @Schema(description = "ghjfId")
    private Long ghjfId;
    @Schema(description = "spuuid")
    @ExcelProperty("税票ID")
    private String spuuid;
    @Schema(description = "zspmDm")
    @ExcelProperty(value = "征收品目代码", converter = DictConvert.class)
    @DictFormat("sys_zspm_type")
    private String zspmDm;
    @Schema(description = "nd")
    private String nd;
    @Schema(description = "deptId")
    @ExcelProperty(value = "工会机构", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    private String deptId;
    @Schema(description = "shxydm")
    @ExcelProperty("社会信用代码")
    private String shxydm;
    @Schema(description = "djxh")
    @ExcelProperty("登记序号")
    private String djxh;
    @Schema(description = "nsrmc")
    @ExcelProperty("纳税人名称")
    private String nsrmc;
    @Schema(description = "rkrq")
    @ExcelProperty("入库日期")
    private LocalDateTime rkrq;
    @Schema(description = "jsrq")
    @ExcelProperty("结算日期")
    private LocalDateTime jsrq;
    @Schema(description = "skssqq")
    @ExcelProperty("所属期起")
    private LocalDateTime skssqq;
    @Schema(description = "skssqz")
    @ExcelProperty("所属期止")
    private LocalDateTime skssqz;
    @Schema(description = "jsbj")
    @ExcelProperty(value = "结算标记", converter = DictConvert.class)
    @DictFormat("sys_jsbj_type")
    private String jsbj;
    @Schema(description = "cbjthbj")
    @ExcelProperty(value = "基层经费拨付状态", converter = DictConvert.class)
    @DictFormat("sys_cbjcl_type")
    private String cbjthbj;
    @Schema(description = "qtje")
    private BigDecimal qtje;
    @Schema(description = "jfje")
    private BigDecimal jfje;
    @Schema(description = "cbjje")
    @ExcelProperty("筹备金金额")
    private BigDecimal cbjje;
    @Schema(description = "zjxcrq")
    @ExcelProperty("资金下拨日期")
    private LocalDateTime zjxcrq;
    @Schema(description = "fbbj")
    @ExcelProperty("返拨标记")
    private String fbbj;
    @Schema(description = "fbrq")
    @ExcelProperty("返拨日期")
    private LocalDateTime fbrq;
    @Schema(description = "sfbje")
    @ExcelProperty("实返拨金额")
    private BigDecimal sfbje;
}
