package cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.jfmx;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 经费明细(查询统计) DO — 映射 v1 表 gh_jf
 * <p>
 * 注意：gh_jf 是 v1 表，禁止继承 BaseDO / TenantBaseDO，禁止 @TableLogic
 */
@TableName("gh_jf")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CxtjJfmxDO {

    @TableId
    private Long ghjfId;

    private String spuuid;
    private String djxh;
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
    private String zzjglxDm;
    private String hyDm;
    private String djzclxDm;
    private String dwlsgxDm;
    private String sblbDm;
    private String nssbrq;
    private String pzxh;
    private Long pzmxxh;
    private String skssqq;
    private String skssqz;
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
    private String rkrq;
    private String jsbj;
    private String jsrq;
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
    private String cbjthbj;
    private String cbjthrq;
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
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
}
