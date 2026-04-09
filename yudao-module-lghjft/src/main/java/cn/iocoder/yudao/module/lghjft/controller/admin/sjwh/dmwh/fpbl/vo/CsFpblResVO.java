package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.fpbl.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 分配比例 Response VO")
@Data
public class CsFpblResVO {

    @Schema(description = "blId")
    private Long blId;
    @Schema(description = "bluuid")
    private String bluuid;
    @Schema(description = "lx")
    @ExcelProperty("类型")
    private String lx;
    @Schema(description = "ms")
    @ExcelProperty("描述")
    private String ms;
    @Schema(description = "yxqq")
    @ExcelProperty("有效期起")
    private LocalDateTime yxqq;
    @Schema(description = "yxqz")
    @ExcelProperty("有效期止")
    private LocalDateTime yxqz;
    @Schema(description = "xybz")
    @ExcelProperty("有效标志")
    private String xybz;
    @Schema(description = "jcghbl")
    @ExcelProperty("基层工会比例")
    private BigDecimal jcghbl;
    @Schema(description = "hyghbl")
    @ExcelProperty("行业工会比例")
    private BigDecimal hyghbl;
    @Schema(description = "sdghbl")
    @ExcelProperty("属地工会比例")
    private BigDecimal sdghbl;
    @Schema(description = "xjghbl")
    @ExcelProperty("县级工会比例")
    private BigDecimal xjghbl;
    @Schema(description = "sjghbl")
    @ExcelProperty("市级工会比例")
    private BigDecimal sjghbl;
    @Schema(description = "szghbl")
    @ExcelProperty("省总工会比例")
    private BigDecimal szghbl;
    @Schema(description = "qgzghbl")
    @ExcelProperty("全总工会比例")
    private BigDecimal qgzghbl;
    @Schema(description = "sjcjbl")
    @ExcelProperty("省稽查局比例")
    private BigDecimal sjcjbl;
    @Schema(description = "sdsjbl")
    @ExcelProperty("省税局比例")
    private BigDecimal sdsjbl;
    @Schema(description = "swjgbl")
    @ExcelProperty("主管税务机关比例")
    private BigDecimal swjgbl;
    @Schema(description = "tj")
    @ExcelProperty("条件")
    private String tj;
    @Schema(description = "yxj")
    @ExcelProperty("优先级")
    private Long yxj;
    @Schema(description = "mrbz")
    @ExcelProperty("默认标志")
    private String mrbz;
    @Schema(description = "jym")
    @ExcelProperty("校验码")
    private String jym;
}
