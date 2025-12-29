package cn.iocoder.yudao.module.lghjft.dal.dataobject.wfsqtfsq;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

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
@Accessors(chain = true)
public class WfSqTfsqmxDO extends BaseDO {

    /**
     * 退费申请明细ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 退费申请明细序号
     */
    private Long tfsqId;
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
     * 社会信用代码
     */
    private String shxydm;
    /**
     * 纳税人名称
     */
    private String nsrmc;
    /**
     * 税款所属期起
     */
    private LocalDate skssqq;
    /**
     * 税款所属期止
     */
    private LocalDate skssqz;

}
