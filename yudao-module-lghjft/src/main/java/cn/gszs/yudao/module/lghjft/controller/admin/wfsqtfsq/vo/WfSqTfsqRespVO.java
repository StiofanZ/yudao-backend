package cn.gszs.yudao.module.lghjft.controller.admin.wfsqtfsq.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 申请-退费申请 Response VO")
@Data
@ExcelIgnoreUnannotated
public class WfSqTfsqRespVO {

    @Schema(description = "退费申请ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31462")
    @ExcelProperty("退费申请ID")
    private Long id;

    @Schema(description = "退费申请明细ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22410")
    @ExcelProperty("退费申请明细ID")
    private Long tfsqmxId;

    @Schema(description = "申请退费类型代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("申请退费类型代码")
    private Integer sqtflxDm;

    @Schema(description = "流程实例的编号", example = "9171")
    @ExcelProperty("流程实例的编号")
    private String processInstanceId;

    @Schema(description = "审批结果", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("审批结果")
    private Integer status;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}