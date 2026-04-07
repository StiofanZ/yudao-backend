package cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.jrbxzqdw;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 金融保险证券单位 DO — 映射 v1 表 jrbxzqdw
 *
 * <p>注意：不继承 BaseDO，不使用 @TableLogic。
 * 审计字段使用 v1 命名：createBy/updateBy。
 * 关联字段（deptId, xtlbDm, ghlbDm, gzze, bs, je, sl, bs20xx, je20xx）
 * 来自 gh_hj / jrbxzqdwjfqk1 / jrbxzqdwjfqk 三表 JOIN，
 * 通过 XML Mapper 的 resultMap 映射，非 jrbxzqdw 本表字段。
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
    /** 来自 gh_hj.dept_id（JOIN 字段） */
    private String deptId;
    private String shxydm;
    private String nsrsbh;
    private String nsrmc;
    private String djxh;
    private String zgjrghzgdwbz;
    /** 来自 gh_hj.ghlb_dm（JOIN 字段） */
    private String ghlbDm;
    /** 来自 gh_hj.xtlb_dm（JOIN 字段） */
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

    // v1 审计字段
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;

    // 来自 jrbxzqdwjfqk1 (b 表) — 2025 数据
    private BigDecimal gzze;
    private Long bs;
    private BigDecimal je;
    private String sl;

    // 来自 jrbxzqdwjfqk (k 表) — 2020-2024 数据
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
