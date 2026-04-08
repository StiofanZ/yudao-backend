package cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.jczhkxwh;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 基层账户空需维护 DO
 */
@TableName("gh_hj")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class JczhkxwhDO {

    private String deptId;
    private String hyghbz;
    @TableId(type = IdType.INPUT)
    private String djxh;
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
    /**
     * dclghbj - maps to column HJFL8_DM
     */
    @TableField("HJFL8_DM")
    private String dclghbj;
    /**
     * xwyqbj - maps to column HJFL9_DM
     */
    @TableField("HJFL9_DM")
    private String xwyqbj;
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
