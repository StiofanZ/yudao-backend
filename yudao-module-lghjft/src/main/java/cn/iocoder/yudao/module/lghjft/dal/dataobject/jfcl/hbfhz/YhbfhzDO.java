package cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.hbfhz;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 银行拨付汇总 DO
 *
 * @author 李文军
 */
@TableName("gh_hkxx_yhbfhz")
@KeySequence("gh_hkxx_yhbfhz_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YhbfhzDO extends BaseDO {

    /**
     * 拨付汇总ID
     */
    @TableId
    private Integer bfhzid;
    /**
     * 拨付汇总批次号
     */
    private String bfhzpch;
    /**
     * 业务参考号起
     */
    private Long bfidq;
    /**
     * 业务参考号止
     */
    private Long bfidz;
    /**
     * 拨付包名称
     */
    private String bfbmc;
    /**
     * 拨付笔数
     */
    private Long bs;
    /**
     * 拨付合计金额
     */
    private BigDecimal hzje;
    /**
     * 退回笔数
     */
    private String thbs;
    /**
     * 退回金额
     */
    private BigDecimal thje;
    /**
     * 失败笔数
     */
    private String sbbs;
    /**
     * 失败金额
     */
    private BigDecimal sbje;
    /**
     * 成功笔数
     */
    private String cgbs;
    /**
     * 成功金额
     */
    private BigDecimal cgje;
    /**
     * 唯一识别号
     */
    private String uuid;
    /**
     * 拨付状态
     */
    private String bfzt;
    /**
     * 处理结果
     */
    private String bfjg;
    /**
     * 退回日期
     */
    private String thrq;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 修改人
     */
    private String updateBy;
    /**
     * 全部业务参考号
     */
    private String bfidStr;
    // 新增：时间查询条件（非数据库字段）
    @TableField(exist = false)
    private String beginCreateTime;

    @TableField(exist = false)
    private String endCreateTime;

}