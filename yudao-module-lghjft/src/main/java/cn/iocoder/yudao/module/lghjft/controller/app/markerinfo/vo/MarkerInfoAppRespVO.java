package cn.iocoder.yudao.module.lghjft.controller.app.markerinfo.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "用户 App - 办事地图标注点信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MarkerInfoAppRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "32339")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "工会名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("工会名称")
    private String name;

    @Schema(description = "联系电话")
    @ExcelProperty("联系电话")
    private String phone;

    @Schema(description = "详细地址", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("详细地址")
    private String address;

    @Schema(description = "工作时间")
    private  String jobtime;

    @Schema(description = "级别")
    private  String grade;

    @Schema(description = "经度（保留6位小数，满足高德地图精度）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("经度（保留6位小数，满足高德地图精度）")
    private BigDecimal lng;

    @Schema(description = "纬度（保留6位小数，满足高德地图精度）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("纬度（保留6位小数，满足高德地图精度）")
    private BigDecimal lat;

    @Schema(description = "备注信息", example = "你猜")
    @ExcelProperty("备注信息")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;


    @Schema(description = "工会级别文字显示")
    private String gradeText;  // 新增字段


//    @Schema(description = "是否删除（0-未删，1-已删）", requiredMode = Schema.RequiredMode.REQUIRED)
//    @ExcelProperty("是否删除（0-未删，1-已删）")
//    private Integer isDeleted;
  }