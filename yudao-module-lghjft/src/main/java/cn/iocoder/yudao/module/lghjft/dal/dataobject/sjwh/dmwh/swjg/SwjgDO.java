package cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.dmwh.swjg;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 税务机关 DO
 *
 * @author 李文军
 */
@TableName("dm_swjg")
@KeySequence("dm_swjg_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SwjgDO {

    /**
     * 税务机关代码
     */
    @TableId(type = IdType.INPUT)
    private String swjgDm;
    /**
     * 税务机关名称
     */
    private String swjgmc;
    /**
     * 税务机关简称
     */
    private String swjgjc;
    /**
     * 地址
     */
    private String dz;
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
     * 手续费账号
     */
    private String sxfzh;
    /**
     * 户名
     */
    private String sxfhm;
    /**
     * 行号
     */
    private String sxfhh;
    /**
     * 银行
     */
    private String sxfyh;
    /**
     * 上级税务机关代码
     */
    private String sjswjgDm;
    /**
     * 稽查局标记
     */
    private String jcjbj;
    /**
     * 工会机构代码
     */
    private String ghjgDm;
    /**
     * 顺序号
     */
    private Long sxh;
    /**
     * 校验码
     */
    private String jym;


}