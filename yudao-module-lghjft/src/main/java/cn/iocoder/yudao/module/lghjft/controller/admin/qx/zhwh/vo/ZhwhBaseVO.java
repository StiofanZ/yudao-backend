package cn.iocoder.yudao.module.lghjft.controller.admin.qx.zhwh.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 账户维护申请 Base VO")
@Data
public class ZhwhBaseVO {

    @Schema(description = "登录账号 ID")
    private Long dlzhId;

    @Schema(description = "登记序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "登记序号不能为空")
    private String djxh;

    @Schema(description = "新基层工会账号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "基层工会账号不能为空")
    private String newJcghzh;

    @Schema(description = "新基层工会户名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "基层工会户名不能为空")
    private String newJcghhm;

    @Schema(description = "新基层工会行号")
    private String newJcghhh;

    @Schema(description = "新基层工会开户行", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "基层工会开户行不能为空")
    private String newJcghyh;

    @Schema(description = "申请原因", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "申请原因不能为空")
    private String applyReason;

    @Schema(description = "材料附件 URL 列表")
    private List<String> materials;
}
