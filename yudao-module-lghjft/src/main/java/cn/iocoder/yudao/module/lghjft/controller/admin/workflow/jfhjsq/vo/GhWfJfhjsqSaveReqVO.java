package cn.iocoder.yudao.module.lghjft.controller.admin.workflow.jfhjsq.vo;

import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.dwdbsq.vo.WfDbsqRespVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Schema(description = "管理后台 - 经费缓缴申请新增/修改 Request VO")
@Data
public class GhWfJfhjsqSaveReqVO {

    @Schema(description = "主键ID", example = "1")
    private Long id;

    @Schema(description = "社会信用代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "社会信用代码不能为空")
    private String shxydm;

    @Schema(description = "单位名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "单位名称不能为空")
    private String dwmc;

    @Schema(description = "联系人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "联系人不能为空")
    private String lxr;

    @Schema(description = "联系电话", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "联系电话不能为空")
    private String lxdh;

    @Schema(description = "适用费率", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "适用费率不能为空")
    @DecimalMin(value = "0.01", message = "适用费率不能小于 0.01")
    private BigDecimal syfl;

    @Schema(description = "职工总数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "职工总数不能为空")
    @Min(value = 1, message = "职工总数不能小于 1")
    private Integer zgzs;

    @Schema(description = "月工资总额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "月工资总额不能为空")
    @DecimalMin(value = "0.01", message = "月工资总额不能小于 0.01")
    private BigDecimal ygzze;

    @Schema(description = "月拨缴金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "月拨缴金额不能为空")
    @DecimalMin(value = "0.00", message = "月拨缴金额不能为负数")
    private BigDecimal ybjje;

    @Schema(description = "缓缴开始年月", requiredMode = Schema.RequiredMode.REQUIRED, example = "2026-03")
    @NotBlank(message = "缓缴开始年月不能为空")
    private String hjkssj;

    @Schema(description = "缓缴结束年月", requiredMode = Schema.RequiredMode.REQUIRED, example = "2026-06")
    @NotBlank(message = "缓缴结束年月不能为空")
    private String hjjssj;

    @Schema(description = "缓缴月数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "缓缴月数不能为空")
    @Min(value = 1, message = "缓缴月数不能小于 1")
    @Max(value = 24, message = "缓缴月数不能大于 24")
    private Integer hjzys;

    @Schema(description = "累计缓缴金额")
    private BigDecimal ljhjje;

    @Schema(description = "缓缴情况说明", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "缓缴情况说明不能为空")
    private String hjqksm;

    @Schema(description = "单位负责人")
    private String dwfzr;

    @Schema(description = "经办人")
    private String jbr;

    @Schema(description = "申请日期")
    private LocalDate sqrq;

    @Schema(description = "基层工会意见")
    private String jcghyj;

    @Schema(description = "基层工会负责人")
    private String jcghfzr;

    @Schema(description = "基层工会经办人")
    private String jcghjbr;

    @Schema(description = "基层工会盖章日期")
    private LocalDate jcghgzrq;

    @Schema(description = "主管工会审核意见")
    private String zgghspyj;

    @Schema(description = "主管工会负责人")
    private String zgghfzr;

    @Schema(description = "主管工会经办人")
    private String zgghjbr;

    @Schema(description = "主管工会审核日期")
    private LocalDate zgghsprq;

    @Schema(description = "主管工会财务负责人")
    private String zgghcwfzr;

    @Schema(description = "流程实例ID")
    private String lcslId;
    @Schema(description = "附件列表")
    private List<FjItem> fjList;

    //    存放文件的子表
    @Data
    @Schema(description = "附件项")
    public static class FjItem {
        private String wjmc;
        /**
         * 文件访问地址
         */
        private String wjlj;
        /**
         * 文件类型（pdf/png/jpg/docx）
         */
        private String fjlx;

        private  String ywjmc;

    }
}
