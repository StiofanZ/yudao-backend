package cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.top;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * Top DO
 */
@TableName("top")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class TopDO {

    @TableId(type = IdType.INPUT)
    private String djxh;
    private String shxydm;
    private String nsrmc;
    private String deptId;
    private String dwmc;
    private Long bsdn;
    private Long bswn;
    private Long bscy;
    private BigDecimal jfjedn;
    private BigDecimal jfjewn;
    private BigDecimal jfjecy;
    private String jfbl;
    private BigDecimal sjjedn;
    private BigDecimal sjjewn;
    private BigDecimal sjjecy;
    private String sjbl;
}
