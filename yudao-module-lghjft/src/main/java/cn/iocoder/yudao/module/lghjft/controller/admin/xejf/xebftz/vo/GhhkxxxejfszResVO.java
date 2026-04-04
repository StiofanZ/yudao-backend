package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 小额拨付省总记账凭证 Response VO (v1: selectGhHkxxxejfszList, 表 xebfsz)
 */
@Schema(description = "管理后台 - 小额拨付省总 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GhhkxxxejfszResVO {

    @Schema(description = "单位代码")
    private String deptId;

    @Schema(description = "单位名称")
    @ExcelProperty("单位名称")
    private String dwmc;

    @Schema(description = "缴费笔数")
    private String jfbs;

    @Schema(description = "缴费金额")
    private String jfje;

    @Schema(description = "省总金额")
    @ExcelProperty("省总金额")
    private String szje;

    @Schema(description = "全总金额")
    @ExcelProperty("全总金额")
    private String qzje;

    @Schema(description = "合计金额")
    @ExcelProperty("合计金额")
    private String hjje;

    @Schema(description = "划款批次号")
    private String hkpch;
}
