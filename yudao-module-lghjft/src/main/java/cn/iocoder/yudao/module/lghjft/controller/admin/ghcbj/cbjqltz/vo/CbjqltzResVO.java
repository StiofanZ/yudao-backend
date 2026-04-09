package cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjqltz.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 筹备金清理台账 Response VO")
@Data
public class CbjqltzResVO {

    @Schema(description = "deptId")
    @ExcelProperty(value = "工会机构", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    private String deptId;
    @Schema(description = "zgswjDm")
    @ExcelProperty(value = "主管税务机关", converter = DictConvert.class)
    @DictFormat("sys_swjg_type")
    private String zgswjDm;
    @Schema(description = "zgswskfjDm")
    @ExcelProperty(value = "科所分局", converter = DictConvert.class)
    @DictFormat("sys_swjg_type")
    private String zgswskfjDm;
    @Schema(description = "jdxzDm")
    @ExcelProperty(value = "街道乡镇代码", converter = DictConvert.class)
    @DictFormat("sys_jdxz")
    private String jdxzDm;
    @Schema(description = "shxydm")
    @ExcelProperty("社会信用代码")
    private String shxydm;
    @Schema(description = "nsrmc")
    @ExcelProperty("纳税人名称")
    private String nsrmc;
    @Schema(description = "djxh")
    @ExcelProperty("登记序号")
    private String djxh;
    @Schema(description = "nsrztDm")
    @ExcelProperty(value = "纳税人状态", converter = DictConvert.class)
    @DictFormat("sys_nsrzt")
    private String nsrztDm;
    @Schema(description = "nsrztmc")
    private String nsrztmc;
    @Schema(description = "sjtbSj")
    @ExcelProperty("税务数据同步时间")
    private LocalDateTime sjtbSj;
    @Schema(description = "jsbj")
    @ExcelProperty(value = "结算标记", converter = DictConvert.class)
    @DictFormat("sys_jsbj_type")
    private String jsbj;
    @Schema(description = "cbjthbj")
    @ExcelProperty(value = "基层经费拨付状态", converter = DictConvert.class)
    @DictFormat("sys_cbjcl_type")
    private String cbjthbj;
    @Schema(description = "fbbj")
    private String fbbj;
    @Schema(description = "jfbs")
    @ExcelProperty("缴费笔数")
    private Long jfbs;
    @Schema(description = "jfje")
    @ExcelProperty("缴费金额")
    private BigDecimal jfje;
    @Schema(description = "cbjje")
    @ExcelProperty("筹备金金额")
    private BigDecimal cbjje;
    @Schema(description = "fbbs")
    @ExcelProperty("实返拨笔数")
    private Long fbbs;
    @Schema(description = "sfbje")
    @ExcelProperty("实返拨金额")
    private BigDecimal sfbje;
    @Schema(description = "rkrq")
    @ExcelProperty("筹备金最新缴费日期")
    private LocalDateTime rkrq;
    @Schema(description = "yf")
    @ExcelProperty("距目前月份")
    private Long yf;
    @Schema(description = "jfrkrq")
    @ExcelProperty("经费最新缴费日期")
    private LocalDateTime jfrkrq;
    @Schema(description = "jfyf")
    @ExcelProperty("距离目前月份")
    private Long jfyf;
    @Schema(description = "subNsrztDm")
    private String subNsrztDm;
    @Schema(description = "subJfztDm")
    private String subJfztDm;
    @Schema(description = "subGzqrztDm")
    private String subGzqrztDm;
    @Schema(description = "subUpdateBy")
    private String subUpdateBy;
    @Schema(description = "subUpdateTime")
    private LocalDateTime subUpdateTime;
}
