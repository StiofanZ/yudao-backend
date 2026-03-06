package cn.iocoder.yudao.module.lghjft.controller.admin.qx.zhwh.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 账户维护申请 Response VO")
@Data
public class ZhwhRespVO {

    @Schema(description = "编号")
    private Long id;

    @Schema(description = "申请编号")
    private String applyNo;

    @Schema(description = "登录账号 ID")
    private Long dlzhId;

    @Schema(description = "申请人姓名")
    private String applicantName;

    @Schema(description = "申请人手机号")
    private String applicantPhone;

    @Schema(description = "部门 ID")
    private Long deptId;

    @Schema(description = "部门名称")
    private String deptName;

    @Schema(description = "登记序号")
    private String djxh;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "单位名称")
    private String dwmc;

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

    @Schema(description = "申请原因")
    private String applyReason;

    @Schema(description = "材料附件 URL 列表")
    private List<String> materials;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "审核意见")
    private String auditRemark;

    @Schema(description = "审核人 ID")
    private Long auditUserId;

    @Schema(description = "审核人")
    private String auditUserName;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "同步状态")
    private Integer syncStatus;

    @Schema(description = "同步说明")
    private String syncMessage;

    @Schema(description = "同步时间")
    private LocalDateTime syncTime;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
