package cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.rws;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * 年度任务 DO
 *
 * @author 李文军
 */
@TableName("cxtj_rws")
@KeySequence("cxtj_rws_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RwsDO {

    /**
     * 任务id
     */
    @TableId
    private Long rwid;
    /**
     * 任务类型	
     *
     * 枚举 {@link TODO sys_rwlx 对应的类}
     */
    private String rwlx;
    /**
     * 年度
     *
     * 枚举 {@link TODO sys_nd 对应的类}
     */
    private String nd;
    /**
     * 单位代码
     */
    private String dwdm;
    /**
     * 单位名称
     */
    private String dwmc;
    /**
     * 任务数
     */
    private BigDecimal rws;
    /**
     * 完成数
     */
    private String wcs;

}