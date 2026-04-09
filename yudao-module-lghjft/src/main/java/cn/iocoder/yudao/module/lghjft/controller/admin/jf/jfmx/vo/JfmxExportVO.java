package cn.iocoder.yudao.module.lghjft.controller.admin.jf.jfmx.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;

@Schema(description = "管理后台 - 经费明细导出 VO")
@Data
public class JfmxExportVO {

    @ExcelProperty("税票ID")
    private String spuuid;

    @ExcelProperty("登记序号")
    private String djxh;

    @ExcelProperty("社会信用代码")
    private String shxydm;

    @ExcelProperty("纳税人名称")
    private String nsrmc;

    @ExcelProperty(value = "工会机构", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    private String deptId;

    @ExcelProperty(value = "主管税务局", converter = DictConvert.class)
    @DictFormat("sys_swjg_type")
    private String zgswjDm;

    @ExcelProperty(value = "工会类别", converter = DictConvert.class)
    @DictFormat("sys_ghlb_type")
    private String ghlbDm;

    @ExcelProperty(value = "系统类别", converter = DictConvert.class)
    @DictFormat("sys_xtlb_type")
    private String xtlbDm;

    @ExcelProperty(value = "小微类型", converter = DictConvert.class)
    @DictFormat("sys_xwlx")
    private String xwlx;

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty("申报日期")
    private Date nssbrq;

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty("所属期起")
    private Date skssqq;

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty("所属期止")
    private Date skssqz;

    @ExcelProperty(value = "征收品目代码", converter = DictConvert.class)
    @DictFormat("sys_zspm_type")
    private String zspmDm;

    @ExcelProperty("工资总额")
    private BigDecimal gzze;

    @ExcelProperty("税率")
    private BigDecimal sl;

    @ExcelProperty("缴费金额")
    private BigDecimal ybtse;

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty("入库日期")
    private Date rkrq;

    @ExcelProperty(value = "结算标记", converter = DictConvert.class)
    @DictFormat("sys_jsbj_type")
    private String jsbj;

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty("结算日期")
    private Date jsrq;

    @ExcelProperty(value = "基层经费拨付状态", converter = DictConvert.class)
    @DictFormat("sys_cbjcl_type")
    private String cbjthbj;

    @ExcelProperty("基层工会账户")
    private String jcghzh;

    @ExcelProperty("基层工会户名")
    private String jcghhm;

    @ExcelProperty("基层工会行号")
    private String jcghhh;

    @ExcelProperty("基层工会比例")
    private BigDecimal jcghbl;

    @ExcelProperty("基层工会金额")
    private BigDecimal jcghje;

    @ExcelProperty("筹备金比例")
    private BigDecimal cbjbl;

    @ExcelProperty("筹备金金额")
    private BigDecimal cbjje;

    @ExcelProperty("行业工会比例")
    private BigDecimal hyghbl;

    @ExcelProperty("行业工会金额")
    private BigDecimal hyghje;

    @ExcelProperty("县级工会比例")
    private BigDecimal xjghbl;

    @ExcelProperty("县级工会金额")
    private BigDecimal xjghje;

    @ExcelProperty("市级工会比例")
    private BigDecimal sjghbl;

    @ExcelProperty("市级工会金额")
    private BigDecimal sjghje;

    @ExcelProperty("省总工会比例")
    private BigDecimal szghbl;

    @ExcelProperty("省总工会金额")
    private BigDecimal szghje;

    @ExcelProperty("全总比例")
    private BigDecimal qgghbl;

    @ExcelProperty("全总金额")
    private BigDecimal qgghje;

    @ExcelProperty("省税局比例")
    private BigDecimal sdsbl;

    @ExcelProperty("省税局金额")
    private BigDecimal sdsje;

    @ExcelProperty("税务机关比例")
    private BigDecimal swjgbl;

    @ExcelProperty("税务机关金额")
    private BigDecimal swjgje;

    @ExcelProperty("划款批次号")
    private String hkpch;

    @ExcelProperty("备注")
    private String bz;

    @ExcelProperty("属地工会比例")
    private BigDecimal sdghbl;

    @ExcelProperty("属地工会金额")
    private BigDecimal sdghje;
}