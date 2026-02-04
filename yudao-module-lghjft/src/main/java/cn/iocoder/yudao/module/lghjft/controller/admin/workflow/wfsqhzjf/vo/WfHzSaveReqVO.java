package cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfsqhzjf.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "管理后台 - 工会经费汇总缴纳申请表（主表）新增/修改 Request VO")
@Data
public class WfHzSaveReqVO {
    @Schema(description = "主键ID，新增时不传，修改时传", example = "26956")
    private Long id;

    @Schema(description = "申请汇总缴费单位社会信用代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "申请汇总缴费单位社会信用代码不能为空")
    private String xyxdm;

    @Schema(description = "申请汇总缴费单位全称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "申请汇总缴费单位全称不能为空")
    private String dwqc;

    @Schema(description = "主管税务部门")
    private String zgsbm;

    @Schema(description = "缴费单位地址")
    private String dwdz;

    @Schema(description = "工会法人登记证件号码")
    private String ghfrdjzjh;

    @Schema(description = "缴费单位工会全称")
    private String ghqc;

    @Schema(description = "职工总人数")
    private Integer zzgzs;

    @Schema(description = "工会会员数")
    private Integer ghyhs;

    @Schema(description = "工会负责人")
    private String ghfzr;

    @Schema(description = "联系电话")
    private String lxdh;

    @Schema(description = "工会账户账号")
    private String ghzhzh;

    @Schema(description = "开户银行名称")
    private String khyhmc;

    @Schema(description = "工会账户户名")
    private String ghzhhm;

    @Schema(description = "开户银行网点代码")
    private String khyhwdm;

    @Schema(description = "汇总申报缴纳原因")
    private String hzbsjygy;

    @Schema(description = "负责人姓名")
    private String fzrxm;

    @Schema(description = "经办人姓名")
    private String jbrxm;

    @Schema(description = "经办人联系电话")
    private String jbrdh;

    @Schema(description = "申请日期")
    private LocalDate sqrq;

    @Schema(description = "分支机构总数")
    private Integer fjgzs;

    @Schema(description = "主管工会审核意见")
    private String zgghsjy;

    @Schema(description = "主管工会审核负责人")
    private String zgghsfzr;

    @Schema(description = "主管工会审核经办人")
    private String zgghsjbr;

    @Schema(description = "主管工会审核经办人电话")
    private String zgghsjbrdh;

    @Schema(description = "主管工会审核日期")
    private LocalDate zgghsrq;

    @Schema(description = "省总工会审核意见")
    private String sghzsjy;

    @Schema(description = "省总工会审核负责人")
    private String sghsfzr;

    @Schema(description = "省总工会审核日期")
    private LocalDate sghsrq;

    // ========== 核心：嵌套分支机构明细表列表 ==========
    @Schema(description = "分支机构明细列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分支机构明细不能为空")
    private List<WfHzmxSaveReqVO> detailList;
}