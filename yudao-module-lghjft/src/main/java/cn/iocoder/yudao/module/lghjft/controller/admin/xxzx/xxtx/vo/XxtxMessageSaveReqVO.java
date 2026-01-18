package cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 消息创建/修改 Request VO")
@Data
public class XxtxMessageSaveReqVO {

    @Schema(description = "消息ID", example = "1024")
    private Long id;

    @Schema(description = "消息标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "系统消息")
    @NotBlank(message = "消息标题不能为空")
    @Size(max = 255, message = "消息标题不能超过255个字符")
    private String title;

    @Schema(description = "消息内容", requiredMode = Schema.RequiredMode.REQUIRED, example = "这是一条系统消息")
    @NotBlank(message = "消息内容不能为空")
    private String content;

    @Schema(description = "消息类型（0：系统消息，1：业务消息）", example = "0")
    private Integer messageType;

    @Schema(description = "消息状态（0：草稿，1：已发送，2：已撤回）", example = "0")
    private Integer status;

    @Schema(description = "部门ID列表", example = "[1, 2]")
    private List<Long> deptIds;

    @Schema(description = "用户ID列表", example = "[10, 20]")
    private List<Long> userIds;

}
