package cn.iocoder.yudao.module.lghjft.controller.admin.qx.aqzx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 安全中心个人资料 Response VO")
@Data
public class AqzxProfileResVO {

    @Schema(description = "登录账号 ID")
    private Long id;

    @Schema(description = "用户账号")
    private String yhzh;

    @Schema(description = "用户姓名")
    private String yhxm;

    @Schema(description = "联系电话")
    private String lxdh;

    @Schema(description = "用户邮箱")
    private String yhyx;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "最后登录 IP")
    private String loginIp;

    @Schema(description = "最后登录时间")
    private LocalDateTime loginDate;
}
