package cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejfhbsbyxg;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 小额失败已修改 DO
 */
@TableName("gh_hkxxxejf")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class XejfhbsbyxgDO {

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
    private String thrq;
    private String thyy;
    private String hkxxidgl;
    private String schkpch;
    private String bz;
    private String xgbj;
    private String scbz;
    private String yxbj;
    private String xgr;
    private String xgsj;
}
