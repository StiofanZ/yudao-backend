package cn.gszs.yudao.module.lghjft.controller.admin.wfsqtfsq.vo;

import cn.gszs.yudao.module.lghjft.dal.dataobject.wfsqtfsq.WfSqTfsqmxDO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 申请-退费申请新增/修改 Request VO")
@Data
public class WfSqTfsqSaveReqVO {

    @Schema(description = "退费申请ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31462")
    private Long id;

    @Schema(description = "退费申请明细ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22410")
    @NotNull(message = "退费申请明细ID不能为空")
    private Long tfsqmxId;

    @Schema(description = "申请退费类型代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "申请退费类型代码不能为空")
    private Integer sqtflxDm;

    @Schema(description = "流程实例的编号", example = "9171")
    private String processInstanceId;

    @Schema(description = "审批结果", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "审批结果不能为空")
    private Integer status;

    @Schema(description = "申请-退费申请明细列表")
    private List<WfSqTfsqmxDO> wfSqTfsqmxs;

}