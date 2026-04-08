package cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.cbjzz;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 筹备金做账 DO — 映射 v1 表 gh_hkxx
 * 不继承 BaseDO，不使用 @TableLogic
 */
@TableName("gh_hkxx")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class CbjzzDO {

    @TableId
    private Long hkxxId;
    private String hkpch;
    private Long xh;
    private String lx;
    private String zh;
    private String hm;
    private String hh;
    private BigDecimal je;
    /** v1: dept_id */
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
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
}
