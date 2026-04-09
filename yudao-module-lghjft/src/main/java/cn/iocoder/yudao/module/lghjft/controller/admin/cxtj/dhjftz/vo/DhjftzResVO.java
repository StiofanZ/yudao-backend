package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dhjftz.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;

/**
 * 单户经费台账 Response VO — 映射 v1 gh_hj 表字段
 * Excel 导出字段与 V1 @Excel 注解对齐
 */
@Schema(description = "管理后台 - 到户经费台账 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DhjftzResVO {

    @ExcelProperty("登记序号")
    @Schema(description = "登记序号")
    private String djxh;

    @ExcelProperty(value = "工会机构代码", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "行业工会标志")
    private String hyghbz;

    @ExcelProperty("社会信用代码")
    @Schema(description = "社会信用代码")
    private String shxydm;

    @ExcelProperty("缴费单位名称")
    @Schema(description = "纳税人名称")
    private String nsrmc;

    @ExcelProperty("缴费单位简称")
    @Schema(description = "纳税人简称")
    private String nsrjc;

    @ExcelProperty(value = "主管税务机关代码", converter = DictConvert.class)
    @DictFormat("sys_swjg_type")
    @Schema(description = "主管税务局代码")
    private String zgswjDm;

    @Schema(description = "主管税务局名称")
    private String zgswjmc;

    @ExcelProperty("科所分局代码")
    @Schema(description = "主管税务所科分局代码")
    private String zgswskfjDm;

    @ExcelProperty("科所分局名称")
    @Schema(description = "主管税务所科分局名称")
    private String zgswskfjmc;

    @Schema(description = "所属管理员代码")
    private String ssglyDm;

    @ExcelProperty("税管员姓名")
    @Schema(description = "所属管理员姓名")
    private String ssglyxm;

    @ExcelProperty("组织机构代码")
    @Schema(description = "组织机构类型代码")
    private String zzjglxDm;

    @ExcelProperty("组织机构名称")
    @Schema(description = "组织机构类型名称")
    private String zzjglxmc;

    @Schema(description = "行业代码")
    private String hyDm;

    @ExcelProperty("行业名称")
    @Schema(description = "行业名称")
    private String hymc;

    @Schema(description = "登记注册类型代码")
    private String djzclxDm;

    @ExcelProperty("登记注册类型名称")
    @Schema(description = "登记注册类型名称")
    private String djzclxmc;

    @Schema(description = "单位隶属关系代码")
    private String dwlsgxDm;

    @ExcelProperty("单位隶属关系名称")
    @Schema(description = "单位隶属关系名称")
    private String dwlsgxmc;

    @ExcelProperty("总共人数")
    @Schema(description = "职工人数")
    private BigDecimal zgrs;

    @ExcelProperty("缴费单位状态代码")
    @Schema(description = "纳税人状态代码")
    private String nsrztDm;

    @ExcelProperty("缴费单位状态名称")
    @Schema(description = "纳税人状态名称")
    private String nsrztmc;

    @ExcelProperty("发证日期")
    @Schema(description = "发证日期")
    private LocalDateTime fzcrq;

    @ExcelProperty("注销日期")
    @Schema(description = "注销日期")
    private LocalDateTime zxrq;

    @ExcelProperty("注册地址")
    @Schema(description = "注册地址")
    private String zcdz;

    @ExcelProperty("邮政编码")
    @Schema(description = "邮政编码")
    private String yzbm;

    @ExcelProperty("联系人")
    @Schema(description = "联系人")
    private String lxr;

    @ExcelProperty("联系电话")
    @Schema(description = "联系电话")
    private String lxdh;

    @Schema(description = "工会类别代码")
    private String ghlbDm;

    @Schema(description = "系统类别代码")
    private String xtlbDm;

    @ExcelProperty("户籍分类")
    @Schema(description = "户籍分类1")
    private String hjfl1Dm;

    @Schema(description = "户籍分类2")
    private String hjfl2Dm;

    @Schema(description = "户籍分类3")
    private String hjfl3Dm;

    @Schema(description = "户籍分类4")
    private String hjfl4Dm;

    @Schema(description = "户籍分类5")
    private String hjfl5Dm;

    @Schema(description = "户籍分类6")
    private String hjfl6Dm;

    @ExcelProperty("小微上报日期")
    @Schema(description = "小微上报日期")
    private String hjfl7Dm;

    @ExcelProperty("建会缴纳筹备金标志")
    @Schema(description = "建会缴纳筹备金标志")
    private String hjfl8Dm;

    @ExcelProperty("小微标志")
    @Schema(description = "小微标志")
    private String hjfl9Dm;

    @Schema(description = "上代工会机构代码")
    private String sdghjgDm;

    @ExcelProperty("成立工会标志")
    @Schema(description = "成立工会标记")
    private String clghbj;

    @ExcelProperty("成立工会日期")
    @Schema(description = "成立工会日期")
    private LocalDateTime clghrq;

    @ExcelProperty("基层工会代码")
    @Schema(description = "基层工会代码")
    private String jcghdm;

    @ExcelProperty("基层工会名称")
    @Schema(description = "基层工会名称")
    private String jcghmc;

    @ExcelProperty("基层工会账号")
    @Schema(description = "基层工会账户")
    private String jcghzh;

    @ExcelProperty("基层工会户名")
    @Schema(description = "基层工会户名")
    private String jcghhm;

    @ExcelProperty("基层工会行号")
    @Schema(description = "基层工会行号")
    private String jcghhh;

    @ExcelProperty("基层工会银行")
    @Schema(description = "基层工会银行")
    private String jcghyh;

    @ExcelProperty("备注")
    @Schema(description = "备注")
    private String bz;

    @Schema(description = "校验码")
    private String jym;

    @Schema(description = "纳税人识别号")
    private String nsrsbh;
}
