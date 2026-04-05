package cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.cbjhztj;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Cbjhztj DO
 */
@TableName("cbjmx")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class CbjhztjDO {

    @TableId(type = IdType.AUTO)
    private Long ghjfId;
    private String spuuid;
    private String zspmDm;
    private String nd;
    private String deptId;
    private String shxydm;
    private String djxh;
    private String nsrmc;
    private LocalDateTime rkrq;
    private LocalDateTime jsrq;
    private LocalDateTime skssqq;
    private LocalDateTime skssqz;
    private String jsbj;
    private String cbjthbj;
    private BigDecimal qtje;
    private BigDecimal jfje;
    private BigDecimal cbjje;
    private LocalDateTime zjxcrq;
    private String fbbj;
    private LocalDateTime fbrq;
    private BigDecimal sfbje;
}
