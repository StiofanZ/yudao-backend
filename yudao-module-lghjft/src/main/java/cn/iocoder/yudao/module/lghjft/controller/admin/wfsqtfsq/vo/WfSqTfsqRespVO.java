package cn.iocoder.yudao.module.lghjft.controller.admin.wfsqtfsq.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 申请-退费申请 Response VO")
@Data
public class WfSqTfsqRespVO {

    @Schema(description = "退费申请ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "申请退费类型代码", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer sqtflxDm;

    @Schema(description = "流程实例的编号", example = "process-123")
    private String processInstanceId;

    @Schema(description = "审批结果", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    private Integer status;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

    @Schema(description = "退费明细列表")
    private List<WfSqTfsqmxRespVO> mxList;

}
