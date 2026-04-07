package cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.fydsqk;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * Fydsqk DO -- 分月代收情况（聚合查询结果）
 *
 * <p>V1 查询使用 GROUP BY ... WITH ROLLUP 聚合，结果只含 dsyf + 4 个 SUM 列。
 * 不继承 BaseDO，不使用 @TableLogic。
 */
@TableName("fydsqk")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FydsqkDO {

    /** 代收月份 */
    private String dsyf;
    /** 入库经费 */
    private BigDecimal rkjf;
    /** 工会经费 */
    private BigDecimal ghjf;
    /** 筹备金 */
    private BigDecimal cbj;
    /** 滞纳金 */
    private BigDecimal znj;
}
