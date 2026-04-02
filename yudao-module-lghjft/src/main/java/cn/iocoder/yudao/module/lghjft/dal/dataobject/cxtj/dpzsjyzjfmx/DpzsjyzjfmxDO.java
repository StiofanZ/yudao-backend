package cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.dpzsjyzjfmx;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Dpzsjyzjfmx DO
 */
@TableName("dpzsjyzjfmx")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class DpzsjyzjfmxDO {

    @TableId(type = IdType.INPUT)
    private String spuuid;
    private String zsswjgDm;
    private String swjg;
    private String ghjg;
    private String djxh;
    private String jfdw;
    private LocalDateTime skssqq;
    private LocalDateTime skssqz;
    private String zspm;
    private BigDecimal jfje;
    private LocalDateTime jfrq;
}
