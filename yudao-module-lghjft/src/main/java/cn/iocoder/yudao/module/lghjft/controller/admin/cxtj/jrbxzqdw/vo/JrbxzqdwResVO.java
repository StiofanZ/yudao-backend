package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jrbxzqdw.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;

@Schema(description = "管理后台 - 金融保险证券单位 Response VO")
@Data
@ExcelIgnoreUnannotated
public class JrbxzqdwResVO {

    @Schema(description = "id")
    private String id;

    @Schema(description = "单位代码")
    @ExcelProperty(value = "默认工会机构", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    private String dwdm;

    @Schema(description = "主管工会（来自 gh_hj.dept_id）")
    @ExcelProperty(value = "主管工会", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    private String deptId;

    @Schema(description = "社会信用代码")
    @ExcelProperty("社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人识别号")
    @ExcelProperty("纳税人识别号")
    private String nsrsbh;

    @Schema(description = "纳税人名称")
    @ExcelProperty("纳税人名称")
    private String nsrmc;

    @Schema(description = "登记序号")
    @ExcelProperty("登记序号")
    private String djxh;

    @Schema(description = "中国金融工会在甘单位标志")
    @ExcelProperty(value = "中国金融工会在甘单位标志", converter = DictConvert.class)
    @DictFormat("sys_yes_no")
    private String zgjrghzgdwbz;

    @Schema(description = "工会类别代码")
    private String ghlbDm;

    @Schema(description = "系统类别代码")
    private String xtlbDm;

    @Schema(description = "纳税人状态代码")
    private String nsrztDm;

    @Schema(description = "登记注册类型代码")
    private String djzclxDm;

    @Schema(description = "登记注册类型名称")
    @ExcelProperty("登记注册类型")
    private String djzclxmc;

    @Schema(description = "生产经营地址")
    @ExcelProperty("生产经营地址")
    private String scjydz;

    @Schema(description = "行政区划代码")
    private String scjydzxzqhszDm;

    @Schema(description = "行政区划名称")
    @ExcelProperty("行政区划")
    private String xzqhmc;

    @Schema(description = "街道乡镇代码")
    private String jdxzDm;

    @Schema(description = "行业代码")
    private String hyDm;

    @Schema(description = "行业名称")
    @ExcelProperty("行业名称")
    private String hymc;

    @Schema(description = "登记机关代码")
    private String djjgDm;

    @Schema(description = "登记机关名称")
    private String djjgmc;

    @Schema(description = "主管税务局代码")
    private String zgswjDm;

    @Schema(description = "主管税务局名称")
    private String zgswjmc;

    @Schema(description = "主管税务所代码")
    private String zgswskfjDm;

    @Schema(description = "科所分局名称")
    private String zgksfjmc;

    @Schema(description = "财务负责人姓名")
    @ExcelProperty("财务负责人姓名")
    private String cwfzrxm;

    @Schema(description = "财务负责人固定电话")
    @ExcelProperty("财务负责人固定电话")
    private String cwfzrgddh;

    @Schema(description = "财务负责人移动电话")
    @ExcelProperty("财务负责人移动电话")
    private String cwfzryddh;

    @Schema(description = "从业人数")
    @ExcelProperty("从业人数")
    private BigDecimal cyrs;

    @Schema(description = "备注信息")
    @ExcelProperty("备注信息")
    private String bz;

    @Schema(description = "核实结果")
    @ExcelProperty(value = "核实结果", converter = DictConvert.class)
    @DictFormat("sys_hsjg")
    private String hsjg;

    @Schema(description = "创建人")
    private String createBy;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "修改人")
    private String updateBy;

    @Schema(description = "修改时间")
    private LocalDateTime updateTime;

    @Schema(description = "工资总额")
    @ExcelProperty("工资总额")
    private BigDecimal gzze;

    @Schema(description = "2025缴费笔数")
    @ExcelProperty("2025缴费笔数")
    private Long bs;

    @Schema(description = "2025缴费金额")
    @ExcelProperty("2025缴费金额")
    private BigDecimal je;

    @Schema(description = "2025平均税率")
    @ExcelProperty("2025平均税率")
    private String sl;

    @Schema(description = "2024缴费笔数")
    @ExcelProperty("2024缴费笔数")
    private Long bs2024;

    @Schema(description = "2024缴费金额")
    @ExcelProperty("2024缴费金额")
    private BigDecimal je2024;

    @Schema(description = "2023缴费笔数")
    @ExcelProperty("2023缴费笔数")
    private Long bs2023;

    @Schema(description = "2023缴费金额")
    @ExcelProperty("2023缴费金额")
    private BigDecimal je2023;

    @Schema(description = "2022缴费笔数")
    @ExcelProperty("2022缴费笔数")
    private Long bs2022;

    @Schema(description = "2022缴费金额")
    @ExcelProperty("2022缴费金额")
    private BigDecimal je2022;

    @Schema(description = "2021缴费笔数")
    @ExcelProperty("2021缴费笔数")
    private Long bs2021;

    @Schema(description = "2021缴费金额")
    @ExcelProperty("2021缴费金额")
    private BigDecimal je2021;

    @Schema(description = "2020缴费笔数")
    @ExcelProperty("2020缴费笔数")
    private Long bs2020;

    @Schema(description = "2020缴费金额")
    @ExcelProperty("2020缴费金额")
    private BigDecimal je2020;
}
