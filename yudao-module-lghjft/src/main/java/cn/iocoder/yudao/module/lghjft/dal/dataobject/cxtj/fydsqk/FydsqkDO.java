package cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.fydsqk;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * Fydsqk DO — 分月代收情况（按月汇总视图表）
 *
 * <p>V1 的查询使用 GROUP BY ... WITH ROLLUP 聚合，
 * 但物理表 fydsqk 本身也可直接分页查询。
 */
@TableName("fydsqk")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class FydsqkDO {

    @TableId(type = IdType.INPUT)
    private String deptId;
    private String rkrq;
    private String dsyf;
    private BigDecimal rkjf;
    private BigDecimal ghjf;
    private BigDecimal cbj;
    private BigDecimal znj;
}
