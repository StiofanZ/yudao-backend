package cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.Yhbfjgcx;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 银行拨付结果查询 DO
 *
 * @author 李文军
 */
@TableName("yhbfjg_cx")
@KeySequence("yhbfjg_cx_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class yhbfjgcxDO extends BaseDO {

    /**
     * 拨付批次号
     */
    @TableId(type = IdType.INPUT)
    private String bfpch;
    /**
     * 拨付日期
     */
    private String bfrq;
    /**
     * 拨付汇总ID
     */
    private String bfhzid;
    /**
     * 拨付包名称
     */
    private String bfbmc;
    /**
     * 拨付汇总批次号
     */
    private String bfhzpch;
    /**
     * 拨付状态
     */
    private String bfzt;
    /**
     * 拨付总笔数
     */
    private Integer bfzbs;
    /**
     * 拨付总金额
     */
    private BigDecimal bfzje;
    /**
     * 成功结果
     */
    private String cgjg;
    /**
     * 成功笔数
     */
    private Integer cgbs;
    /**
     * 成功金额
     */
    private BigDecimal cgje;
    /**
     * 失败结果
     */
    private String sbjg;
    /**
     * 失败笔数
     */
    private Integer sbbs;
    /**
     * 失败金额
     */
    private BigDecimal sbje;
    /**
     * 退票结果
     */
    private String tpjg;
    /**
     * 退票笔数
     */
    private Integer tpbs;
    /**
     * 退票金额
     */
    private BigDecimal tpje;
    /**
     * 否决结果
     */
    private String fjjg;
    /**
     * 否决笔数
     */
    private Integer fjbs;
    /**
     * 否决金额
     */
    private BigDecimal fjje;
    /**
     * 过期结果
     */
    private String gqjg;
    /**
     * 过期笔数
     */
    private Integer gqbs;
    /**
     * 过期金额
     */
    private BigDecimal gqje;
    /**
     * 撤销结果
     */
    private String cxjg;
    /**
     * 撤销笔数
     */
    private Integer cxbs;
    /**
     * 撤销金额
     */
    private BigDecimal cxje;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 备注
     */
    private String remark;
    @TableField(exist = false)  // 告诉MyBatis这个字段不在表中
    private LocalDateTime createTime;

    @TableField(exist = false)
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private String creator;

    @TableField(exist = false)
    private String updater;

    @TableField(exist = false)
    private Boolean deleted;

}