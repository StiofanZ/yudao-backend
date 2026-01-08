package cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.skgk;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 收款国库 DO
 *
 * @author 李文军
 */
@TableName("dm_skgk")
@KeySequence("dm_skgk_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkgkDO extends BaseDO {

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

    @TableField(exist = false)  // 告诉MyBatis这个字段不在表中
    private LocalDateTime createTime;

    @TableField(exist = false)
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private String creator;

    @TableField(exist = false)
    private String updater;

    @TableField(exist = false)
    private Boolean deleted;

}