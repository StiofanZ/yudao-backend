package cn.iocoder.yudao.module.lghjft.controller.app.xxzx.xxtx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 消息响应 VO")
@Data
public class XxtxMessageAppRespVO {

    @Schema(description = "消息ID", example = "1024")
    private Long id;

    @Schema(description = "消息标题", example = "系统消息")
    private String title;

    @Schema(description = "消息内容", example = "这是一条系统消息")
    private String content;

    @Schema(description = "消息类型（0：系统消息，1：业务消息）", example = "0")
    private Integer messageType;

    @Schema(description = "发送者ID", example = "1024")
    private Long senderId;

    @Schema(description = "发送者姓名", example = "管理员")
    private String senderName;

    @Schema(description = "发送时间", example = "2023-01-01 12:00:00")
    private String sendTime;

    @Schema(description = "消息状态（0：草稿，1：已发送，2：已撤回）", example = "1")
    private Integer status;

    @Schema(description = "创建时间", example = "2023-01-01 12:00:00")
    private String createTime;

    @Schema(description = "部门ID列表", example = "[1, 2]")
    private List<Long> deptIds;

    @Schema(description = "用户ID列表", example = "[10, 20]")
    private List<Long> userIds;

    @Schema(description = "部门名称列表", example = "['研发部', '测试部']")
    private List<String> deptNames;

    @Schema(description = "用户名称列表", example = "['张三', '李四']")
    private List<String> userNames;

}
