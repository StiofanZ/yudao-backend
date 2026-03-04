package cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.jcjfzz;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@TableName("gh_hkxx")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HkxxDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Integer hkxxId;

    /**
     * 划款批次号
     */
    private String hkpch;

    /**
     * 序号
     */
    private Long xh;

    /**
     * 类型
     */
    private String lx;

    /**
     * 账号
     */
    private String zh;

    /**
     * 户名
     */
    private String hm;

    /**
     * 行号
     */
    private String hh;

    /**
     * 正确账号
     */
    private String xzh;

    /**
     * 正确户名
     */
    private String xhm;

    /**
     * 正确行号
     */
    private String xhh;

    /**
     * 金额
     */
    private BigDecimal je;
    /**
     * 工会机构ID
     */
    private String deptId;

    /**
     * 地址
     */
    private String dz;

    /**
     * 附言
     */
    private String fy;

    /**
     * 校验码
     */
    private String jym;

    /**
     * 退回标志
     */
    private String thbj;

    /**
     * 退回日期
     */
    private LocalDateTime thrq;

    /**
     * 退回原因
     */
    private String thyy;

    /**
     * 生成划款批次号
     */
    private String schkpch;

    /**
     * 备注
     */
    private String bz;

    /**
     * 修改标志
     */
    private String xgbj;

    /**
     * 系统生成标志
     */
    private String scbz;

    /**
     * 有效标志
     */
    private String yxbj;

    /**
     * 修改人
     */
    private String xgr;

    /**
     * 修改时间
     */
    private LocalDateTime xgsj;

    private LocalDateTime createTime;
    //创建人
    private String createBy;

    private LocalDateTime updateTime;
    //修改人
    private String updateBy;


//    子表字段
    /**
     * 到账标记
     */
    @TableField(exist = false)
    private String dzbj;
    /**
     * 确认日期
     */
    @TableField(exist = false)
    private LocalDateTime qrrq;
    /**
     * 银行回单号
     */
    @TableField(exist = false)
    private String yhhdh;

    // ========== 一对多关联：到账确认列表 ==========
    @TableField(exist = false)
    private List<HkxxQrszDO> jcjfdzList;


//    不存在字段
    @TableField(exist = false)
    private String creator;

    @TableField(exist = false)
    private String updater;

    @TableField(exist = false)
    private Boolean deleted;


}