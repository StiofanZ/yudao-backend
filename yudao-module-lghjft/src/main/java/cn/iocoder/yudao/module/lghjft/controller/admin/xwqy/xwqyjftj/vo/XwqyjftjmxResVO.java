package cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjftj.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 小微经费统计明细 Response VO (v1: selectXwqyjftjmxList 对应的行级明细)
 */
@Schema(description = "管理后台 - 小微经费统计明细 Response VO")
@Data
@ExcelIgnoreUnannotated
public class XwqyjftjmxResVO {

    @Schema(description = "单位代码")
    @ExcelProperty("单位代码")
    private String dwdm;

    @Schema(description = "年度")
    @ExcelProperty("年度")
    private String nd;

    @Schema(description = "工会经费ID")
    @ExcelProperty("工会经费ID")
    private Long ghjfId;

    @Schema(description = "审批UUID")
    private String spuuid;

    @Schema(description = "登记序号")
    @ExcelProperty("登记序号")
    private String djxh;

    @Schema(description = "社会信用代码")
    @ExcelProperty("社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人名称")
    @ExcelProperty("纳税人名称")
    private String nsrmc;

    @Schema(description = "小微上报单位代码")
    private String xwsbdwdm;

    @Schema(description = "小微上报日期")
    private Date xwsbrq;

    @Schema(description = "小微类型")
    @ExcelProperty("小微类型")
    private String xwlx;

    @Schema(description = "纳税人简称")
    private String nsrjc;

    @Schema(description = "部门ID")
    @ExcelProperty("部门ID")
    private String deptId;

    @Schema(description = "工会机构行政级别")
    private String ghjgxzjb;

    @Schema(description = "成立工会标记")
    private String clghbj;

    @Schema(description = "行业工会标志")
    private String hyghbz;

    @Schema(description = "属地工会机构代码")
    private String sdghjgDm;

    @Schema(description = "属地工会机构行政级别")
    private String sdghjgxzjb;

    @Schema(description = "主管税务机关代码")
    private String zgswjDm;

    @Schema(description = "主管税务科分局代码")
    private String zgswskfjDm;

    @Schema(description = "所属管理员代码")
    private String ssglyDm;

    @Schema(description = "工会类别代码")
    private String ghlbDm;

    @Schema(description = "系统类别代码")
    private String xtlbDm;

    @Schema(description = "组织机构类型代码")
    private String zzjglxDm;

    @Schema(description = "行业代码")
    private String hyDm;

    @Schema(description = "登记注册类型代码")
    private String djzclxDm;

    @Schema(description = "单位隶属关系代码")
    private String dwlsgxDm;

    @Schema(description = "申报类别代码")
    private String sblbDm;

    @Schema(description = "纳税申报日期")
    private Date nssbrq;

    @Schema(description = "凭证序号")
    private String pzxh;

    @Schema(description = "凭证明细序号")
    private Long pzmxxh;

    @Schema(description = "所属期起")
    private Date skssqq;

    @Schema(description = "所属期止")
    private Date skssqz;

    @Schema(description = "征收税务机关代码")
    private String zsswjgDm;

    @Schema(description = "税款所属税务机关代码")
    private String skssswjgDm;

    @Schema(description = "征收品目代码")
    @ExcelProperty("征收品目代码")
    private String zspmDm;

    @Schema(description = "工资总额")
    @ExcelProperty("工资总额")
    private BigDecimal gzze;

    @Schema(description = "税率")
    private BigDecimal sl;

    @Schema(description = "应纳税额")
    private BigDecimal ynse;

    @Schema(description = "减免税额")
    private BigDecimal jmse;

    @Schema(description = "已缴税额")
    private BigDecimal yjse;

    @Schema(description = "应补退税额")
    @ExcelProperty("应补退税额")
    private BigDecimal ybtse;

    @Schema(description = "应付金额")
    @ExcelProperty("应付金额")
    private BigDecimal yfje;

    @Schema(description = "国库支付金额")
    @ExcelProperty("国库支付金额")
    private BigDecimal gkzfje;

    @Schema(description = "国家退还金额")
    @ExcelProperty("国家退还金额")
    private BigDecimal gjthje;

    @Schema(description = "结算标记")
    @ExcelProperty("结算标记")
    private String jsbj;

    @Schema(description = "结算日期")
    private Date jsrq;

    @Schema(description = "基础归户金额")
    @ExcelProperty("基础归户金额")
    private BigDecimal jcghje;

    @Schema(description = "承包金金额")
    @ExcelProperty("承包金金额")
    private BigDecimal cbjje;

    @Schema(description = "行业归户金额")
    @ExcelProperty("行业归户金额")
    private BigDecimal hyghje;

    @Schema(description = "乡镇归户金额")
    @ExcelProperty("乡镇归户金额")
    private BigDecimal xjghje;

    @Schema(description = "市局归户金额")
    @ExcelProperty("市局归户金额")
    private BigDecimal sjghje;

    @Schema(description = "省直归户金额")
    @ExcelProperty("省直归户金额")
    private BigDecimal szghje;

    @Schema(description = "全国归户金额")
    @ExcelProperty("全国归户金额")
    private BigDecimal qgghje;

    @Schema(description = "所得税金额")
    @ExcelProperty("所得税金额")
    private BigDecimal sdsje;

    @Schema(description = "税务机关金额")
    @ExcelProperty("税务机关金额")
    private BigDecimal swjgje;

    @Schema(description = "承包金退还标记")
    private String cbjthbj;

    @Schema(description = "划款批次号")
    private String hkpch;

    @Schema(description = "入库日期")
    private Date rkrq;
}
