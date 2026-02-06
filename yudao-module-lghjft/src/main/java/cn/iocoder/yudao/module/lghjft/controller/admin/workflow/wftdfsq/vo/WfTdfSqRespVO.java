// WfTdfsqRespVO.java
package cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wftdfsq.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

//申请请求
@Data
public class WfTdfSqRespVO {
    @Schema(description = "ID")
    private Long id;

    // 申请信息
    private String shxydm;
    private String nsrmc;
    private String situationDesc;
    private String unitLeader;
    private String handler;
    private String contactPhone;
    private String accountName;
    private String bankName;
    private String accountNo;
    private String bankCode;
    private java.time.LocalDate applyDate;

    // ========== 附件 - 扁平化字段（关键！） ==========
    @Schema(description = "凭证文件 URL")
    private String voucherUrl;
    @Schema(description = "工资表文件 URL")
    private String payrollUrl;
    @Schema(description = "开户许可证文件 URL")
    private String licenseUrl;


    // 主管工会审批
    private String managerOpinion;
    private String managerLeaderName;
    private String managerHandlerName;
    private LocalDate managerApproveTime;
    // 省总工会审批
    private String provinceOpinion;
    private String provinceLeaderName;
    private String provinceHandlerName;
    private LocalDate provinceApproveTime;
    @Schema(description = "退款方式")
    private Integer refundMethod; // 1-差额退库 2-全额退库 3-抵扣欠费 4-抵扣下期应缴费

}