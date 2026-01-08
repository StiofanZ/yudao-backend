package cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.yhwd;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 银行网点 DO
 *
 * @author 李文军
 */
@TableName("dm_yhwd")
@KeySequence("dm_yhwd_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YhwdDO extends BaseDO {

    /**
     * 银行行别代码
     */
    private String yhhbDm;
    /**
     * 网点代码
     */
    private String yhwdDm;
    /**
     * 网点名称
     */
    private String yhwdmc;
    /**
     * 网点简称
     */
    private String yhwdjc;
    /**
     * 网点行号
     */
    private String wdhh;
    /**
     * 清算行号
     */
    private String qshh;
    /**
     * 行政区划代码
     */
    private String xzqhDm;
    /**
     * 顺序号
     */
    private Integer sxh;
    /**
     * 有效期止
     */
    private LocalDateTime yxqz;
    /**
     * 网点地址
     */
    private String wddz;
    /**
     * 网点电话
     */
    private String wddh;
    /**
     * 银行行别ID
     */
    @TableId
    private Long yhhbId;
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