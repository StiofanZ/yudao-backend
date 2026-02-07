package cn.iocoder.yudao.module.lghjft.dal.dataobject.nsrxx;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 纳税人扩展信息 DO
 *
 * @author 芋道源码
 */
@TableName("dj_nsrxx_kz")
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NsrxxKzDO {

    /**
     * 登记序号
     */
    @TableId
    private String djxh;

    /**
     * 法定代表人移动电话
     */
    @TableField("FDDBRYDDH")
    private String fddbryddh;

    /**
     * 财务负责人移动电话
     */
    @TableField("CWFZRYDDH")
    private String cwfzryddh;

    /**
     * 财务负责人姓名
     */
    @TableField("CWFZRXM")
    private String cwfzrxm;

    @TableField("HJSZD")
    private String hjszd;

    @TableField("JYFW")
    private String jyfw;

    @TableField("ZCDLXDH")
    private String zcdlxdh;

    @TableField("ZCDYZBM")
    private String zcdyzbm;

    @TableField("SCJYDLXDH")
    private String scjydlxdh;

    @TableField("SCJYDYZBM")
    private String scjydyzbm;

    @TableField("HSFS_DM")
    private String hsfsDm;

    @TableField("CYRS")
    private BigDecimal cyrs;

    @TableField("WJCYRS")
    private BigDecimal wjcyrs;

    @TableField("HHRS")
    private BigDecimal hhrs;

    @TableField("GGRS")
    private BigDecimal ggrs;

    @TableField("GDGRS")
    private BigDecimal gdgrs;

    @TableField("ZZJGLX_DM")
    private String zzjglxDm;

    @TableField("KJZDZZ_DM")
    private String kjzdzzDm;

    @TableField("WZ")
    private String wz;

    @TableField("SWDLRLXDH")
    private String swdlrlxdh;

    @TableField("SWDLRDZXX")
    private String swdlrdzxx;

    @TableField("ZCZB")
    private BigDecimal zczb;

    @TableField("TZZE")
    private BigDecimal tzze;

    @TableField("ZRRTZBL")
    private BigDecimal zrrtzbl;

    @TableField("WZTZBL")
    private BigDecimal wztzbl;

    @TableField("GYTZBL")
    private BigDecimal gytzbl;

    @TableField("GYKGLX_DM")
    private String gykglxDm;

    @TableField("ZFJGLX_DM")
    private String zfjglxDm;

    @TableField("BZFS_DM")
    private String bzfsDm;

    @TableField("FDDBRGDDH")
    private String fddbrgddh;

    @TableField("FDDBRDZXX")
    private String fddbrdzxx;

    @TableField("CWFZRSFZJZL_DM")
    private String cwfzrsfzjzlDm;

    @TableField("CWFZRSFZJHM")
    private String cwfzrsfzjhm;

    @TableField("CWFZRGDDH")
    private String cwfzrgddh;

    @TableField("CWFZRDZXX")
    private String cwfzrdzxx;

    @TableField("BSRXM")
    private String bsrxm;

    @TableField("BSRSFZJZL_DM")
    private String bsrsfzjzlDm;

    @TableField("BSRSFZJHM")
    private String bsrsfzjhm;

    @TableField("BSRGDDH")
    private String bsrgddh;

    @TableField("BSRYDDH")
    private String bsryddh;

    @TableField("BSRDZXX")
    private String bsrdzxx;

    @TableField("LSSWDJYXQQ")
    private Date lsswdjyxqq;

    @TableField("LSSWDJYXQZ")
    private Date lsswdjyxqz;

    @TableField("SWDLRNSRSBH")
    private String swdlrnsrsbh;

    @TableField("SWDLRMC")
    private String swdlrmc;

    @TableField("WHSYJSFJFXXDJBZ")
    private String whsyjsfjfxxdjbz;

    @TableField("ZZSJYLB")
    private String zzsjylb;

    @TableField("YHSJNFS_DM")
    private String yhsjnfsDm;

    @TableField("ZSXMCXBZ_DM")
    private String zsxmcxbzDm;

    @TableField("ZZSQYLX_DM")
    private String zzsqylxDm;

    @TableField("GJHDQSZ_DM")
    private String gjhdqszDm;

    @TableField("YGZNSRLX_DM")
    private String ygznsrlxDm;

    @TableField("LRR_DM")
    private String lrrDm;

    @TableField("LRRQ")
    private Date lrrq;

    @TableField("XGR_DM")
    private String xgrDm;

    @TableField("XGRQ")
    private Date xgrq;

    @TableField("SJGSDQ")
    private String sjgsdq;

    @TableField("SJTB_SJ")
    private Date sjtbSj;

    @TableField("QYGLCJBH")
    private String qyglcjbh;

    @TableField("QYSSJT_DM")
    private String qyssjtDm;

    @TableField("NSRYWMC")
    private String nsrywmc;

    @TableField("YWZCDZ")
    private String ywzcdz;

    @TableField("FDDBRZS")
    private String fddbrzs;

    @TableField("FDDBRZSYZBM")
    private String fddbrzsyzbm;

    @TableField("ZCXS")
    private String zcxs;

    @TableField("SZGJDQNSRSBH")
    private String szgjdqnsrsbh;

    @TableField("SJBLBZ")
    private Short sjblbz;

}
