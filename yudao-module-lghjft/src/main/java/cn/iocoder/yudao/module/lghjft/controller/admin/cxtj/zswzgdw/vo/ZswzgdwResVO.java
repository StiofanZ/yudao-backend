package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zswzgdw.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 征收未主管单位 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ZswzgdwResVO {

    @Schema(description = "id")
    private String id;
    @Schema(description = "dwdm")
    private String dwdm;
    @Schema(description = "deptId")
    private String deptId;
    @Schema(description = "zgswjDm")
    private String zgswjDm;
    @Schema(description = "shxydm")
    private String shxydm;
    @Schema(description = "nsrmc")
    private String nsrmc;
    @Schema(description = "djxh")
    private String djxh;
    @Schema(description = "bs")
    private Long bs;
    @Schema(description = "jfje")
    private BigDecimal jfje;
    @Schema(description = "jcghje")
    private BigDecimal jcghje;
    @Schema(description = "qrjgDm")
    private String qrjgDm;
}
