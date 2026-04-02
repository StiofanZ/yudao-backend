package cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.jrbxzqdw;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * Jrbxzqdw DO
 */
@TableName("jrbxzqdw")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class JrbxzqdwDO {

    @TableId(type = IdType.INPUT)
    private String id;
    private String dwdm;
    private String deptId;
    private String shxydm;
    private String nsrsbh;
    private String nsrmc;
    private String djxh;
    private String zgjrghzgdwbz;
    private String ghlbDm;
    private String xtlbDm;
    private String nsrztDm;
    private String djzclxDm;
    private String djzclxmc;
    private String scjydz;
    private String scjydzxzqhszDm;
    private String xzqhmc;
    private String jdxzDm;
    private String hyDm;
    private String hymc;
    private String djjgDm;
    private String djjgmc;
    private String zgswjDm;
    private String zgswjmc;
    private String zgswskfjDm;
    private String zgksfjmc;
    private String cwfzrxm;
    private String cwfzrgddh;
    private String cwfzryddh;
    private BigDecimal cyrs;
    private String bz;
    private String hsjg;
    private BigDecimal gzze;
    private Long bs;
    private BigDecimal je;
    private String sl;
    private Long bs2024;
    private BigDecimal je2024;
    private Long bs2023;
    private BigDecimal je2023;
    private Long bs2022;
    private BigDecimal je2022;
    private Long bs2021;
    private BigDecimal je2021;
    private Long bs2020;
    private BigDecimal je2020;
}
