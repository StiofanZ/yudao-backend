package cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.hbsbjlyxg;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * 划拨失败已修改 DO (same table gh_hkxx, filtered by xgbj='1')
 */
@TableName("gh_hkxx")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class HbsbjlyxgDO {

    @TableId(type = IdType.AUTO)
    private Long hkxxId;
    private String hkpch;
    private Long xh;
    private String lx;
    private String zh;
    private String hm;
    private String hh;
    private String xzh;
    private String xhm;
    private String xhh;
    private BigDecimal je;
    private String deptId;
    private String dz;
    private String fy;
    private String jym;
    private String thbj;
    private String thrq;
    private String thyy;
    private String xgbj;
    private String hkxxidgl;
    private String schkpch;
    private String bz;
    private String scbz;
    private String xgr;
    private String xgsj;
}
