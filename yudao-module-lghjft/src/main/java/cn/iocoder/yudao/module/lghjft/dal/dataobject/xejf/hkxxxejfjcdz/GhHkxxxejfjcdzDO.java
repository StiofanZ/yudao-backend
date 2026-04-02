package cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.hkxxxejfjcdz;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 小额缴费基层到账 DO
 */
@TableName("gh_hkxxxejf")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class GhHkxxxejfjcdzDO {

    @TableId
    private Long hkxxId;
    private String hkpch;
    private Long xh;
    private Long jfqj;
    private String lx;
    private String zh;
    private String hm;
    private String hh;
    private String xzh;
    private String xhm;
    private String xhh;
    private BigDecimal wqyfje;
    private BigDecimal je;
    private String deptId;
    private String dz;
    private String fy;
    private String jym;
    private String thbj;
    private LocalDateTime thrq;
    private String thyy;
    private String hkxxidgl;
    private String schkpch;
    private String bz;
    private String xgbj;
    private String scbz;
    private String yxbj;
    private String xgr;
    private LocalDateTime xgsj;
}
