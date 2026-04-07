package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 经费台账明细 Response VO — v1 selectJftzmxList 映射 gh_jf LEFT JOIN gh_hj
 */
@Schema(description = "管理后台 - 经费台账明细 Response VO")
@Data
@ExcelIgnoreUnannotated
public class JftzmxResVO {

    @Schema(description = "spuuid")
    private String spuuid;

    @ExcelProperty("登记序号")
    @Schema(description = "djxh")
    private String djxh;

    @Schema(description = "swjgmc")
    private String swjgmc;

    @Schema(description = "ghjgmc")
    private String ghjgmc;

    @ExcelProperty("社会信用代码")
    @Schema(description = "shxydm")
    private String shxydm;

    @ExcelProperty("纳税人名称")
    @Schema(description = "nsrmc")
    private String nsrmc;

    @Schema(description = "nsrjc")
    private String nsrjc;

    @Schema(description = "deptId")
    private String deptId;

    @Schema(description = "ghjgxzjb")
    private String ghjgxzjb;

    @Schema(description = "clghbj")
    private String clghbj;

    @Schema(description = "hyghbz")
    private String hyghbz;

    @Schema(description = "sdghjgDm")
    private String sdghjgDm;

    @Schema(description = "sdghjgxzjb")
    private String sdghjgxzjb;

    @Schema(description = "zgswjDm")
    private String zgswjDm;

    @Schema(description = "zgswskfjDm")
    private String zgswskfjDm;

    @Schema(description = "ssglyDm")
    private String ssglyDm;

    @ExcelProperty("工会类别代码")
    @Schema(description = "ghlbDm")
    private String ghlbDm;

    @ExcelProperty("系统类别代码")
    @Schema(description = "xtlbDm")
    private String xtlbDm;

    @Schema(description = "zzjglxDm")
    private String zzjglxDm;

    @Schema(description = "hyDm")
    private String hyDm;

    @Schema(description = "djzclxDm")
    private String djzclxDm;

    @Schema(description = "dwlsgxDm")
    private String dwlsgxDm;

    @Schema(description = "sblbDm")
    private String sblbDm;

    @ExcelProperty("申报日期")
    @Schema(description = "nssbrq")
    private String nssbrq;

    @Schema(description = "pzxh")
    private String pzxh;

    @Schema(description = "pzmxxh")
    private Long pzmxxh;

    @ExcelProperty("所属期起")
    @Schema(description = "skssqq")
    private String skssqq;

    @ExcelProperty("所属期止")
    @Schema(description = "skssqz")
    private String skssqz;

    @Schema(description = "zsswjgDm")
    private String zsswjgDm;

    @Schema(description = "skssswjgDm")
    private String skssswjgDm;

    @Schema(description = "skgkDm")
    private String skgkDm;

    @Schema(description = "dzsphm")
    private String dzsphm;

    @Schema(description = "pzzlDm")
    private String pzzlDm;

    @Schema(description = "pzzgDm")
    private String pzzgDm;

    @Schema(description = "pzhm")
    private String pzhm;

    @Schema(description = "kpyDm")
    private String kpyDm;

    @Schema(description = "kpyXm")
    private String kpyXm;

    @ExcelProperty("征收品目代码")
    @Schema(description = "zspmDm")
    private String zspmDm;

    @Schema(description = "zszmDm")
    private String zszmDm;

    @Schema(description = "yskmDm")
    private String yskmDm;

    @ExcelProperty("工资总额")
    @Schema(description = "gzze")
    private BigDecimal gzze;

    @ExcelProperty("税率")
    @Schema(description = "sl")
    private BigDecimal sl;

    @Schema(description = "ynse")
    private BigDecimal ynse;

    @Schema(description = "jmse")
    private BigDecimal jmse;

    @Schema(description = "yjse")
    private BigDecimal yjse;

    @ExcelProperty("缴费金额")
    @Schema(description = "ybtse")
    private BigDecimal ybtse;

    @Schema(description = "jkfsDm")
    private String jkfsDm;

    @Schema(description = "jfhb")
    private String jfhb;

    @Schema(description = "jfzh")
    private String jfzh;

    @Schema(description = "jfhm")
    private String jfhm;

    @Schema(description = "jfhh")
    private String jfhh;

    @Schema(description = "jfyh")
    private String jfyh;

    @ExcelProperty("入库日期")
    @Schema(description = "rkrq")
    private String rkrq;

    @ExcelProperty("结算标记")
    @Schema(description = "jsbj")
    private String jsbj;

    @ExcelProperty("结算日期")
    @Schema(description = "jsrq")
    private String jsrq;

    @Schema(description = "jsczy")
    private String jsczy;

    @ExcelProperty("基层工会账户")
    @Schema(description = "jcghzh")
    private String jcghzh;
    @ExcelProperty("基层工会户名")
    @Schema(description = "jcghhm")
    private String jcghhm;
    @ExcelProperty("基层工会行号")
    @Schema(description = "jcghhh")
    private String jcghhh;
    @ExcelProperty("基层工会比例")
    @Schema(description = "jcghbl")
    private BigDecimal jcghbl;
    @ExcelProperty("基层工会金额")
    @Schema(description = "jcghje")
    private BigDecimal jcghje;

