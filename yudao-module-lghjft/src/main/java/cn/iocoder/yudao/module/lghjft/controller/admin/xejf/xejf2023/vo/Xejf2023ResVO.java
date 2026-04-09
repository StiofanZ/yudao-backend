package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf2023.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 小额缴费明细 Response VO")
@Data
@ExcelIgnoreUnannotated
public class Xejf2023ResVO {
    private Long ghjfId;
    private String sjdm;
    private String xjdm;
    private BigDecimal bs;
    private BigDecimal hs;
    private String spuuid;
    private String djxh;
    @ExcelProperty("社会信用代码")
    private String shxydm;
    private String nsrsbh;
    @ExcelProperty("纳税人名称")
    private String nsrmc;
    private String nsrjc;
    @ExcelProperty("工会机构代码")
    private String deptId;
    private String ghjgxzjb;
    private String clghbj;
    private String hyghbz;
    private String sdghjgDm;
    private String sdghjgxzjb;
    private String zgswjDm;
    private String zgswskfjDm;
    private String ssglyDm;
    private String ghlbDm;
    private String xtlbDm;
    private String xwlx;
    private String[] xelx23;
    private String[] xelx24;
    private String[] xelx25;
    private String zzjglxDm;
    private String hyDm;
    private String djzclxDm;
    private String dwlsgxDm;
    private String sblbDm;
    private LocalDateTime nssbrq;
    private String pzxh;
    private Long pzmxxh;
    private LocalDateTime skssqq;
    private LocalDateTime skssqz;
    private String zsswjgDm;
    private String skssswjgDm;
    private String skgkDm;
    private String dzsphm;
    private String pzzlDm;
    private String pzzgDm;
    private String pzhm;
    private String kpyDm;
    private String kpyXm;
    @ExcelProperty("征收品目代码")
    private String zspmDm;
    private String zszmDm;
    private String yskmDm;
    private BigDecimal gzze;
    private BigDecimal sl;
    private BigDecimal ynse;
    private BigDecimal jmse;
    private BigDecimal yjse;
    @ExcelProperty("缴费金额")
    private BigDecimal ybtse;
    private BigDecimal sjje;
    private String jkfsDm;
    private String jfhb;
    private String jfzh;
    private String jfhm;
    private String jfhh;
    private String jfyh;
    private LocalDateTime rkrq;
    private String[] jsbj;
    private LocalDateTime jsrq;
    private String jsczy;
    private String jcghzh;
    private String jcghhm;
    private String jcghhh;
    private BigDecimal jcghbl;
    @ExcelProperty("基层工会金额")
    private BigDecimal jcghje;
    private String cbjzh;
    private String cbjhm;
    private String cbjhh;
    private BigDecimal cbjbl;
    private BigDecimal cbjje;
    private String hyghzh;
    private String hyghhm;
    private String hyghhh;
    private BigDecimal hyghbl;
    private BigDecimal hyghje;
    private String xjghzh;
    private String xjghhm;
    private String xjghhh;
    private BigDecimal xjghbl;
    private BigDecimal xjghje;
    private String sjghzh;
    private String sjghhm;
    private String sjghhh;
    private BigDecimal sjghbl;
    private BigDecimal sjghje;
    private String szghzh;
    private String szghhm;
    private String szghhh;
    private BigDecimal szghbl;
    private BigDecimal szghje;
    private String qgghzh;
    private String qgghhm;
    private String qgghhh;
    private BigDecimal qgghbl;
    private BigDecimal qgghje;
    private String jcjzh;
    private String jcjhm;
    private String jcjhh;
    private BigDecimal jcjbl;
    private BigDecimal jcjje;
    private String jcwsh;
    private String sdszh;
    private String sdshm;
    private String sdshh;
    private BigDecimal sdsbl;
    private BigDecimal sdsje;
    private String swjgzh;
    private String swjghm;
    private String swjghh;
    private BigDecimal swjgbl;
    private BigDecimal swjgje;
    private String cbjthbj;
    private LocalDateTime cbjthrq;
    private String cbjthczy;
    private String jym;
    private String hkpch;
    private String jfqj;
    private BigDecimal yfbje;
    private String sdghzh;
    private String sdghhm;
    private String sdghhh;
    private BigDecimal sdghbl;
    private BigDecimal sdghje;
}
