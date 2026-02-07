package cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 基层账户空需维护对象创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GhHjCreateReqVO extends GhHjBaseVO {

    @Schema(description = "登记序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "登记序号不能为空")
    private String djxh;

    @Schema(description = "工会机构代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "工会机构代码不能为空")
    private String deptId;

    @Schema(description = "社会信用代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "社会信用代码不能为空")
    private String shxydm;

    @Schema(description = "纳税人名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "纳税人名称不能为空")
    private String nsrmc;

    @Schema(description = "成立工会标志", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "成立工会标志不能为空")
    private String clghbj;

}
