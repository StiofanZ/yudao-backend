package cn.iocoder.yudao.module.lghjft.controller.admin.qx.dwxxsp.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 单位信息审批 Response VO")
@Data
public class DwxxspRespVO {

    @Schema(description = "业务类型")
    private String businessType;

    @Schema(description = "业务 ID")
    private Long businessId;

    @Schema(description = "业务编号")
    private String applyNo;

    @Schema(description = "申请人")
    private String applicantName;

    @Schema(description = "联系电话")
    private String applicantPhone;

    @Schema(description = "单位名称")
    private String dwmc;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "摘要")
    private String summary;

    @Schema(description = "部门 ID")
    private Long deptId;

    @Schema(description = "部门名称")
    private String deptName;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
