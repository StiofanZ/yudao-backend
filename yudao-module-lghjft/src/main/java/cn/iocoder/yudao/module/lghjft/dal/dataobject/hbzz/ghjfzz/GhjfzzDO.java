package cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.ghjfzz;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * 工会经费做账 DO
 */
@TableName("gh_hkxx")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class GhjfzzDO {

    @TableId
    private Long hkxxId;
    private String hkpch;
    private Long xh;
    private String lx;
    private String zh;
    private String hm;
    private String hh;
    private BigDecimal je;
    private String deptId;
    private String dz;
    private String fy;
    private String jym;
    private String thbj;
    private String thrq;
    private String thyy;
    private String schkpch;
    private String bz;
}
