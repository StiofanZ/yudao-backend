package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jhdwyds.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 应代收单位 Response VO")
@Data
@ExcelIgnoreUnannotated
public class JhdwydsRespVO {

    @Schema(description = "工会机构", requiredMode = Schema.RequiredMode.REQUIRED, example = "31326")
    @ExcelProperty("工会机构")
    private String deptId;

    @Schema(description = "社会信用代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("纳税人名称")
    private String nsrmc;

    @Schema(description = "工会法人统一社会信用代码")
    @ExcelProperty("工会法人统一社会信用代码")
    private String ghshxydm;

    @Schema(description = "工会组织名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("工会组织名称")
    private String ghmc;

    @Schema(description = "工会联系人")
    @ExcelProperty("工会联系人")
    private String ghlxr;

    @Schema(description = "工会联系电话")
    @ExcelProperty("工会联系电话")
    private String ghlxdh;

    @Schema(description = "成立工会标志（0 筹备建会 1 已建会）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("成立工会标志（0 筹备建会 1 已建会）")
    private String clghbj;

    @Schema(description = "成立工会日期（筹备金建会日期）")
    @ExcelProperty("成立工会日期（筹备金建会日期）")
    private String clghrq;

    @Schema(description = "工会状态代码")
    @ExcelProperty("工会状态代码")
    private String ghztDm;

}