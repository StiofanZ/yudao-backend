package cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.dhjftz;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 单户经费台账 DO — 映射 v1 表 gh_hj（企业台账主数据）
 *
 * 注意：gh_hj 是 v1 表，禁止继承 BaseDO / TenantBaseDO，禁止 @TableLogic
 */
@TableName("gh_hj")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class DhjftzDO {

    @TableId
    private String djxh;
    private String deptId;
    private String hyghbz;
    private String shxydm;
    private String nsrmc;
    private String nsrjc;
    private String zgswjDm;
    private String zgswjmc;
    private String zgswskfjDm;
    private String zgswskfjmc;
    private String ssglyDm;
    private String ssglyxm;
    private String zzjglxDm;
    private String zzjglxmc;
    private String hyDm;
    private String hymc;
    private String djzclxDm;
    private String djzclxmc;
    private String dwlsgxDm;
    private String dwlsgxmc;
    private BigDecimal zgrs;
    private String nsrztDm;
    private String nsrztmc;
    private LocalDateTime fzcrq;
    private LocalDateTime zxrq;
    private String zcdz;
    private String yzbm;
    private String lxr;
    private String lxdh;
    private String ghlbDm;
    private String xtlbDm;
    private String hjfl1Dm;
    private String hjfl2Dm;
    private String hjfl3Dm;
    private String hjfl4Dm;
    private String hjfl5Dm;
    private String hjfl6Dm;
    private String hjfl7Dm;
    private String hjfl8Dm;
    private String hjfl9Dm;
    private String sdghjgDm;
    private String clghbj;
    private LocalDateTime clghrq;
    private String jcghdm;
    private String jcghmc;
    private String jcghzh;
    private String jcghhm;
    private String jcghhh;
    private String jcghyh;
    private String bz;
    private String jym;
    private String nsrsbh;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
}
