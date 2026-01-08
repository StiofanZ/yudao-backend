package cn.iocoder.yudao.module.dm.controller.admin.fpblcopy.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 分配比例 Response VO")
@Data
@ExcelIgnoreUnannotated
public class FpblCopyRespVO {

    @Schema(description = "比例ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14659")
    @ExcelProperty("比例ID")
    private Integer blId;

    @Schema(description = "比例ID", example = "6988")
    @ExcelProperty("比例ID")
    private String bluuid;

    @Schema(description = "类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty(value = "类型", converter = DictConvert.class)
    @DictFormat("sys_blhf_gh") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private String lx;

    @Schema(description = "描述", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("描述")
    private String ms;

    @Schema(description = "有效期起", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("有效期起")
    private LocalDateTime yxqq;

    @Schema(description = "有效期止", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("有效期止")
    private LocalDateTime yxqz;

    @Schema(description = "有效标志", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty(value = "有效标志", converter = DictConvert.class)
    @DictFormat("sys_blhf_gh") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private String xybz;

    @Schema(description = "基层工会比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("基层工会比例")
    private BigDecimal jcghbl;

    @Schema(description = "行业工会比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行业工会比例")
    private BigDecimal hyghbl;

    @Schema(description = "属地工会比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("属地工会比例")
    private BigDecimal sdghbl;

    @Schema(description = "县级工会比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("县级工会比例")
    private BigDecimal xjghbl;

    @Schema(description = "市级工会比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("市级工会比例")
    private BigDecimal sjghbl;

    @Schema(description = "省总工会比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("省总工会比例")
    private BigDecimal szghbl;

    @Schema(description = "全总工会比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("全总工会比例")
    private BigDecimal qgzghbl;

    @Schema(description = "省稽查局比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("省稽查局比例")
    private BigDecimal sjcjbl;

    @Schema(description = "省税局比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("省税局比例")
    private BigDecimal sdsjbl;

    @Schema(description = "主管税务机关比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主管税务机关比例")
    private BigDecimal swjgbl;

    @Schema(description = "TJ", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("TJ")
    private String tj;

    @Schema(description = "YXJ")
    @ExcelProperty("YXJ")
    private Integer yxj;

    @Schema(description = "MRBZ", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("MRBZ")
    private String mrbz;

    @Schema(description = "JYM", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("JYM")
    private String jym;

}