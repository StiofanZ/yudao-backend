package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqdssj.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * V1: selecList result — from gh_qsjshkrj LEFT JOIN sys_user
 */
@Schema(description = "管理后台 - 读取代收数据 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DqdssjResVO {

    @Schema(description = "类型")
    private String lx;

    @Schema(description = "入库日期")
    @ExcelProperty("入库日期")
    private Date rkrq;

    @Schema(description = "入库金额")
    @ExcelProperty("入库金额")
    private BigDecimal je;

    @Schema(description = "结算日期")
    private Date jsrq;

    @Schema(description = "划款日期")
    @ExcelProperty("划款日期")
    private Date hkrq;

    @Schema(description = "批次号")
    @ExcelProperty("批次号")
    private String pch;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private Date createTime;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String createBy;
}
