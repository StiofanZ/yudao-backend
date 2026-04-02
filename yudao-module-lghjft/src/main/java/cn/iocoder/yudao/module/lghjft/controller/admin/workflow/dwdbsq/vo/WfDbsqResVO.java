package cn.iocoder.yudao.module.lghjft.controller.admin.workflow.dwdbsq.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 工会隶属关系调拨申请 Response VO")
@Data
@ExcelIgnoreUnannotated
public class WfDbsqResVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11810")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "社会信用代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("社会信用代码")
    private String shxydm;

    @Schema(description = "单位名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("单位名称")
    private String dwmc;

    @Schema(description = "申请日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("申请日期")
    private LocalDate sqrq;

    @Schema(description = "原主管工会名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("原主管工会名称")
    private String yzghmc;

    @Schema(description = "现主管工会名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("现主管工会名称")
    private String xzgghmc;

    @Schema(description = "调拨原因")
    @ExcelProperty("调拨原因")
    private String dbyy;

    @Schema(description = "联系电话")
    @ExcelProperty("联系电话")
    private String lxdh;

    @Schema(description = "原主管工会意见（调拨相关）")
    @ExcelProperty("原主管工会意见（调拨相关）")
    private String yzghyj;

    @Schema(description = "原主管工会负责人")
    @ExcelProperty("原主管工会负责人")
    private String yzghfzr;

    @Schema(description = "原主管工会经办人")
    @ExcelProperty("原主管工会经办人")
    private String yzghjbr;

    @Schema(description = "原主管工会日期")
    @ExcelProperty("原主管工会日期")
    private LocalDate yzghgzrq;

    @Schema(description = "现主管工会审核意见（调拨相关）")
    @ExcelProperty("现主管工会审核意见（调拨相关）")
    private String xzgghspyj;

    @Schema(description = "现主管工会负责人")
    @ExcelProperty("现主管工会负责人")
    private String xzgghfzr;

    @Schema(description = "现主管工会经办人")
    @ExcelProperty("现主管工会经办人")
    private String xzgghjbr;

    @Schema(description = "现主管工会审核日期")
    @ExcelProperty("现主管工会审核日期")
    private LocalDate xzgghsprq;

    @Schema(description = "省总工会审核意见（调拨相关）")
    @ExcelProperty("省总工会审核意见（调拨相关）")
    private String sghspyj;

    @Schema(description = "省总工会负责人")
    @ExcelProperty("省总工会负责人")
    private String sghfzr;

    @Schema(description = "省总工会经办人")
    @ExcelProperty("省总工会经办人")
    private String sghjbr;

    @Schema(description = "省总工会审核日期")
    @ExcelProperty("省总工会审核日期")
    private LocalDate sghsprq;

    @Schema(description = "流程实例ID（调拨审批流程）", example = "3154")
    @ExcelProperty("流程实例ID（调拨审批流程）")
    private String lcslId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;
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

        private String ywjmc;

    }
}