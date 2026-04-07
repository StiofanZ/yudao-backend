package cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.fyfcqk;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * Fyfcqk DO — 分月分成情况（按月汇总视图表）
 *
 * <p>V1 的查询使用 GROUP BY ... WITH ROLLUP 聚合。
 */
@TableName("fyfcqk")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class FyfcqkDO {

    @TableId(type = IdType.INPUT)
    private String deptId;
    private String rkrq;
    private String dsyf;
    private BigDecimal rkjf;
    private BigDecimal tkjf;
    private BigDecimal ygfcjf;
    private BigDecimal yfcjf;
    private BigDecimal wfcjf;
    private BigDecimal szfc;
    private BigDecimal znj;
    private BigDecimal qzfc;
    private BigDecimal hj;
}
