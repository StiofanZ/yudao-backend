package cn.iocoder.yudao.module.lghjft.controller.admin.jf.jfmx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Schema(description = "管理后台 - 经费明细查询 Request VO")
@Data
public class JfmxQuery {

    @Schema(description = "页码，从 1 开始")
    @Min(value = 1, message = "页码最小值为 1")
    private Integer pageNo = 1;

    @Schema(description = "每页条数")
    @Min(value = 1, message = "每页条数最小值为 1")
    @Max(value = 100, message = "每页条数最大值为 100")
    private Integer pageSize = 10;

    @Schema(description = "分页偏移量")
    private Integer offset;

    @Schema(description = "分页限制")
    private Integer limit;

    private String spuuid;
    private String djxh;
    private String ghjgmc;
    private String shxydm;
    private String nsrmc;
    private String nsrjc;
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
    private String xelx23;
    private String xelx24;
    private String xelx25;
    private String zzjglxDm;
    private String hyDm;
    private String djzclxDm;
    private String dwlsgxDm;
    private String sblbDm;
    private Date nssbrq;
    private String pzxh;
    private Long pzmxxh;
    private Date skssqq;
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
    private String zspmDm;
    private String zszmDm;
    private String yskmDm;
    private BigDecimal gzze;
    private BigDecimal sl;
    private BigDecimal ynse;
    private BigDecimal jmse;
    private BigDecimal yjse;
    private BigDecimal ybtse;
    private String jkfsDm;
    private String jfhb;
    private String jfzh;
    private String jfhm;
    private String jfhh;
    private String jfyh;
    private Date rkrq;
    private String jsbj;
    private Date jsrq;
    private String jsczy;
    private String jcghzh;
    private String jcghhm;
    private String jcghhh;
    private BigDecimal jcghbl;
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
    private Date cbjthrq;
    private String cbjthczy;
    private String jym;
    private String hkpch;
    private String bz;
    private String bluuid;
    private String sdghzh;
    private String sdghhm;
    private String sdghhh;
    private BigDecimal sdghbl;
    private BigDecimal sdghje;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;

}