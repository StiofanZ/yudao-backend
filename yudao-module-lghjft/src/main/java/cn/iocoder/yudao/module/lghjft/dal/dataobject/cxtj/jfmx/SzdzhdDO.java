package cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.jfmx;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 省总到账核对 DO — 映射 v1 gh_jf 聚合查询结果
 * <p>
 * 注意：gh_jf 是 v1 表，禁止继承 BaseDO / TenantBaseDO，禁止 @TableLogic
 */
@TableName("gh_jf")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SzdzhdDO {

    /** 市级代码 */
    private String sjdm;

    /** 县级代码 */
    private String xjdm;

    /** 笔数 */
    private BigDecimal jfbs;

    /** 工会机构代码 */
    private String deptId;

    /** 划款批次号 */
    private String hkpch;

    /** 应补退税额（缴费金额） */
    private BigDecimal jfje;

    /** 筹备金金额 */
    private BigDecimal cbjje;

    /** 省总金额 */
    private BigDecimal szje;

    /** 全总金额 */
    private BigDecimal qzje;

    /** 省总全总合计 */
    private BigDecimal szqzhj;

    /** 合计金额 */
    private BigDecimal hjje;

    /** 结算日期 */
    private Date jsrq;
}
