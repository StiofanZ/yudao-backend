package cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.cbjqltz;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Cbjqltz DO
 */
@TableName("cbjqltz_qr")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class CbjqltzDO {

    private String deptId;
    private String zgswjDm;
    private String zgswskfjDm;
    private String jdxzDm;
    private String shxydm;
    private String nsrmc;
    @TableId(type = IdType.INPUT)
    private String djxh;
    private String nsrztDm;
    private String nsrztmc;
    private LocalDateTime sjtbSj;
    private String jsbj;
    private String cbjthbj;
    private String fbbj;
    private Long jfbs;
    private BigDecimal jfje;
    private BigDecimal cbjje;
    private Long fbbs;
    private BigDecimal sfbje;
    private LocalDateTime rkrq;
    private Long yf;
    private LocalDateTime jfrkrq;
    private Long jfyf;
    private String subNsrztDm;
    private String subJfztDm;
    private String subGzqrztDm;
    private String subUpdateBy;
    private LocalDateTime subUpdateTime;
}
