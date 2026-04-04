package cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.hkxx;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("gh_hkxx")
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HkxxDO {

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

}
