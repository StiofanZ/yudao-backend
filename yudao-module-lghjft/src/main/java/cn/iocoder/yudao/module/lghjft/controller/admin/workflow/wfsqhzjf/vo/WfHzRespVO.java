package cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfsqhzjf.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


import java.time.LocalDate;
import java.util.List;

@Schema(description = "管理后台 - 工会经费汇总缴纳申请表（主表）返回 VO")
@Data
public class WfHzRespVO {
    @Schema(description = "主表主键ID", example = "26956")
    private Long id;

    @Schema(description = "申请汇总缴费单位社会信用代码", example = "91370200MA3TGYQY5R", requiredMode = Schema.RequiredMode.REQUIRED)
    private String xyxdm;

    @Schema(description = "申请汇总缴费单位全称", example = "XX省XX集团有限公司", requiredMode = Schema.RequiredMode.REQUIRED)
    private String dwqc;

    @Schema(description = "主管税务部门", example = "XX市税务局第一分局", requiredMode = Schema.RequiredMode.REQUIRED)
    private String zgsbm;

    @Schema(description = "缴费单位地址", example = "XX市XX区XX路100号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String dwdz;

    @Schema(description = "工会法人登记证件号码", example = "123456789012345678", requiredMode = Schema.RequiredMode.REQUIRED)
    private String ghfrdjzjh;

    @Schema(description = "缴费单位工会全称", example = "XX集团工会委员会", requiredMode = Schema.RequiredMode.REQUIRED)
    private String ghqc;

    @Schema(description = "职工总人数", example = "500", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer zzgzs;

    @Schema(description = "工会会员数", example = "450", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer ghyhs;

    @Schema(description = "工会负责人", example = "张三", requiredMode = Schema.RequiredMode.REQUIRED)
    private String ghfzr;

    @Schema(description = "联系电话", example = "13800138000", requiredMode = Schema.RequiredMode.REQUIRED)
    private String lxdh;

    @Schema(description = "工会账户", example = "6222081234567890", requiredMode = Schema.RequiredMode.REQUIRED)
    private String ghzhzh;

    @Schema(description = "开户银行名称", example = "中国工商银行XX支行", requiredMode = Schema.RequiredMode.REQUIRED)
    private String khyhmc;

    @Schema(description = "工会账户户名", example = "XX集团工会委员会", requiredMode = Schema.RequiredMode.REQUIRED)
    private String ghzhhm;

    @Schema(description = "开户银行网点代码", example = "102370000001", requiredMode = Schema.RequiredMode.REQUIRED)
    private String khyhwdm;

    @Schema(description = "汇总申报缴纳原因", example = "统一汇总缴纳工会经费，便于管理", requiredMode = Schema.RequiredMode.REQUIRED)
    private String hzbsjygy;

    @Schema(description = "负责人姓名", example = "李四")
    private String fzrxm;

    @Schema(description = "经办人姓名", example = "王五", requiredMode = Schema.RequiredMode.REQUIRED)
    private String jbrxm;

    @Schema(description = "经办人联系电话", example = "13900139000", requiredMode = Schema.RequiredMode.REQUIRED)
    private String jbrdh;

    @Schema(description = "申请日期", example = "2026-02-03")
    private LocalDate sqrq;

    @Schema(description = "分支机构总数", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer fjgzs;

    @Schema(description = "主管工会审核意见", example = "同意汇总缴纳", requiredMode = Schema.RequiredMode.REQUIRED)
    private String zgghsjy;

    @Schema(description = "主管工会审核负责人", example = "赵六")
    private String zgghsfzr;

    @Schema(description = "主管工会审核经办人", example = "孙七")
    private String zgghsjbr;

    @Schema(description = "主管工会审核经办人电话", example = "13700137000")
    private String zgghsjbrdh;

    @Schema(description = "主管工会审核日期", example = "2026-02-05")
    private LocalDate zgghsrq;

    @Schema(description = "省总工会审核意见", example = "同意")
    private String sghzsjy;

    @Schema(description = "省总工会审核负责人", example = "周八")
    private String sghsfzr;

    @Schema(description = "省总工会审核日期", example = "2026-02-10")
    private LocalDate sghsrq;

    // ========== 核心：嵌套分支机构明细表列表 ==========
    @Schema(description = "分支机构明细列表")
    private List<WfHzmxRespVO> detailList;
}