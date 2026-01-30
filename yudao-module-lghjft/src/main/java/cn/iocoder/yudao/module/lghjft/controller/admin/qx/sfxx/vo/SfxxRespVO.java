package cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 身份信息 Response VO")
@Data
public class SfxxRespVO {

    @Schema(description = "ID", example = "1024")
    private Long id;

    @Schema(description = "登录账号ID", example = "1024")
    private Long dlzhId;

    @Schema(description = "登记序号")
    private String djxh;

    @Schema(description = "身份类型（01-08）", example = "01")
    private String sflx;

    @Schema(description = "权限类型（01:管理员,02:一般人）", example = "01")
    private String qxlx;

    @Schema(description = "授权原因", example = "0")
    private Integer sqyy;

    @Schema(description = "解绑原因", example = "0")
    private Integer jbyy;

    @Schema(description = "部门编号", example = "100")
    private Long deptId;

    @Schema(description = "部门名称", example = "某某部门")
    private String deptName;

    @Schema(description = "状态 0:待审核 1:已审核", example = "0")
    private Integer status;

    @Schema(description = "创建者", example = "1")
    private String creator;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新者", example = "1")
    private String updater;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}
