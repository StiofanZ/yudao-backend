package cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 消息分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class XxtxMessagePageReqVO extends PageParam {

    @Schema(description = "消息标题，模糊匹配", example = "系统")
    private String title;

    @Schema(description = "消息类型（0：系统消息，1：业务消息）", example = "0")
    private Integer messageType;

    @Schema(description = "消息状态（0：草稿，1：已发送，2：已撤回）", example = "1")
    private Integer status;

    @Schema(description = "发送者ID", example = "1024")
    private Long senderId;

    @Schema(description = "发送时间范围开始", example = "2023-01-01")
    private String sendTimeBegin;

    @Schema(description = "发送时间范围结束", example = "2023-12-31")
    private String sendTimeEnd;

    @Schema(description = "阅读状态（0：未读，1：已读）", example = "1")
    private Integer readStatus;

    @Schema(description = "接收着ID", example = "1")
    private Integer receiverId;

}
