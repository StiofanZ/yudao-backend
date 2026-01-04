package cn.iocoder.yudao.module.dm.controller.admin.xzqh.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 行政区划新增/修改 Request VO")
@Data
public class XzqhSaveReqVO {

    @Schema(description = "行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long xzqhDm;

    @Schema(description = "行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划名称不能为空")
    private String xzqhmc;

    @Schema(description = "上级行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "上级行政区划代码不能为空")
    private String sjxzqhDm;

    @Schema(description = "行政区划级别", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划级别不能为空")
    private String xzqhjb;

}