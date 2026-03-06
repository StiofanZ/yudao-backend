package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.wtfk.vo;

import cn.iocoder.yudao.framework.common.validation.Mobile;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 问题反馈新增/修改 Request VO")
@Data
public class GhNrglWtfkSaveReqVO {

    @Schema(description = "主键ID", example = "1")
    private Long id;

    @Schema(description = "反馈类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotBlank(message = "反馈类型不能为空")
    private String lx;

    @Schema(description = "平台名称", example = "网页端")
    private String ptmc;

    @Schema(description = "反馈内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "反馈内容不能为空")
    private String nr;

    @Schema(description = "联系电话")
    @Mobile
    private String lxdh;

    @Schema(description = "联系邮箱")
    private String lxyx;

    @Schema(description = "处理状态")
    private Integer zt;

    @Schema(description = "处理人ID")
    private Long clrId;

    @Schema(description = "处理时间")
    private LocalDateTime clsj;

    @Schema(description = "处理说明")
    private String clsm;

    @Schema(description = "附件列表")
    private List<FjItem> fjList;

    @Data
    @Schema(description = "附件项")
    public static class FjItem {
        @Schema(description = "文件路径", requiredMode = Schema.RequiredMode.REQUIRED)
        private String wjlj;
        @Schema(description = "文件名")
        private String wjmc;
        @Schema(description = "原始文件名")
        private String ywjmc;
    }
}
