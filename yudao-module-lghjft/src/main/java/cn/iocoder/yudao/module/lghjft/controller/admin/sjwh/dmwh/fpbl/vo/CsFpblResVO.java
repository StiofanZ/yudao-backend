package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.fpbl.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 分配比例 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CsFpblResVO {

    @Schema(description = "blId")
    private Long blId;
    @Schema(description = "bluuid")
    private String bluuid;
    @Schema(description = "lx")
    private String lx;
    @Schema(description = "ms")
    private String ms;
    @Schema(description = "yxqq")
    private LocalDateTime yxqq;
    @Schema(description = "yxqz")
    private LocalDateTime yxqz;
    @Schema(description = "xybz")
    private String xybz;
    @Schema(description = "jcghbl")
    private BigDecimal jcghbl;
    @Schema(description = "hyghbl")
    private BigDecimal hyghbl;
    @Schema(description = "sdghbl")
    private BigDecimal sdghbl;
    @Schema(description = "xjghbl")
    private BigDecimal xjghbl;
    @Schema(description = "sjghbl")
    private BigDecimal sjghbl;
    @Schema(description = "szghbl")
    private BigDecimal szghbl;
    @Schema(description = "qgzghbl")
    private BigDecimal qgzghbl;
    @Schema(description = "sjcjbl")
    private BigDecimal sjcjbl;
    @Schema(description = "sdsjbl")
    private BigDecimal sdsjbl;
    @Schema(description = "swjgbl")
    private BigDecimal swjgbl;
    @Schema(description = "tj")
    private String tj;
    @Schema(description = "yxj")
    private Long yxj;
    @Schema(description = "mrbz")
    private String mrbz;
    @Schema(description = "jym")
    private String jym;
}
