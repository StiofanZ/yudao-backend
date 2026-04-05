package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 经费明细(查询统计) Response VO — v1 selectJfmxList 映射 gh_jf LEFT JOIN gh_hj
 */
@Schema(description = "管理后台 - 经费明细(查询统计) Response VO")
@Data
@ExcelIgnoreUnannotated
public class JfmxResVO {

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

    @Schema(description = "ghlbDm")
    private String ghlbDm;

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

    @Schema(description = "nssbrq")
    private String nssbrq;

    @Schema(description = "pzxh")
    private String pzxh;

    @Schema(description = "pzmxxh")
    private Long pzmxxh;

    @Schema(description = "skssqq")
    private String skssqq;

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

    @Schema(description = "zspmDm")
    private String zspmDm;

    @Schema(description = "zszmDm")
    private String zszmDm;

    @Schema(description = "yskmDm")
    private String yskmDm;

    @ExcelProperty("工资总额")
    @Schema(description = "gzze")
    private BigDecimal gzze;

    @Schema(description = "sl")
    private BigDecimal sl;

    @Schema(description = "ynse")
    private BigDecimal ynse;

    @Schema(description = "jmse")
    private BigDecimal jmse;

    @Schema(description = "yjse")
    private BigDecimal yjse;

    @ExcelProperty("应补退税额")
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

    @Schema(description = "rkrq")
    private String rkrq;

    @Schema(description = "jsbj")
    private String jsbj;

    @Schema(description = "jsrq")
    private String jsrq;

    @Schema(description = "jsczy")
    private String jsczy;

    @ExcelProperty("基层工会账号")
    @Schema(description = "jcghzh")
    private String jcghzh;
    @Schema(description = "jcghhm")
    private String jcghhm;
    @Schema(description = "jcghhh")
    private String jcghhh;
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
    @Schema(description = "hyghbl")
    private BigDecimal hyghbl;
    @Schema(description = "hyghje")
    private BigDecimal hyghje;

    @Schema(description = "xjghzh")
    private String xjghzh;
    @Schema(description = "xjghhm")
    private String xjghhm;
    @Schema(description = "xjghhh")
    private String xjghhh;
    @Schema(description = "xjghbl")
    private BigDecimal xjghbl;
    @Schema(description = "xjghje")
    private BigDecimal xjghje;

    @Schema(description = "sjghzh")
    private String sjghzh;
    @Schema(description = "sjghhm")
    private String sjghhm;
    @Schema(description = "sjghhh")
    private String sjghhh;
    @Schema(description = "sjghbl")
    private BigDecimal sjghbl;
    @Schema(description = "sjghje")
    private BigDecimal sjghje;

    @Schema(description = "szghzh")
    private String szghzh;
    @Schema(description = "szghhm")
    private String szghhm;
    @Schema(description = "szghhh")
    private String szghhh;
    @Schema(description = "szghbl")
    private BigDecimal szghbl;
    @Schema(description = "szghje")
    private BigDecimal szghje;

    @Schema(description = "qgghzh")
    private String qgghzh;
    @Schema(description = "qgghhm")
    private String qgghhm;
    @Schema(description = "qgghhh")
    private String qgghhh;
    @Schema(description = "qgghbl")
    private BigDecimal qgghbl;
    @Schema(description = "qgghje")
    private BigDecimal qgghje;

    @Schema(description = "sdszh")
    private String sdszh;
    @Schema(description = "sdshm")
    private String sdshm;
    @Schema(description = "sdshh")
    private String sdshh;
    @Schema(description = "sdsbl")
    private BigDecimal sdsbl;
    @Schema(description = "sdsje")
    private BigDecimal sdsje;

    @Schema(description = "swjgzh")
    private String swjgzh;
    @Schema(description = "swjghm")
    private String swjghm;
    @Schema(description = "swjghh")
    private String swjghh;
    @Schema(description = "swjgbl")
    private BigDecimal swjgbl;
    @Schema(description = "swjgje")
    private BigDecimal swjgje;

    @Schema(description = "cbjthbj")
    private String cbjthbj;
    @Schema(description = "cbjthrq")
    private String cbjthrq;
    @Schema(description = "cbjthczy")
    private String cbjthczy;

    @Schema(description = "jym")
    private String jym;
    @Schema(description = "hkpch")
    private String hkpch;
    @Schema(description = "bz")
    private String bz;
    @Schema(description = "bluuid")
    private String bluuid;

    @Schema(description = "sdghzh")
    private String sdghzh;
    @Schema(description = "sdghhm")
    private String sdghhm;
    @Schema(description = "sdghhh")
    private String sdghhh;
    @Schema(description = "sdghbl")
    private BigDecimal sdghbl;
    @Schema(description = "sdghje")
    private BigDecimal sdghje;

    @Schema(description = "createBy")
    private String createBy;
    @Schema(description = "createTime")
    private LocalDateTime createTime;
    @Schema(description = "updateBy")
    private String updateBy;
    @Schema(description = "updateTime")
    private LocalDateTime updateTime;

    /* ---- gh_hj join fields ---- */
    @Schema(description = "小微类型")
    private String xwlx;
    @Schema(description = "小额类型23")
    private String xelx23;
    @Schema(description = "小额类型24")
    private String xelx24;
    @Schema(description = "小额类型25")
    private String xelx25;
}
