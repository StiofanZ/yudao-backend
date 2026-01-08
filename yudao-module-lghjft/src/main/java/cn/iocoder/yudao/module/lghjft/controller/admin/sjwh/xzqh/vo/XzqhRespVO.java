package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.xzqh.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 行政区划 Response VO")
@Data
@ExcelIgnoreUnannotated
public class XzqhRespVO {

    @Schema(description = "行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划代码")
    private Long xzqhDm;

    @Schema(description = "行政区划名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划名称")
    private String xzqhmc;

    @Schema(description = "上级行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("上级行政区划代码")
    private String sjxzqhDm;

    @Schema(description = "行政区划级别", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划级别")
    private String xzqhjb;

}