package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.skgk.vo;

import cn.idev.excel.annotation.ExcelIgnore;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 收款国库 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SkgkRespVO {

    @Schema(description = "国库ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19910")
    private Integer gkId;


    @Schema(description = "税款国库代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("税款国库代码")
    private String skgkDm;

    @Schema(description = "税款国库名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("税款国库名称")
    private String skgkmc;

    @Schema(description = "税款国库简称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("税款国库简称")
    private String skgkjc;

    @Schema(description = "行政区划代码")
    @ExcelIgnore // 新增这一行！导出时忽略该字段
    private String xzqhDm;



}