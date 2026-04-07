package cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.fyfcqk;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * Fyfcqk DO -- 分月分成情况（聚合查询结果）
 *
 * <p>V1 查询使用 GROUP BY ... WITH ROLLUP 聚合，结果只含 dsyf + 9 个 SUM 列。
 * 不继承 BaseDO，不使用 @TableLogic。
 */
@TableName("fyfcqk")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FyfcqkDO {

    /** 代收月份 */
    private String dsyf;
    /** 入库经费 */
    private BigDecimal rkjf;
    /** 退库经费 */
    private BigDecimal tkjf;
    /** 应分成经费 */
    private BigDecimal ygfcjf;
    /** 已分成经费 */
    private BigDecimal yfcjf;
    /** 未分成经费 */
    private BigDecimal wfcjf;
    /** 省总分成 */
    private BigDecimal szfc;
    /** 滞纳金 */
    private BigDecimal znj;
    /** 全总分成 */
    private BigDecimal qzfc;
    /** 合计 */
    private BigDecimal hj;
}
