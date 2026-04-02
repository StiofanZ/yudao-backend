package cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.syhzxxfy;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * Syhzxxfy DO
 */
@TableName("syhzxxfy")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class SyhzxxfyDO {

    @TableId(type = IdType.INPUT)
    private String nd;
    private String deptId;
    private BigDecimal month1;
    private BigDecimal month2;
    private BigDecimal month3;
    private BigDecimal month4;
    private BigDecimal month5;
    private BigDecimal month6;
    private BigDecimal month7;
    private BigDecimal month8;
    private BigDecimal month9;
    private BigDecimal month10;
    private BigDecimal month11;
    private BigDecimal month12;
    private BigDecimal total;
    private BigDecimal totalsjszje;
    private BigDecimal totalbjfcje;
}