    @Schema(description = "cbjzh")
    private String cbjzh;
    @Schema(description = "cbjhm")
    private String cbjhm;
    @Schema(description = "cbjhh")
    private String cbjhh;
    @ExcelProperty("筹备金比例")
    @Schema(description = "cbjbl")
    private BigDecimal cbjbl;
    @ExcelProperty("筹备金金额")
    @Schema(description = "cbjje")
    private BigDecimal cbjje;

    @Schema(description = "hyghzh")
    private String hyghzh;
    @Schema(description = "hyghhm")
    private String hyghhm;
    @Schema(description = "hyghhh")
    private String hyghhh;
    @ExcelProperty("行业工会比例")
    @Schema(description = "hyghbl")
    private BigDecimal hyghbl;
    @ExcelProperty("行业工会金额")
    @Schema(description = "hyghje")
    private BigDecimal hyghje;

    @Schema(description = "xjghzh")
    private String xjghzh;
    @Schema(description = "xjghhm")
    private String xjghhm;
    @Schema(description = "xjghhh")
    private String xjghhh;
    @ExcelProperty("县级工会比例")
    @Schema(description = "xjghbl")
    private BigDecimal xjghbl;
    @ExcelProperty("县级工会金额")
    @Schema(description = "xjghje")
    private BigDecimal xjghje;

    @Schema(description = "sjghzh")
    private String sjghzh;
    @Schema(description = "sjghhm")
    private String sjghhm;
    @Schema(description = "sjghhh")
    private String sjghhh;
    @ExcelProperty("市级工会比例")
    @Schema(description = "sjghbl")
    private BigDecimal sjghbl;
    @ExcelProperty("市级工会金额")
    @Schema(description = "sjghje")
    private BigDecimal sjghje;

    @Schema(description = "szghzh")
    private String szghzh;
    @Schema(description = "szghhm")
    private String szghhm;
    @Schema(description = "szghhh")
    private String szghhh;
    @ExcelProperty("省总工会比例")
    @Schema(description = "szghbl")
    private BigDecimal szghbl;
    @ExcelProperty("省总工会金额")
    @Schema(description = "szghje")
    private BigDecimal szghje;

    @Schema(description = "qgghzh")
    private String qgghzh;
    @Schema(description = "qgghhm")
    private String qgghhm;
    @Schema(description = "qgghhh")
    private String qgghhh;
    @ExcelProperty("全总比例")
    @Schema(description = "qgghbl")
    private BigDecimal qgghbl;
    @ExcelProperty("全总金额")
    @Schema(description = "qgghje")
    private BigDecimal qgghje;

    @Schema(description = "jcjzh")
    private String jcjzh;
    @Schema(description = "jcjhm")
    private String jcjhm;
    @Schema(description = "jcjhh")
    private String jcjhh;
    @Schema(description = "jcjbl")
    private BigDecimal jcjbl;
    @Schema(description = "jcjje")
    private BigDecimal jcjje;
    @Schema(description = "jcwsh")
    private String jcwsh;

    @Schema(description = "sdszh")
    private String sdszh;
    @Schema(description = "sdshm")
    private String sdshm;
    @Schema(description = "sdshh")
    private String sdshh;
    @ExcelProperty("省税局比例")
    @Schema(description = "sdsbl")
    private BigDecimal sdsbl;
    @ExcelProperty("省税局金额")
    @Schema(description = "sdsje")
    private BigDecimal sdsje;

    @Schema(description = "swjgzh")
    private String swjgzh;
    @Schema(description = "swjghm")
    private String swjghm;
    @Schema(description = "swjghh")
    private String swjghh;
    @ExcelProperty("税务机关比例")
    @Schema(description = "swjgbl")
    private BigDecimal swjgbl;
    @ExcelProperty("税务机关金额")
    @Schema(description = "swjgje")
    private BigDecimal swjgje;

    @ExcelProperty("基层经费拨付状态")
    @Schema(description = "cbjthbj")
    private String cbjthbj;
    @Schema(description = "cbjthrq")
    private String cbjthrq;
    @Schema(description = "cbjthczy")
    private String cbjthczy;

    @Schema(description = "jym")
    private String jym;
    @ExcelProperty("划款批次号")
    @Schema(description = "hkpch")
    private String hkpch;
    @ExcelProperty("备注")
    @Schema(description = "bz")
    private String bz;
    @Schema(description = "bluuid")
    private String bluuid;

    @ExcelProperty("属地工会比例")
    @Schema(description = "sdghbl")
    private BigDecimal sdghbl;
    @ExcelProperty("属地工会金额")
    @Schema(description = "sdghje")
    private BigDecimal sdghje;
    @Schema(description = "sdghzh")
    private String sdghzh;
    @Schema(description = "sdghhm")
    private String sdghhm;
    @Schema(description = "sdghhh")
    private String sdghhh;

    @Schema(description = "createBy")
    private String createBy;
    @Schema(description = "createTime")
    private LocalDateTime createTime;
    @Schema(description = "updateBy")
    private String updateBy;
    @Schema(description = "updateTime")
    private LocalDateTime updateTime;

    /* ---- gh_hj join fields ---- */
    @ExcelProperty("小微类型")
    @Schema(description = "小微类型")
    private String xwlx;
    @Schema(description = "小额类型23")
    private String xelx23;
    @Schema(description = "小额类型24")
    private String xelx24;
    @Schema(description = "小额类型25")
    private String xelx25;

    @Schema(description = "年度")
    private String nd;
}
