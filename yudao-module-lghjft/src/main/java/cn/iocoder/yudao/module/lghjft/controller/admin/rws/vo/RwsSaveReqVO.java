package cn.iocoder.yudao.module.lghjft.controller.admin.rws.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 年度任务新增/修改 Request VO")
@Data
public class RwsSaveReqVO {

    @Schema(description = "任务id", requiredMode = Schema.RequiredMode.REQUIRED, example = "9124")
    private Integer rwid;

    @Schema(description = "任务类型	")
    private String rwlx;

    @Schema(description = "年度")
    private String nd;

    @Schema(description = "单位代码")
    private String dwdm;

    @Schema(description = "单位名称")
    private String dwmc;

    @Schema(description = "任务数")
    private BigDecimal rws;

    @Schema(description = "完成数")
    private String wcs;

}