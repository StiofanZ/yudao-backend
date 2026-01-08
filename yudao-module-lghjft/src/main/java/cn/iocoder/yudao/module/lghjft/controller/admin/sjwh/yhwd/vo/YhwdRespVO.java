package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.yhwd.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 银行网点 Response VO")
@Data
@ExcelIgnoreUnannotated
public class YhwdRespVO {

    @Schema(description = "银行行别代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("银行行别代码")
    private String yhhbDm;

    @Schema(description = "网点代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("网点代码")
    private String yhwdDm;

    @Schema(description = "网点名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("网点名称")
    private String yhwdmc;

    @Schema(description = "网点简称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("网点简称")
    private String yhwdjc;

    @Schema(description = "网点行号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("网点行号")
    private String wdhh;

    @Schema(description = "清算行号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("清算行号")
    private String qshh;

    @Schema(description = "行政区划代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划代码")
    private String xzqhDm;

    @Schema(description = "顺序号")
    @ExcelProperty("顺序号")
    private Integer sxh;

    @Schema(description = "有效期止")
    @ExcelProperty("有效期止")
    private LocalDateTime yxqz;

    @Schema(description = "网点地址")
    @ExcelProperty("网点地址")
    private String wddz;

    @Schema(description = "网点电话")
    @ExcelProperty("网点电话")
    private String wddh;

    @Schema(description = "银行行别ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11421")
    @ExcelProperty("银行行别ID")
    private Long yhhbId;

}