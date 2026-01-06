package cn.iocoder.yudao.module.dm.controller.admin.swjg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 税务机关 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SwjgRespVO {

    @Schema(description = "税务机关代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("税务机关代码")
    private String swjgDm;

    @Schema(description = "税务机关名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("税务机关名称")
    private String swjgmc;

    @Schema(description = "税务机关简称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("税务机关简称")
    private String swjgjc;

    @Schema(description = "地址")
    @ExcelProperty("地址")
    private String dz;

    @Schema(description = "邮政编码")
    @ExcelProperty("邮政编码")
    private String yzbm;

    @Schema(description = "联系人")
    @ExcelProperty("联系人")
    private String lxr;

    @Schema(description = "联系电话")
    @ExcelProperty("联系电话")
    private String lxdh;

    @Schema(description = "手续费账号")
    @ExcelProperty("手续费账号")
    private String sxfzh;

    @Schema(description = "户名")
    @ExcelProperty("户名")
    private String sxfhm;

    @Schema(description = "行号")
    @ExcelProperty("行号")
    private String sxfhh;

    @Schema(description = "银行")
    @ExcelProperty("银行")
    private String sxfyh;

    @Schema(description = "上级税务机关代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("上级税务机关代码")
    private String sjswjgDm;

    @Schema(description = "稽查局标记", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("稽查局标记")
    private String jcjbj;

    @Schema(description = "工会机构代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("工会机构代码")
    private String ghjgDm;

    @Schema(description = "顺序号")
    @ExcelProperty("顺序号")
    private Integer sxh;

    @Schema(description = "校验码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("校验码")
    private String jym;

}