package cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.hkxxxejf;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 小额拨付记账凭证 DO
 *
 * 注意：gh_hkxxxejf 表没有 qrrq/yhhdh/zh1/zh2/zh3 列，
 * 这些字段仅用于查询参数传递，标记 exist=false。
 */
@TableName("gh_hkxxxejf")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class GhHkxxxejfDO {

    @TableId
    private Long hkxxId;
    private String hkpch;
    private Long xh;
    private Long jfqj;
    private String lx;
    private String zh;
    @TableField(exist = false)
    private String zh1;
    @TableField(exist = false)
    private String zh2;
    @TableField(exist = false)
    private String zh3;
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
    @TableField(exist = false)
    private LocalDateTime qrrq;
    @TableField(exist = false)
    private String yhhdh;
    private String bz;
    private String xgbj;
    private String scbz;
    private String yxbj;
}
