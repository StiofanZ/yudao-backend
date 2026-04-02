package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.wtfk.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 问题反馈处理明细 Response VO")
@Data
public class GhNrglWtfkClmxResVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "问题反馈ID")
    private Long wtfkId;

    @Schema(description = "处理人ID")
    private Long clrId;

    @Schema(description = "处理人姓名")
    private String clrmc;

    @Schema(description = "处理说明")
    private String clsm;

    @Schema(description = "处理状态")
    private Integer zt;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
