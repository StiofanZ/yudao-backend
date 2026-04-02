package cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejf24;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 24年小额确认 DO
 */
@TableName("gh_hj_xejf24")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class GhHjXejf24DO {

    /**
     * 工会机构代码
     */
    @TableId
    private String deptId;
    /**
     * 行业工会标志
     */
    private String hyghbz;
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
     * 纳税人简称
     */
    private String nsrjc;
    /**
     * 主管税务机关代码
     */
    private String zgswjDm;
    /**
     * 主管税务机关名称
     */
    private String zgswjmc;
    /**
     * 科所分局代码
     */
    private String zgswskfjDm;
    /**
     * 科所分局名称
     */
    private String zgswskfjmc;
    /**
     * 税管员代码
     */
    private String ssglyDm;
    /**
     * 税管员姓名
     */
    private String ssglyxm;
    /**
     * 组织机构类型代码
     */
    private String zzjglxDm;
    /**
     * 组织机构类型名称
     */
    private String zzjglxmc;
    /**
     * 行业代码
     */
    private String hyDm;
    /**
     * 行业名称
     */
    private String hymc;
    /**
     * 登记注册类型代码
     */
    private String djzclxDm;
    /**
     * 登记注册类型名称
     */
    private String djzclxmc;
    /**
     * 单位隶属关系代码
     */
    private String dwlsgxDm;
    /**
     * 单位隶属关系名称
     */
    private String dwlsgxmc;
    /**
     * 总共人数
     */
    private BigDecimal zgrs;
    /**
     * 纳税人状态代码
     */
    private String nsrztDm;
    /**
     * 纳税人状态名称
     */
    private String nsrztmc;
    /**
     * 发证日期
     */
    private LocalDateTime fzcrq;
    /**
     * 注销日期
     */
    private LocalDateTime zxrq;
    /**
     * 注册地址
     */
    private String zcdz;
    /**
     * 邮政编码
     */
    private String yzbm;
    /**
     * 联系人
     */
    private String lxr;
    /**
     * 联系电话
     */
    private String lxdh;
    /**
     * 工会类别代码
     */
    private String ghlbDm;
    /**
     * 系统类别代码
     */
    private String xtlbDm;
    /**
     * 户籍分类1
     */
    private String hjfl1Dm;
    /**
     * 户籍分类2
     */
    private String hjfl2Dm;
    /**
     * 户籍分类3
     */
    private String hjfl3Dm;
    /**
     * 24小额原始情况
     */
    private String hjfl4Dm;
    /**
     * 24小额确认情况
     */
    private String hjfl5Dm;
    /**
     * 24小额类型
     */
    private String hjfl6Dm;
    /**
     * 小微上报时间
     */
    private LocalDateTime hjfl7Dm;
    /**
     * 是否已建会缴纳筹备金
     */
    private String hjfl8Dm;
    /**
     * 小微企业标志
     */
    private String hjfl9Dm;
    /**
     * 属地工会机构代码
     */
    private String sdghjgDm;
    /**
     * 成立工会标志
     */
    private String clghbj;
    /**
     * 成立工会日期
     */
    private LocalDateTime clghrq;
    /**
     * 基层工会代码
     */
    private String jcghdm;
    /**
     * 基层工会名称
     */
    private String jcghmc;
    /**
     * 基层工会账户
     */
    private String jcghzh;
    /**
     * 基层工会户名
     */
    private String jcghhm;
    /**
     * 基层工会行号
     */
    private String jcghhh;
    /**
     * 基层工会银行
     */
    private String jcghyh;
    /**
     * 备注
     */
    private String bz;
    /**
     * 校验码
     */
    private String jym;
    /**
     * 纳税人识别号
     */
    private String nsrsbh;
    /**
     * 文件地址
     */
    private String fileurl;
}
