package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ghjfjfdw.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 近三年缴费情况 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GhjfjfdwResVO {

    @Schema(description = "djxh")
    private String djxh;
    @Schema(description = "shxydm")
    private String shxydm;
    @Schema(description = "nsrmc")
    private String nsrmc;
    @Schema(description = "deptId")
    private String deptId;
    @Schema(description = "zsswjgDm")
    private String zsswjgDm;
    @Schema(description = "djxhbn")
    private String djxhbn;
    @Schema(description = "bsbn")
    private Long bsbn;
    @Schema(description = "ysbn")
    private Long ysbn;
    @Schema(description = "jfjebn")
    private BigDecimal jfjebn;
    @Schema(description = "djxhsn")
    private String djxhsn;
    @Schema(description = "bssn")
    private Long bssn;
    @Schema(description = "yssn")
    private Long yssn;
    @Schema(description = "jfjesn")
    private BigDecimal jfjesn;
    @Schema(description = "djxhqn")
    private String djxhqn;
    @Schema(description = "bsqn")
    private Long bsqn;
    @Schema(description = "ysqn")
    private Long ysqn;
    @Schema(description = "jfjeqn")
    private BigDecimal jfjeqn;
}
