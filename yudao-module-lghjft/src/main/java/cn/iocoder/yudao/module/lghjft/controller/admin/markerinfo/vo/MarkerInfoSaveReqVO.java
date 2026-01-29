package cn.iocoder.yudao.module.lghjft.controller.admin.markerinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 高德地图标注点信息新增/修改 Request VO")
@Data
public class MarkerInfoSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "32339")
    private Long id;

    @Schema(description = "工会名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "工会名称")
    private String name;

    @Schema(description = "联系电话")
    private String phone;

    @Schema(description = "详细地址", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "详细地址不能为空")
    private String address;

    @Schema(description = "经度（保留6位小数，满足高德地图精度）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "经度（保留6位小数，满足高德地图精度）不能为空")
    private BigDecimal lng;

    @Schema(description = "纬度（保留6位小数，满足高德地图精度）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "纬度（保留6位小数，满足高德地图精度）不能为空")
    private BigDecimal lat;

    @Schema(description = "备注信息", example = "你猜")
    private String remark;
//
//    @Schema(description = "是否删除（0-未删，1-已删）", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "是否删除（0-未删，1-已删）不能为空")
//    private Integer isDeleted;

    @Schema(description = "工作时间")
    private  String jobtime;

    @Schema(description = "级别")
    private  String grade;


}