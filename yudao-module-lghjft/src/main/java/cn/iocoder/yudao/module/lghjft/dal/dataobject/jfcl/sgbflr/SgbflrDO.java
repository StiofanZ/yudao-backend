package cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.sgbflr;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 手工拨付录入 DO - maps v1 table gh_hkxx
 * NOT extending BaseDO, no @TableLogic per v1 mapping rules
 */
@TableName("gh_hkxx")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class SgbflrDO {

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
    private LocalDateTime thrq;
    private String thyy;
    private String xgbj;
    private String yxbj;
    private String schkpch;
    private String bz;
    private String scbz;
    /** v1 audit fields */
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
}
