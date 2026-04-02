package cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.ghjfjfdw;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * Ghjfjfdw DO
 */
@TableName("ghjfjfdw")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class GhjfjfdwDO {

    @TableId(type = IdType.INPUT)
    private String djxh;
    private String shxydm;
    private String nsrmc;
    private String deptId;
    private String zsswjgDm;
    private String djxhbn;
    private Long bsbn;
    private Long ysbn;
    private BigDecimal jfjebn;
    private String djxhsn;
    private Long bssn;
    private Long yssn;
    private BigDecimal jfjesn;
    private String djxhqn;
    private Long bsqn;
    private Long ysqn;
    private BigDecimal jfjeqn;
}
