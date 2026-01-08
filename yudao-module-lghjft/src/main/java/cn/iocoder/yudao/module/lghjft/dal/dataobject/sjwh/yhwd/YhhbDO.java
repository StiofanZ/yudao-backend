package cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.yhwd;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 银行行别 DO
 *
 * @author 李文军
 */
@TableName("dm_yhhb")
@KeySequence("dm_yhhb_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YhhbDO {

    /**
     * 银行行别代码
     */
    @TableId(type = IdType.INPUT)
    private String yhhbDm;
    /**
     * 银行行别名称
     */
    private String yhhbmc;
    /**
     * 银行行别简称
     */
    private String yhhbjc;
    /**
     * 顺序号
     */
    private Integer sxh;

}