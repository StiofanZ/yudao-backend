package cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.yhbfhz;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 银行拨付汇总 DO
 *
 * @author 李文军
 */
@TableName("gh_hkxx_yhbfhz")
@KeySequence("gh_hkxx_yhbfhz_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YhbfhzDO {

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
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 修改人
     */
    private String updateBy;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
    /**
     * 全部业务参考号
     */
    private String bfidStr;
}