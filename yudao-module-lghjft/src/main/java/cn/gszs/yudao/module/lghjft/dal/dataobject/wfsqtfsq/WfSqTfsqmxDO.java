package cn.gszs.yudao.module.lghjft.dal.dataobject.wfsqtfsq;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * 申请-退费申请明细 DO
 *
 * @author 李文军
 */
@TableName("gh_wf_sq_tfsqmx")
@KeySequence("gh_wf_sq_tfsqmx_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WfSqTfsqmxDO extends BaseDO {

    /**
     * 退费申请明细ID
     */
    @TableId
    private Long id;
    /**
     * 退费申请明细序号
     */
    @TableId
    private Long xh;
    /**
     * 税票UUID
     */
    private String spuuid;
    /**
     * 入库金额
     */
    private BigDecimal rkje;
    /**
     * 退费审批金额
     */
    private BigDecimal tfsqJe;
    /**
     * 有效标志
     */
    private Integer xybz;

}