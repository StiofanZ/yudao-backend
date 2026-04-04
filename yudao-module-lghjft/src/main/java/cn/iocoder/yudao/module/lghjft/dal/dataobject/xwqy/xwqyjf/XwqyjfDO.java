package cn.iocoder.yudao.module.lghjft.dal.dataobject.xwqy.xwqyjf;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Xwqyjf DO
 */
@TableName("gh_jf")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class XwqyjfDO {

    @TableId(type = IdType.AUTO)
    private Long ghjfId;
    private String spuuid;
    private String djxh;
    private String shxydm;
    private String nsrmc;
    /**
     * JOIN 字段：来自 gh_hj.dept_id，小微上报单位代码
     */
    @TableField(exist = false)
    private String xwsbdwdm;
    /**
     * JOIN 字段：来自 gh_hj.HJFL7_DM，小微上报日期
     */
    @TableField(exist = false)
    private String xwsbrq;
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
    private java.util.Date cbjthrq;
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
    private BigDecimal yfje;
    private BigDecimal gkzfje;
    private BigDecimal gjthje;
    private String createBy;
    private java.util.Date createTime;
    private String updateBy;
    private java.util.Date updateTime;
}
