package cn.iocoder.yudao.module.lghjft.controller.admin.markerinfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 高德地图标注点信息分页 Request VO")
@Data
public class MarkerInfoPageReqVO extends PageParam {

    @Schema(description = "地点名称", example = "赵六")
    private String name;

    @Schema(description = "联系电话")
    private String phone;

    @Schema(description = "详细地址")
    private String address;

    @Schema(description = "经度（保留6位小数，满足高德地图精度）")
    private BigDecimal lng;

    @Schema(description = "纬度（保留6位小数，满足高德地图精度）")
    private BigDecimal lat;

    @Schema(description = "备注信息", example = "你猜")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "工作时间")
    private  String jobtime;
    @Schema(description = "级别")
    private  String grade;
//    @Schema(description = "是否删除（0-未删，1-已删）")
//    private Integer isDeleted;

    @Schema(description = "搜索关键字", example = "名称或地址")
    private String searchKey;

}