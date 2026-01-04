package cn.iocoder.yudao.module.dm.dal.dataobject.xzqh;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 行政区划 DO
 *
 * @author 李文军
 */
@TableName("dm_xzqh")
@KeySequence("dm_xzqh_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
//@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class XzqhDO {

    public static final Long SJXZQH_DM_ROOT = 0L;

    /**
     * 行政区划代码
     */
    @TableId
    private Long xzqhDm;
    /**
     * 行政区划名称
     */
    private String xzqhmc;
    /**
     * 上级行政区划代码
     */
    private String sjxzqhDm;
    /**
     * 行政区划级别
     */
    private String xzqhjb;


}