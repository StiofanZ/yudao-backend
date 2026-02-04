package cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wfsqhzjf;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import java.time.LocalDate;


/**
 * 工会经费汇总缴纳申请表（主表） DO
 *
 * @author 李文军
 */
@TableName("lghjft_wf_hz")
@KeySequence("lghjft_wf_hz_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WfHzDO extends BaseDO {

    /**
     * 主键ID，自增
     */
    @TableId
    private Long id;
    /**
     * 申请汇总缴费单位社会信用代码
     */
    private String xyxdm;
    /**
     * 申请汇总缴费单位全称
     */
    private String dwqc;
    /**
     * 主管税务部门
     */
    private String zgsbm;
    /**
     * 缴费单位地址
     */
    private String dwdz;
    /**
     * 工会法人登记证件号码
     */
    private String ghfrdjzjh;
    /**
     * 缴费单位工会全称
     */
    private String ghqc;
    /**
     * 职工总人数
     */
    private Integer zzgzs;
    /**
     * 工会会员数
     */
    private Integer ghyhs;
    /**
     * 工会负责人
     */
    private String ghfzr;
    /**
     * 联系电话
     */
    private String lxdh;
    /**
     * 工会账户账号
     */
    private String ghzhzh;
    /**
     * 开户银行名称
     */
    private String khyhmc;
    /**
     * 工会账户户名
     */
    private String ghzhhm;
    /**
     * 开户银行网点代码
     */
    private String khyhwdm;
    /**
     * 汇总申报缴纳原因
     */
    private String hzbsjygy;
    /**
     * 负责人姓名
     */
    private String fzrxm;
    /**
     * 经办人姓名
     */
    private String jbrxm;
    /**
     * 经办人联系电话
     */
    private String jbrdh;
    /**
     * 申请日期
     */
    private LocalDate sqrq;
    /**
     * 分支机构总数
     */
    private Integer fjgzs;
    /**
     * 主管工会审核意见
     */
    private String zgghsjy;
    /**
     * 主管工会审核负责人
     */
    private String zgghsfzr;
    /**
     * 主管工会审核经办人
     */
    private String zgghsjbr;
    /**
     * 主管工会审核经办人电话
     */
    private String zgghsjbrdh;
    /**
     * 主管工会审核日期
     */
    private LocalDate zgghsrq;
    /**
     * 省总工会审核意见
     */
    private String sghzsjy;
    /**
     * 省总工会审核负责人
     */
    private String sghsfzr;
    /**
     * 省总工会审核日期
     */
    private LocalDate sghsrq;

    // 流程
    private String processInstanceId;


    @TableField(exist = false)
    private String creator;

    @TableField(exist = false)
    private Boolean deleted;

}