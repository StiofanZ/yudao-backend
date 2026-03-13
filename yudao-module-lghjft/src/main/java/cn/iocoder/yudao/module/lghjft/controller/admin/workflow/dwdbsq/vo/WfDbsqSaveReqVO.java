package cn.iocoder.yudao.module.lghjft.controller.admin.workflow.dwdbsq.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 工会隶属关系调拨申请新增/修改 Request VO")
@Data
public class WfDbsqSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11810")
    private Long id;

    @Schema(description = "社会信用代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "社会信用代码不能为空")
    private String shxydm;

    @Schema(description = "单位名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "单位名称不能为空")
    private String dwmc;

    @Schema(description = "申请日期", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate sqrq;

    @Schema(description = "原主管工会名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "原主管工会名称不能为空")
    private String yzghmc;

    @Schema(description = "现主管工会名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "现主管工会名称不能为空")
    private String xzgghmc;

    @Schema(description = "调拨原因")
    private String dbyy;

    @Schema(description = "联系电话")
    private  String lxdh;

    @Schema(description = "原主管工会意见（调拨相关）")
    private String yzghyj;

    @Schema(description = "原主管工会负责人")
    private String yzghfzr;

    @Schema(description = "原主管工会经办人")
    private String yzghjbr;

    @Schema(description = "原主管工会日期")
    private LocalDate yzghgzrq;

    @Schema(description = "现主管工会审核意见（调拨相关）")
    private String xzgghspyj;

    @Schema(description = "现主管工会负责人")
    private String xzgghfzr;

    @Schema(description = "现主管工会经办人")
    private String xzgghjbr;

    @Schema(description = "现主管工会审核日期")
    private LocalDate xzgghsprq;

    @Schema(description = "省总工会审核意见（调拨相关）")
    private String sghspyj;

    @Schema(description = "省总工会负责人")
    private String sghfzr;

    @Schema(description = "省总工会经办人")
    private String sghjbr;

    @Schema(description = "省总工会审核日期")
    private LocalDate sghsprq;

    @Schema(description = "流程实例ID（调拨审批流程）", example = "3154")
    private String lcslId;

    @Schema(description = "附件列表")
    private List<FjItem> fjList;

    @Schema(description = "原来的部门id")
    private long oldDeptId ;
    @Schema(description = "现在的部门id")
    private long newDeptId ;
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