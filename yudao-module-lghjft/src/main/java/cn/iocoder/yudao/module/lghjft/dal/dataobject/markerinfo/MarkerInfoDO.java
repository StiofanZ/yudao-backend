package cn.iocoder.yudao.module.lghjft.dal.dataobject.markerinfo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 高德地图标注点信息 DO
 *
 * @author 李文军
 */
@TableName("gh_marker_info")
@KeySequence("gh_marker_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkerInfoDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 地点名称
     */
    private String name;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 经度（保留6位小数，满足高德地图精度）
     */
    private BigDecimal lng;
    /**
     * 纬度（保留6位小数，满足高德地图精度）
     */
    private BigDecimal lat;
    /**
     * 工作时间
     */
    private  String jobtime;
    /**
     *级别
     */
    private  String grade;
    /**
     * 备注信息
     */
    private String remark;
    /**
     * 是否删除（0-未删，1-已删）
     */
    private Integer isDeleted;


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