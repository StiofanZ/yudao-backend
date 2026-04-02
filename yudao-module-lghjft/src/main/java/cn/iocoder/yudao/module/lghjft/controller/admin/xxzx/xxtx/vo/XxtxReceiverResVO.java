package cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 消息接收明细 Response VO")
@Data
public class XxtxReceiverResVO {

    @Schema(description = "接收记录ID", example = "1")
    private Long id;

    @Schema(description = "消息ID", example = "1")
    private Long messageId;

    @Schema(description = "接收人ID", example = "1")
    private Long receiverId;

    @Schema(description = "接收人姓名", example = "张三")
    private String receiverName;

    @Schema(description = "手机号", example = "13800000000")
    private String lxdh;

    @Schema(description = "阅读状态", example = "0")
    private Integer readStatus;

    @Schema(description = "阅读时间")
    private String readTime;

    @Schema(description = "短信日志ID", example = "100")
    private Long dxrzid;

    @Schema(description = "短信状态", example = "10")
    private Integer dxzt;

    @Schema(description = "短信备注", example = "发送成功")
    private String dxbz;
}
