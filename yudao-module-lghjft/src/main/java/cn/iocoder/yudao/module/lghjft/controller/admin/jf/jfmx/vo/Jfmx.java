package cn.iocoder.yudao.module.lghjft.controller.admin.jf.jfmx.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.format.DateTimeFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;

@ExcelIgnoreUnannotated
@Schema(description = "管理后台 - 经费明细 Response VO")
@Data
public class Jfmx {

    @ExcelProperty("税票ID")
    private String spuuid;

    @ExcelProperty("登记序号")
    private String djxh;

    private String ghjgmc;

    @ExcelProperty("社会信用代码")
    private String shxydm;

    @ExcelProperty("纳税人名称")
    private String nsrmc;

    private String nsrjc;

    @ExcelProperty(value = "工会机构", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    private String deptId;

    private String ghjgxzjb;
    private String clghbj;
    private String hyghbz;
    private String sdghjgDm;
    private String sdghjgxzjb;

    @ExcelProperty(value = "主管税务局", converter = DictConvert.class)
    @DictFormat("sys_swjg_type")
    private String zgswjDm;

    private String zgswskfjDm;
    private String ssglyDm;

    @ExcelProperty(value = "工会类别", converter = DictConvert.class)
    @DictFormat("sys_ghlb_type")
    private String ghlbDm;

    @ExcelProperty(value = "系统类别", converter = DictConvert.class)
    @DictFormat("sys_xtlb_type")
    private String xtlbDm;

    private String zzjglxDm;
    private String hyDm;
    private String djzclxDm;
    private String dwlsgxDm;
    private String sblbDm;

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty("申报日期")
    private Date nssbrq;

    private String pzxh;
    private Long pzmxxh;

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty("所属期起")
    private Date skssqq;

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty("所属期止")
    private Date skssqz;

    private String zsswjgDm;
    private String skssswjgDm;
    private String skgkDm;
    private String dzsphm;
    private String pzzlDm;
    private String pzzgDm;
    private String pzhm;
    private String kpyDm;
    private String kpyXm;

    @ExcelProperty(value = "征收品目代码", converter = DictConvert.class)
    @DictFormat("sys_zspm_type")
    private String zspmDm;

    private String zszmDm;
    private String yskmDm;

    @ExcelProperty("工资总额")
    private BigDecimal gzze;

    @ExcelProperty("税率")
    private BigDecimal sl;

    private BigDecimal ynse;
    private BigDecimal jmse;
    private BigDecimal yjse;

    @ExcelProperty("缴费金额")
    private BigDecimal ybtse;

    private String jkfsDm;
    private String jfhb;
    private String jfzh;
    private String jfhm;
    private String jfhh;
    private String jfyh;

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty("入库日期")
    private Date rkrq;

    @ExcelProperty(value = "结算标记", converter = DictConvert.class)
    @DictFormat("sys_jsbj_type")
    private String jsbj;

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty("结算日期")
    private Date jsrq;

    private String jsczy;

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

    @ExcelProperty("筹备金账号")
    private String cbjzh;

    @ExcelProperty("筹备金户名")
    private String cbjhm;

    @ExcelProperty("筹备金行号")
    private String cbjhh;

    @ExcelProperty("筹备金比例")
    private BigDecimal cbjbl;

    @ExcelProperty("筹备金金额")
    private BigDecimal cbjje;

    @ExcelProperty("行业工会账号")
    private String hyghzh;

    @ExcelProperty("行业工会户名")
    private String hyghhm;

    @ExcelProperty("行业工会行号")
    private String hyghhh;

    @ExcelProperty("行业工会比例")
    private BigDecimal hyghbl;

    @ExcelProperty("行业工会金额")
    private BigDecimal hyghje;

    @ExcelProperty("县级工会账号")
    private String xjghzh;

    @ExcelProperty("县级工会户名")
    private String xjghhm;

    @ExcelProperty("县级工会行号")
    private String xjghhh;

    @ExcelProperty("县级工会比例")
    private BigDecimal xjghbl;

    @ExcelProperty("县级工会金额")
    private BigDecimal xjghje;

    @ExcelProperty("市级工会账号")
    private String sjghzh;

    @ExcelProperty("市级工会户名")
    private String sjghhm;

    @ExcelProperty("市级工会行号")
    private String sjghhh;

    @ExcelProperty("市级工会比例")
    private BigDecimal sjghbl;

    @ExcelProperty("市级工会金额")
    private BigDecimal sjghje;

    @ExcelProperty("省总工会账号")
    private String szghzh;

    @ExcelProperty("省总工会户名")
    private String szghhm;

    @ExcelProperty("省总工会行号")
    private String szghhh;

    @ExcelProperty("省总工会比例")
    private BigDecimal szghbl;

    @ExcelProperty("省总工会金额")
    private BigDecimal szghje;

    @ExcelProperty("全总账号")
    private String qgghzh;

    @ExcelProperty("全总户名")
    private String qgghhm;

    @ExcelProperty("全总行号")
    private String qgghhh;

    @ExcelProperty("全总比例")
    private BigDecimal qgghbl;

    @ExcelProperty("全总金额")
    private BigDecimal qgghje;

    private String jcjzh;
    private String jcjhm;
    private String jcjhh;
    private BigDecimal jcjbl;
    private BigDecimal jcjje;
    private String jcwsh;

    @ExcelProperty("省税局账号")
    private String sdszh;

    @ExcelProperty("省税局户名")
    private String sdshm;

    @ExcelProperty("省税局行号")
    private String sdshh;

    @ExcelProperty("省税局比例")
    private BigDecimal sdsbl;

    @ExcelProperty("省税局金额")
    private BigDecimal sdsje;

    @ExcelProperty("税务机关账号")
    private String swjgzh;

    @ExcelProperty("税务机关户名")
    private String swjghm;

    @ExcelProperty("税务机关行号")
    private String swjghh;

    @ExcelProperty("税务机关比例")
    private BigDecimal swjgbl;

    @ExcelProperty("税务机关金额")
    private BigDecimal swjgje;

    @ExcelProperty(value = "基层经费拨付状态", converter = DictConvert.class)
    @DictFormat("sys_cbjcl_type")
    private String cbjthbj;

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty("处理时间")
    private Date cbjthrq;

    @ExcelProperty("操作员")
    private String cbjthczy;

    private String jym;

    @ExcelProperty("划款批次号")
    private String hkpch;

    @ExcelProperty("备注")
    private String bz;

    private String bluuid;

    @ExcelProperty("属地工会账号")
    private String sdghzh;

    @ExcelProperty("属地工会户名")
    private String sdghhm;

    @ExcelProperty("属地工会行号")
    private String sdghhh;

    @ExcelProperty("属地工会比例")
    private BigDecimal sdghbl;

    @ExcelProperty("属地工会金额")
    private BigDecimal sdghje;

    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;

    // gh_hj 关联字段（LEFT JOIN）
    private String xwlx;
    private String xelx23;
    private String xelx24;
    private String xelx25;

    // 分页字段
    private Integer pageNo;
    private Integer pageSize;
    private Integer offset;
    private Integer limit;

}