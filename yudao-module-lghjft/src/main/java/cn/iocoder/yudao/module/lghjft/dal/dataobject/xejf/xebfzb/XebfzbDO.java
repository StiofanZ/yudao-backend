package cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xebfzb;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * 小额拨付占比 DO
 */
@TableName("xebfzb")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class XebfzbDO {

    @TableId
    private String xend;
    private String sjdm;
    private String deptId;
    private Long jfbs;
    private Long jfhs;
    private BigDecimal jfje;
    private BigDecimal sjje;
    private BigDecimal xjjfje;
    private BigDecimal sjjfje;
    private BigDecimal szqzjfje;
    private Long xebs;
    private Long xehs;
    private BigDecimal xjxeje;
    private BigDecimal sjxeje;
    private BigDecimal szqzxeje;
    private BigDecimal xeje;
    private BigDecimal jcghje;
}
