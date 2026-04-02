package cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.dmwh.skgk;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 收款国库 DO
 *
 * @author 李文军
 */
@TableName("dm_skgk")
@KeySequence("dm_skgk_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkgkDO {

    /**
     * 国库ID
     */
    @TableId
    private Integer gkId;
    /**
     * 税款国库代码
     */
    private String skgkDm;
    /**
     * 税款国库名称
     */
    private String skgkmc;
    /**
     * 税款国库简称
     */
    private String skgkjc;
    /**
     * 行政区划代码
     */
    private String xzqhDm;


}