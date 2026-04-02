package cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 户籍调拨 Request VO")
@Data
public class GhHjAllocationReqVO {

    @Schema(description = "目标部门ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "目标部门ID不能为空")
    private String deptId;

    @Schema(description = "行业工会标志")
    private String hyghbz;

    @Schema(description = "登记序号列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "登记序号列表不能为空")
    private List<String> djxhs;
}
