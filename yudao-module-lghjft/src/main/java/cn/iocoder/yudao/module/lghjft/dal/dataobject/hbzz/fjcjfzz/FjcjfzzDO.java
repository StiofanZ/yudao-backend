package cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.fjcjfzz;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 返基层账 DO
 */
@TableName("gh_hkxx")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class FjcjfzzDO {

    @TableId
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
    private LocalDateTime thrq;
    private String thyy;
    private String schkpch;
    private String bz;
    private String scbz;
}
