package cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.szjffydsqk;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * Szjffydsqk DO
 */
@TableName("szjffydsqk")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class SzjffydsqkDO {

    @TableId(type = IdType.INPUT)
    private String dwdm;
    private String dwmc;
    private String dsyf;
    private BigDecimal rkjf;
    private BigDecimal ghjf;
    private BigDecimal cbj;
    private BigDecimal znj;
}
