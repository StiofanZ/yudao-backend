package cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.yhbfmx;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 银行拨付明细 DO
 *
 * @author 李文军
 */
@TableName("gh_hkxx_yhbfmx")
@KeySequence("gh_hkxx_yhbfmx_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YhbfmxDO {

    /**
     * 业务参考号
     */
    @TableId
    private Integer bfid;
    /**
     * 收款人编号
     */
    private Long hkxxId;
    /**
     * *收款人账号
     */
    private String zh;
    /**
     * *收款人名称
     */
    private String hm;
    /**
     * *收方开户支行
     */
    private String yhmc;
    /**
     * 收方联行号
     */
    private String sflhh;
    /**
     * 收款人开户地
     */
    private String skrkhd;
    /**
     * 收方邮件地址
     */
    private String sfyjdz;
    /**
     * 收方移动电话
     */
    private String sfyddh;
    /**
     * 币种
     */
    private String rmb;
    /**
     * 付款分行
     */
    private String fkfh;
    /**
     * *结算方式
     */
    private String jsfs;
    /**
     * 业务种类
     */
    private String ywzl;
    /**
     * *付方账号
     */
    private String ffzh;
    /**
     * 付方记账子单元编号
     */
    private String ffjzzdy;
    /**
     * 期望日
     */
    private String qwr;
    /**
     * 期望时间
     */
    private String qwsj;
    /**
     * *用途
     */
    private String yt;
    /**
     * *金额
     */
    private BigDecimal je;
    /**
     * 部门
     */
    private String deptId;
    /**
     * 内部附言(摘要)
     */
    private String fy;
    /**
     * 划款批次号
     */
    private String hkpch;
    /**
     * 拨付汇总批次号
     */
    private String bfhzpch;
    /**
     * 拨付批次号
     */
    private String bfpch;
    /**
     * 分成类型
     */
    private String fclx;
    /**
     * 序号
     */
    private Long xh;
    /**
     * uuid序列号
     */
    private String uuid;
    /**
     * 拨付类型
     */
    private String bflx;
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
     * 备注
     */
    private String bz;
    /**
     * 生成汇总信息
     */
    private String schzxx;
    /**
     * 有效标记
     */
    private String yxbj;
    /**
     * 原始退回标记
     */
    private String ysthbj;
    /**
     * 原始退回日期
     */
    private LocalDateTime ysthrq;
    /**
     * 原始退回原因
     */
    private String ysthyy;
    /**
     * 原始创建人
     */
    private String yscjr;
    /**
     * 原始创建时间
     */
    private LocalDateTime yscjsj;
    /**
     * ysxgr
     */
    private String ysxgr;
    /**
     * 原始修改时间
     */
    private LocalDateTime ysxgsj;
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


}