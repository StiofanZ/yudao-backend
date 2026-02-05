package cn.iocoder.yudao.module.lghjft.controller.admin.wftdfsq.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
public class WfTdfSqSaveReqVO {

    @Schema(description = "社会信用代码", requiredMode = Schema.RequiredMode.REQUIRED, example = "91370200MA3TGYQY5R")
    @NotEmpty(message = "社会信用代码不能为空")
    private String shxydm;

    @Schema(description = "纳税人名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "纳税人名称不能为空")
    private String nsrmc;

    @Schema(description = "情况说明", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "情况说明不能为空")
    private String situationDesc;

    @Schema(description = "单位负责人")
    private String unitLeader;

    @Schema(description = "经办人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "经办人不能为空")
    private String handler;

    @Schema(description = "联系电话")
    private String contactPhone;

    // 账户信息
    @Schema(description = "户名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "户名不能为空")
    private String accountName;

    @Schema(description = "开户行名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "开户行名称不能为空")
    private String bankName;

    @Schema(description = "账号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "账号不能为空")
    private String accountNo;

    @Schema(description = "开户行行号")
    private String bankCode;

    // 附件（前端传 fileId 列表）

    @Schema(description = "附件列表")
    private List<AttachmentItem> attachments;

    @Data
    public static class AttachmentItem {
        @NotBlank(message = "文件URL不能为空")
        private String fileUrl;

        @NotEmpty(message = "文件类型不能为空")
        private String type; // "voucher", "payroll", "license"
    }
}