package cn.iocoder.yudao.module.lghjft.dal.dataobject.jf.ghjf;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 税务入库 DO
 *
 * @author 芋道源码
 */
@TableName("gh_jf")
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GhJfDO {

    /**
     * 税票ID
     */
    @TableId
    private Integer ghjfId;
    /**
     * 登记序号
     */
    private String spuuid;
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
     * 工会机构
     */
    private String deptId;
    /**
     * 工会机构行政级别
     */
    private String ghjgxzjb;
    /**
     * 成立工会标记
     */
    private String clghbj;
    /**
     * 行业工会标志
     */
    private String hyghbz;
    /**
     * 属地工会机构代码
     */
    private String sdghjgDm;
    /**
     * 属地工会行政级别
     */
    private String sdghjgxzjb;
    /**
     * 主管税务局代码
     */
    private String zgswjDm;
    /**
     * 主管税务分局代码
     */
    private String zgswskfjDm;
    /**
     * 税管员代码
     */
    private String ssglyDm;
    /**
     * 工会类别代码
     */
    private String ghlbDm;
    /**
     * 系统类别代码
     */
    private String xtlbDm;
    /**
     * 组织机构类型代码
     */
    private String zzjglxDm;
    /**
     * 行业代码
     */
    private String hyDm;
    /**
     * 登记注册类型代码
     */
    private String djzclxDm;
    /**
     * 单位隶属关系代码
     */
    private String dwlsgxDm;
    /**
     * 申报类别代码
     */
    private String sblbDm;
    /**
     * 申报日期
     */
    private LocalDateTime nssbrq;
    /**
     * 票证序号
     */
    private String pzxh;
    /**
     * 票证明细号
     */
    private BigDecimal pzmxxh;
    /**
     * 所属期起
     */
    private LocalDateTime skssqq;
    /**
     * 所属期止
     */
    private LocalDateTime skssqz;
    /**
     * 征收税务局代码
     */
    private String zsswjgDm;
    /**
     * 税款所属税务机关
     */
    private String skssswjgDm;
    /**
     * 税款国库代码
     */
    private String skgkDm;
    /**
     * 电子票证号码
     */
    private String dzsphm;
    /**
     * 票证种类
     */
    private String pzzlDm;
    /**
     * 票证字轨
     */
    private String pzzgDm;
    /**
     * 票证号码
     */
    private String pzhm;
    /**
     * 开票员代码
     */
    private String kpyDm;
    /**
     * 开票员姓名
     */
    private String kpyXm;
    /**
     * 征收品目代码
     */
    private String zspmDm;
    /**
     * 征收子目代码
     */
    private String zszmDm;
    /**
     * 预算科目代码
     */
    private String yskmDm;
    /**
     * 工资总额
     */
    private BigDecimal gzze;
    /**
     * 税率
     */
    private BigDecimal sl;
    /**
     * 应纳税额
     */
    private BigDecimal ynse;
    /**
     * 减免税额
     */
    private BigDecimal jmse;
    /**
     * 已缴税额
     */
    private BigDecimal yjse;
    /**
     * 应补退税额
     */
    private BigDecimal ybtse;
    /**
     * 缴款方式
     */
    private String jkfsDm;
    /**
     * 缴费行别
     */
    private String jfhb;
    /**
     * 缴费正好
     */
    private String jfzh;
    /**
     * 缴费户名
     */
    private String jfhm;
    /**
     * 缴费行号
     */
    private String jfhh;
    /**
     * 缴费银行
     */
    private String jfyh;
    /**
     * 入库日期
     */
    private LocalDateTime rkrq;
    /**
     * 结算标记
     */
    private String jsbj;
    /**
     * 结算日期
     */
    private LocalDateTime jsrq;
    /**
     * 结算操作员
     */
    private String jsczy;
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
     * 基层工会比例
     */
    private BigDecimal jcghbl;
    /**
     * 基层工会金额
     */
    private BigDecimal jcghje;
    /**
     * 筹备金账号
     */
    private String cbjzh;
    /**
     * 筹备金户名
     */
    private String cbjhm;
    /**
     * 筹备金行号
     */
    private String cbjhh;
    /**
     * 筹备金比例
     */
    private BigDecimal cbjbl;
    /**
     * 筹备金金额
     */
    private BigDecimal cbjje;
    /**
     * 行业工会账号
     */
    private String hyghzh;
    /**
     * 行业工会户名
     */
    private String hyghhm;
    /**
     * 行业工会行号
     */
    private String hyghhh;
    /**
     * 行业工会比例
     */
    private BigDecimal hyghbl;
    /**
     * 行业工会金额
     */
    private BigDecimal hyghje;
    /**
     * 县级工会账号
     */
    private String xjghzh;
    /**
     * 县级工会户名
     */
    private String xjghhm;
    /**
     * 县级工会行号
     */
    private String xjghhh;
    /**
     * 县级工会比例
     */
    private BigDecimal xjghbl;
    /**
     * 县级工会金额
     */
    private BigDecimal xjghje;
    /**
     * 市级工会账号
     */
    private String sjghzh;
    /**
     * 市级工会户名
     */
    private String sjghhm;
    /**
     * 市级工会行号
     */
    private String sjghhh;
    /**
     * 市级工会比例
     */
    private BigDecimal sjghbl;
    /**
     * 市级工会金额
     */
    private BigDecimal sjghje;
    /**
     * 省总工会账号
     */
    private String szghzh;
    /**
     * 省总工会户名
     */
    private String szghhm;
    /**
     * 省总工会行号
     */
    private String szghhh;
    /**
     * 省总工会比例
     */
    private BigDecimal szghbl;
    /**
     * 省总工会金额
     */
    private BigDecimal szghje;
    /**
     * 全总账号
     */
    private String qgghzh;
    /**
     * 全总户名
     */
    private String qgghhm;
    /**
     * 全总行号
     */
    private String qgghhh;
    /**
     * 全总比例
     */
    private BigDecimal qgghbl;
    /**
     * 全总金额
     */
    private BigDecimal qgghje;
    /**
     * 稽查局账号
     */
    private String jcjzh;
    /**
     * 稽查局户名
     */
    private String jcjhm;
    /**
     * 稽查局户名
     */
    private String jcjhh;
    /**
     * 稽查局比例
     */
    private BigDecimal jcjbl;
    /**
     * 稽查局金额
     */
    private BigDecimal jcjje;
    /**
     * 稽查局文书号
     */
    private String jcwsh;
    /**
     * 省税局账号
     */
    private String sdszh;
    /**
     * 省税局户名
     */
    private String sdshm;
    /**
     * 省税局行号
     */
    private String sdshh;
    /**
     * 省税局比例
     */
    private BigDecimal sdsbl;
    /**
     * 省税局金额
     */
    private BigDecimal sdsje;
    /**
     * 税务机关账号
     */
    private String swjgzh;
    /**
     * 税务机关户名
     */
    private String swjghm;
    /**
     * 税务机关行号
     */
    private String swjghh;
    /**
     * 税务机关比例
     */
    private BigDecimal swjgbl;
    /**
     * 税务机关金额
     */
    private BigDecimal swjgje;
    /**
     * 基层经费拨付状态
     */
    private String cbjthbj;
    /**
     * 处理日期
     */
    private LocalDateTime cbjthrq;
    /**
     * 操作员
     */
    private String cbjthczy;
    /**
     * 校验码
     */
    private String jym;
    /**
     * 划款批次号
     */
    private String hkpch;
    /**
     * 备注
     */
    private String bz;
    /**
     * 分配比例UUID
     */
    private String bluuid;
    /**
     * 属地工会账号
     */
    private String sdghzh;
    /**
     * 属地工会户名
     */
    private String sdghhm;
    /**
     * 属地工会行号
     */
    private String sdghhh;
    /**
     * 属地工会比例
     */
    private BigDecimal sdghbl;
    /**
     * 属地工会金额
     */
    private BigDecimal sdghje;

}
