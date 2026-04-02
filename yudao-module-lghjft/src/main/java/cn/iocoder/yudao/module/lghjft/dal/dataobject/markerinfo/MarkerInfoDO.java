package cn.iocoder.yudao.module.lghjft.dal.dataobject.markerinfo;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 高德地图标注点信息 DO
 *
 * @author 李文军
 */
@TableName("gh_marker_info")
@KeySequence("gh_marker_info_seq")
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkerInfoDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 行政区划代码
     */
    private Integer xzqhDm;
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
     * 经度
     */
    private BigDecimal lng;
    /**
     * 纬度
     */
    private BigDecimal lat;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 工作时间
     */
    private String jobtime;
    /**
     * 级别
     */
    private String grade;

}
