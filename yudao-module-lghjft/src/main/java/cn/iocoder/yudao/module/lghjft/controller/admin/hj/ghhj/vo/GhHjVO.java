package cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 户籍管理对象 gh_hj
 */
@ExcelIgnoreUnannotated
@Schema(description = "管理后台 - 户籍管理 Response VO")
@Data
public class GhHjVO {

    /** 登记序号 */
    @ExcelProperty("登记序号")
    private String djxh;

    /** 工会机构代码 */
    @ExcelProperty("工会机构")
    private String deptId;

    /** 行业工会标志 */
    private String hyghbz;

    /** 社会信用代码 */
    @ExcelProperty("社会信用代码")
    private String shxydm;

    /** 缴费单位名称 */
    @ExcelProperty("缴费单位名称")
    private String nsrmc;

    /** 缴费单位简称 */
    private String nsrjc;

    /** 主管税务机关代码 */
    private String zgswjDm;

    /** 街道乡镇代码 */
    @ExcelProperty("街道乡镇代码")
    private String jdxzDm;

    /** 主管税务机关名称 */
    @ExcelProperty("主管税务机关名称")
    private String zgswjmc;

    /** 科所分局代码 */
    private String zgswskfjDm;

    /** 科所分局名称 */
    @ExcelProperty("科所分局名称")
    private String zgswskfjmc;

    /** 税管员代码 */
    private String ssglyDm;

    /** 税管员姓名 */
    @ExcelProperty("税管员")
    private String ssglyxm;

    /** 组织机构代码 */
    private String zzjglxDm;

    /** 组织机构名称 */
    @ExcelProperty("组织机构名称")
    private String zzjglxmc;

    /** 行业代码 */
    private String hyDm;

    /** 行业名称 */
    @ExcelProperty("行业名称")
    private String hymc;

    /** 登记注册类型代码 */
    private String djzclxDm;

    /** 登记注册类型名称 */
    @ExcelProperty("登记注册类型名称")
    private String djzclxmc;

    /** 单位隶属关系代码 */
    private String dwlsgxDm;

    /** 单位隶属关系名称 */
    @ExcelProperty("单位隶属关系名称")
    private String dwlsgxmc;

    /** 职工人数 */
    @ExcelProperty("职工人数")
    private BigDecimal zgrs;

    /** 缴费单位状态代码 */
    private String nsrztDm;

    /** 缴费单位状态名称 */
    @ExcelProperty("缴费单位状态名称")
    private String nsrztmc;

    /** 注册地址 */
    @ExcelProperty("注册地址")
    private String zcdz;

    /** 邮政编码 */
    private String yzbm;

    /** 联系人 */
    private String lxr;

    /** 联系电话 */
    private String lxdh;

    /** 工会类别 */
    @ExcelProperty("工会类别")
    private String ghlbDm;

    /** 系统类别 */
    @ExcelProperty("系统类别")
    private String xtlbDm;

    /** 建会缴纳筹备金标志 */
    @ExcelProperty("建会缴纳筹备金标志")
    private String hjfl8Dm;

    /** 成立工会标志 */
    @ExcelProperty("成立工会标志")
    private String clghbj;

    /** 成立工会日期 */
    @ExcelProperty("成立工会日期")
    @DateTimeFormat("yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date clghrq;

    /** 基层工会代码 */
    @ExcelProperty("基层工会代码")
    private String jcghdm;

    /** 基层工会名称 */
    @ExcelProperty("基层工会名称")
    private String jcghmc;

    /** 基层工会账号 */
    @ExcelProperty("基层工会账号")
    private String jcghzh;

    /** 基层工会户名 */
    @ExcelProperty("基层工会户名")
    private String jcghhm;

    /** 基层工会行号 */
    @ExcelProperty("基层工会行号")
    private String jcghhh;

    /** 基层工会银行 */
    @ExcelProperty("基层工会银行")
    private String jcghyh;

    /** 税务数据同步时间 */
    @ExcelProperty("税务数据同步时间")
    @DateTimeFormat("yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date sjtbSj;

    /** 备注 */
    private String bz;

    /** 纳税人识别号 */
    private String nsrsbh;
}