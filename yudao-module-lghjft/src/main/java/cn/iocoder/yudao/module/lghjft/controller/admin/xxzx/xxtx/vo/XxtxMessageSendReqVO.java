package cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 消息发送 Request VO")
@Data
public class XxtxMessageSendReqVO {

    @Schema(description = "消息ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "消息ID不能为空")
    private Long messageId;

    @Schema(description = "部门ID列表，支持发送到多个部门", example = "[1, 2, 3]")
    private List<Long> deptIds;

    @Schema(description = "用户ID列表，支持发送到多个人", example = "[1024, 2048]")
    private List<Long> userIds;

}
