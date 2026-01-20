package cn.iocoder.yudao.module.lghjft.controller.admin.wtfk.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 问题反馈处理 Request VO")
@Data
public class WtfkProcessReqVO {

    @Schema(description = "反馈编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "反馈编号不能为空")
    private Long id;

    @Schema(description = "处理状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "处理状态不能为空")
    private Integer status;

    @Schema(description = "处理备注/意见", requiredMode = Schema.RequiredMode.REQUIRED, example = "已联系技术人员修复")
    @NotEmpty(message = "处理备注不能为空")
    private String processNotes;

}