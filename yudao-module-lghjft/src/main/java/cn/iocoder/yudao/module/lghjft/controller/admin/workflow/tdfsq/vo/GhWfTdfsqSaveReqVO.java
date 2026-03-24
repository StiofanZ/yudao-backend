package cn.iocoder.yudao.module.lghjft.controller.admin.workflow.tdfsq.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Schema(description = "管理后台 - 退抵费申请新增/修改 Request VO")
public class GhWfTdfsqSaveReqVO {

    @Schema(description = "主键ID", example = "1")
    private Long id;

    @Schema(description = "社会信用代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "社会信用代码不能为空")
    private String shxydm;

    @Schema(description = "单位名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "单位名称不能为空")
    private String dwmc;

    @Schema(description = "情况说明", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "情况说明不能为空")
    private String qksm;

    @Schema(description = "单位负责人")
    private String dwfzr;

    @Schema(description = "经办人")
    private String jbr;

    @Schema(description = "联系电话")
    private String lxdh;

    @Schema(description = "户名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "户名不能为空")
    private String hm;

    @Schema(description = "开户行名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "开户行名称不能为空")
    private String khyhmc;

    @Schema(description = "账号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "账号不能为空")
    private String zh;

    @Schema(description = "开户行行号")
    private String khyhhh;

    @Schema(description = "申请日期")
    private LocalDate sqrq;

    @Schema(description = "附件列表")
    @NotEmpty(message = "附件列表不能为空")
    private List<FjItem> fjList;

    @Data
    @Schema(description = "附件项")
    public static class FjItem {
        @Schema(description = "附件类型", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "附件类型不能为空")
        private String fjlx;

        @Schema(description = "文件路径", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "文件路径不能为空")
        private String wjlj;

        @Schema(description = "文件名")
        private String wjmc;

        @Schema(description = "原始文件名")
        private String ywjmc;
    }


    // ====================== 新增：退费类型（1-当期 2-往期） ======================
    @Schema(description = "退费类型 1-当期 2-往期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "退费类型不能为空")
    private Integer sqtflxDm;

    // ====================== 新增：退费明细列表 ======================
    @Schema(description = "退费明细列表")
    private List<TdfsqMxItem> mxList;

    // ====================== 新增：退费明细项 ======================
    @Data
    @Schema(description = "退费明细项")
    public static class TdfsqMxItem {
        private String spuuid;
        private BigDecimal rkje;
        private BigDecimal tfsqJe;
        private String shxydm;
        private String nsrmc;
        private LocalDate skssqq;
        private LocalDate skssqz;
    }
}
