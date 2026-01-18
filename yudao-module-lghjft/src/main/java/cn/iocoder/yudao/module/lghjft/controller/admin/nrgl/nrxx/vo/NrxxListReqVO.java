package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.nrxx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 内容管理列表 Request VO")
@Data
public class NrxxListReqVO {

    @Schema(description = "标题", example = "办事指南")
    private String title;

    @Schema(description = "内容类型", example = "guide")
    private String type;

    @Schema(description = "状态", example = "1")
    private Integer status;

}
