package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xetz.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import cn.iocoder.yudao.module.lghjft.framework.excel.StringArrayDictConvert;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 小额台账 Response VO")
@Data
@ExcelIgnoreUnannotated
public class XetzResVO {
    @ExcelProperty(value = "小额缴费期间", converter = DictConvert.class)
    @DictFormat("sys_xejfqj")
    private String jfqj;
    @ExcelProperty(value = "23小额类型", converter = StringArrayDictConvert.class)
    @DictFormat("sys_xejfghzz23_type")
    private String[] xelx23;
    @ExcelProperty(value = "24小额类型", converter = StringArrayDictConvert.class)
    @DictFormat("sys_xejfghzz23_type")
    private String[] xelx24;
    @ExcelProperty(value = "25小额类型", converter = StringArrayDictConvert.class)
    @DictFormat("sys_xejfghzz23_type")
    private String[] xelx25;
    @ExcelProperty(value = "小微类型", converter = DictConvert.class)
    @DictFormat("sys_xwlx")
    private String xwlx;
    @ExcelProperty(value = "工会类别", converter = DictConvert.class)
    @DictFormat("sys_ghlb_type")
    private String ghlbDm;
    @ExcelProperty(value = "系统类别", converter = DictConvert.class)
    @DictFormat("sys_xtlb_type")
    private String xtlbDm;
    @ExcelProperty("登记序号")
    private String djxh;
    @ExcelProperty("社会信用代码")
    private String shxydm;
    @ExcelProperty("缴费单位")
    private String nsrmc;
    @ExcelProperty(value = "工会机构", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    private String deptId;
    private String sdghjgDm;
    @ExcelProperty("工资总额")
    private BigDecimal gzze;
    @ExcelProperty("笔数")
    private Long bs;
    @ExcelProperty("月数")
    private Long yf;
    @ExcelProperty("缴费金额")
    private BigDecimal ybtse;
    @ExcelProperty("上缴金额")
    private BigDecimal sjje;
    @ExcelProperty("基层金额")
    private BigDecimal jcghje;
    @ExcelProperty("筹备金金额")
    private BigDecimal cbjje;
    @ExcelProperty("行业金额")
    private BigDecimal hyghje;
    @ExcelProperty("县级金额")
    private BigDecimal xjghje;
    @ExcelProperty("市级金额")
    private BigDecimal sjghje;
    @ExcelProperty("省总金额")
    private BigDecimal szghje;
    @ExcelProperty("全总金额")
    private BigDecimal qgghje;
    @ExcelProperty("省税局金额")
    private BigDecimal sdsje;
    @ExcelProperty("市州县区税务金额")
    private BigDecimal swjgje;
    @ExcelProperty("属地金额")
    private BigDecimal sdghje;
}
