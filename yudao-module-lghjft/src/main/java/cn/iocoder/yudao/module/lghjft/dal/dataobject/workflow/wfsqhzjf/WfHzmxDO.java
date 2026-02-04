package cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wfsqhzjf;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 工会经费汇总缴纳申请明细表（分支机构） DO
 *
 * @author 李文军
 */
@TableName("lghjft_wf_hzmx")
@KeySequence("lghjft_wf_hzmx_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WfHzmxDO extends BaseDO {

    /**
     * 主键ID，自增
     */
    @TableId
    private Long id;
    /**
     * 关联汇总表主键ID（外键）
     */
    private Long hzId;
    /**
     * 分支机构社会信用代码
     */
    private String fjgxyxdm;
    /**
     * 分支机构单位全称
     */
    private String fjgdwqc;
    /**
     * 分支机构主管税务部门
     */
    private String fjgzgsbm;
    /**
     * 分支机构职工人数
     */
    private Integer fjggzs;
    /**
     * 分支机构月工资总额
     */
    private BigDecimal fjggzze;
//    /**
//     * 创建时间
//     */
//    private LocalDateTime createTime;
//    /**
//     * 更新时间
//     */
//    private LocalDateTime updateTime;

    @TableField(exist = false)
    private String creator;

    @TableField(exist = false)
    private String updater;

    @TableField(exist = false)
    private Boolean deleted;

}