package cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.dmwh.fpbl;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * CsFpbl DO
 */
@TableName("cs_fpbl")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class CsFpblDO {

    @TableId(type = IdType.AUTO)
    private Long blId;
    private String bluuid;
    private String lx;
    private String ms;
    private LocalDateTime yxqq;
    private LocalDateTime yxqz;
    private String xybz;
    private BigDecimal jcghbl;
    private BigDecimal hyghbl;
    private BigDecimal sdghbl;
    private BigDecimal xjghbl;
    private BigDecimal sjghbl;
    private BigDecimal szghbl;
    private BigDecimal qgzghbl;
    private BigDecimal sjcjbl;
    private BigDecimal sdsjbl;
    private BigDecimal swjgbl;
    private String tj;
    private Long yxj;
    private String mrbz;
    private String jym;
}
