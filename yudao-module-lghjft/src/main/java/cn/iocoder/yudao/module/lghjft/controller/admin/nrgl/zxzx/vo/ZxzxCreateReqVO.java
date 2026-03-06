package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zxzx.vo;

import cn.iocoder.yudao.framework.common.validation.Mobile;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Schema(description = "在线咨询新增 Request VO")
@Data
public class ZxzxCreateReqVO {

    @Schema(description = "平台名称")
    private String ptmc;

    @Schema(description = "咨询内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "咨询内容不能为空")
    private String nr;

    @Schema(description = "联系电话")
    @Mobile
    private String lxdh;

    @Schema(description = "联系邮箱")
    private String lxyx;

    @Schema(description = "附件列表")
    private List<FjItem> fjList;

    @Data
    public static class FjItem {
        private String wjlj;
        private String wjmc;
        private String ywjmc;
    }
}
