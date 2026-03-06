package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zxzx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "在线咨询 Response VO")
@Data
public class ZxzxRespVO {

    @Schema(description = "主键 ID")
    private Long id;

    @Schema(description = "咨询单号")
    private String consultNo;

    @Schema(description = "用户 ID")
    private Long yhId;

    @Schema(description = "用户名")
    private String yhmc;

    @Schema(description = "平台名称")
    private String ptmc;

    @Schema(description = "咨询内容")
    private String nr;

    @Schema(description = "联系电话")
    private String lxdh;

    @Schema(description = "联系邮箱")
    private String lxyx;

    @Schema(description = "状态")
    private Integer zt;

    @Schema(description = "处理人 ID")
    private Long clrId;

    @Schema(description = "处理人姓名")
    private String clrmc;

    @Schema(description = "处理时间")
    private LocalDateTime clsj;

    @Schema(description = "处理意见")
    private String clsm;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "附件列表")
    private List<FjItem> fjList;

    @Data
    public static class FjItem {
        private String wjlj;
        private String wjmc;
        private String ywjmc;
    }
}
