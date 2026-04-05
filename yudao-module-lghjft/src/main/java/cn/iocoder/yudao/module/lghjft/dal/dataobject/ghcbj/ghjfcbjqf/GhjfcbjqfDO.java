package cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.ghjfcbjqf;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 筹备金全返 DO - 映射 v1 表 gh_jf_cbjqf
 * <p>
 * 注意：v1 表结构，禁止继承 BaseDO / TenantBaseDO，禁止 @TableLogic
 */
@TableName("gh_jf_cbjqf")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class GhjfcbjqfDO {

    @TableId(type = IdType.AUTO)
    private Long ghjfId;

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
     * 缴费账号
     */
    private String jfzh;

    /**
     * 缴费户名
     */
    private String jfhm;

    /**
     * 工会机构代码
     */
    private String deptId;

    /**
     * 缴费金额 (应补退税额)
     */
    private BigDecimal ybtse;

    /**
     * 筹备金金额
     */
    private BigDecimal cbjje;

    /**
     * 税票UUID
     */
    private String spuuid;

    /**
     * 纳税人简称
     */
    private String nsrjc;

    /**
     * 主管税务局代码
     */
    private String zgswjDm;

    /**
     * 入库日期
     */
    private LocalDateTime rkrq;

    /**
     * 结算标记
     */
    private String jsbj;

    /**
     * 筹备金全返状态
     */
    private String cbjqfzt;

    /**
     * 筹备金全返日期
     */
    private LocalDateTime cbjqfrq;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
