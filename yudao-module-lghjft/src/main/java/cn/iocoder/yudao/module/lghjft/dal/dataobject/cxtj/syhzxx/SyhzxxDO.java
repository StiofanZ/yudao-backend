package cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.syhzxx;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * Syhzxx DO
 */
@TableName("syhzxx")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class SyhzxxDO {

    @TableId(type = IdType.INPUT)
    private String deptId;
    private Long bzhs;
    private BigDecimal bzje;
    private Long byhs;
    private BigDecimal byje;
    private Long bnhs;
    private BigDecimal bnje;
    private Long rkwwhhs;
    private BigDecimal rkwwhje;
    private Long jczhkhs;
    private BigDecimal jczhkje;
    private Long bfsbbs;
    private BigDecimal bfsbje;
}
