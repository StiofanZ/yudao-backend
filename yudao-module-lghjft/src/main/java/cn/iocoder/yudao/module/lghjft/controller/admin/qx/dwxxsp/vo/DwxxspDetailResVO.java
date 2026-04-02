package cn.iocoder.yudao.module.lghjft.controller.admin.qx.dwxxsp.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 单位信息审批详情 Response VO")
@Data
public class DwxxspDetailResVO {

    @Schema(description = "业务类型")
    private String businessType;

    @Schema(description = "业务 ID")
    private Long businessId;

    @Schema(description = "业务编号")
    private String applyNo;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "申请人")
    private String applicantName;

    @Schema(description = "联系电话")
    private String applicantPhone;

    @Schema(description = "单位名称")
    private String dwmc;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "部门名称")
    private String deptName;

    @Schema(description = "申请时间")
    private LocalDateTime createTime;

    @Schema(description = "申请原因")
    private String applyReason;

    @Schema(description = "审核意见")
    private String auditRemark;

    @Schema(description = "旧基层工会账号")
    private String oldJcghzh;

    @Schema(description = "旧基层工会户名")
    private String oldJcghhm;

    @Schema(description = "旧基层工会行号")
    private String oldJcghhh;

    @Schema(description = "旧基层工会开户行")
    private String oldJcghyh;

    @Schema(description = "新基层工会账号")
    private String newJcghzh;

    @Schema(description = "新基层工会户名")
    private String newJcghhm;

    @Schema(description = "新基层工会行号")
    private String newJcghhh;

    @Schema(description = "新基层工会开户行")
    private String newJcghyh;

    @Schema(description = "材料附件")
    private List<String> materials;

    @Schema(description = "身份类型")
    private String sflx;

    @Schema(description = "工会类型")
    private String ghlx;

    @Schema(description = "权限类型")
    private String qxlx;

    @Schema(description = "拒绝原因")
    private String rejectReason;
}
