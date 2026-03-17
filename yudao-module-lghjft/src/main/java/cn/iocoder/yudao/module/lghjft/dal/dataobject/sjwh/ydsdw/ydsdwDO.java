package cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.ydsdw;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 应代收单位 DO
 *
 * @author 李文军
 */
@TableName("jhdwyds")
@KeySequence("jhdwyds_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ydsdwDO extends BaseDO {

    /**
     * 建会单位代收ID
     */
    @TableId
    private Integer jhdwId;
    /**
     * 工会机构
     */
    private String deptId;
    /**
     * 登记序号
     */
    private String djxh;
    /**
     * 社会信用代码
     */
    private String shxydm;
    /**
     * 纳税人名称
     */
    private String nsrmc;
    /**
     * 主管税务机关代码
     */
    private String zgswjDm;
    /**
     * 科所分局代码
     */
    private String zgswskfjDm;
    /**
     * 科所分局名称
     */
    private String zgswskfjmc;
    /**
     * 单位地址
     */
    private String dz;
    /**
     * 单位联系人
     */
    private String dwcwlxr;
    /**
     * 单位联系电话
     */
    private String dwcwlxdh;
    /**
     * 纳税人状态代码
     */
    private String nsrztDm;
    /**
     * 税务登记职工人数
     */
    private Integer zgrs;
    /**
     * 工会登记职工人数
     */
    private Integer ghzgrs;
    /**
     * 工会法人统一社会信用代码
     */
    private String ghshxydm;
    /**
     * 工会组织名称
     */
    private String ghmc;
    /**
     * 工会类型代码
     */
    private String ghlxDm;
    /**
     * 工会联系人
     */
    private String ghjflxr;
    /**
     * 工会联系电话
     */
    private String ghjflxdh;
    /**
     * ghzx
     */
    private String ghzx;
    /**
     * ZXLXDH
     */
    private String zxlxdh;
    /**
     * 成立工会标记（C 筹建工会,Y 建立工会 ,N 待筹建）
     *
     * 枚举
     */
    private String clghbj;
    /**
     * 成立工会日期（筹备金建会日期）
     */
    private LocalDateTime clghrq;
    /**
     * 工会状态代码
     */
    private String ghztDm;
    /**
     * 省级工会名称
     */
    private String sjghmc;
    /**
     * 基层工会账户
     */
    private String jcghzh;
    /**
     * 基层工会户名
     */
    private String jcghhm;
    /**
     * 基层工会行号
     */
    private String jcghhh;
    /**
     * 基层工会银行
     */
    private String jcghyh;
    /**
     * 备注
     */
    private String bz;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 修改人
     */
    private String updateBy;
    /**
     * 街道乡镇代码
     */
    private String jdxzDm;
    /**
     * 税务数据同步时间
     */
    private LocalDateTime sjtbSj;

    @TableField(exist = false)  // 告诉MyBatis这个字段不在表中
    private LocalDateTime createTime;

    @TableField(exist = false)
    private LocalDateTime updateTime;
    @TableField(exist = false)
    private Boolean deleted;

    @TableField(exist = false)
    private String creator;

    @TableField(exist = false)
    private String updater;}