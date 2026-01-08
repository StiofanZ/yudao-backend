package cn.iocoder.yudao.module.dm.controller.admin.fpblcopy.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 分配比例新增/修改 Request VO")
@Data
public class FpblCopySaveReqVO {

    @Schema(description = "比例ID", example = "14659")
    private Integer blId;

    @Schema(description = "比例UUID", example = "6988")
    @Size(max = 32, message = "比例UUID长度不能超过32个字符")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "比例UUID只能包含字母和数字")
    private String bluuid;

    @Schema(description = "类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "类型不能为空")
    private String lx;

    @Schema(description = "描述", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "描述不能为空")
    @Size(max = 65535, message = "描述长度不能超过65535个字符")
    private String ms;

    @Schema(description = "有效期起", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "有效期起不能为空")
    @FutureOrPresent(message = "有效期起不能是过去的时间")
    private LocalDateTime yxqq;

    @Schema(description = "有效期止", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "有效期止不能为空")
    @Future(message = "有效期止必须是未来的时间")
    private LocalDateTime yxqz;

    @Schema(description = "有效标志", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "有效标志不能为空")
    @Size(min = 1, max = 1, message = "有效标志长度必须为1个字符")
    @Pattern(regexp = "^[01YN]$", message = "有效标志只能是0、1、Y或N")
    private String xybz;

    @Schema(description = "基层工会比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "基层工会比例不能为空")
    @DecimalMin(value = "0.0000", message = "基层工会比例不能小于0")
    @DecimalMax(value = "1.0000", message = "基层工会比例不能大于1")
    @Digits(integer = 3, fraction = 4, message = "基层工会比例整数部分最多3位，小数部分最多4位")
    private BigDecimal jcghbl;

    @Schema(description = "行业工会比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "行业工会比例不能为空")
    @DecimalMin(value = "0.0000", message = "行业工会比例不能小于0")
    @DecimalMax(value = "1.0000", message = "行业工会比例不能大于1")
    @Digits(integer = 3, fraction = 4, message = "行业工会比例整数部分最多3位，小数部分最多4位")
    private BigDecimal hyghbl;

    @Schema(description = "属地工会比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "属地工会比例不能为空")
    @DecimalMin(value = "0.0000", message = "属地工会比例不能小于0")
    @DecimalMax(value = "1.0000", message = "属地工会比例不能大于1")
    @Digits(integer = 3, fraction = 4, message = "属地工会比例整数部分最多3位，小数部分最多4位")
    private BigDecimal sdghbl;

    @Schema(description = "县级工会比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "县级工会比例不能为空")
    @DecimalMin(value = "0.0000", message = "县级工会比例不能小于0")
    @DecimalMax(value = "1.0000", message = "县级工会比例不能大于1")
    @Digits(integer = 3, fraction = 4, message = "县级工会比例整数部分最多3位，小数部分最多4位")
    private BigDecimal xjghbl;

    @Schema(description = "市级工会比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "市级工会比例不能为空")
    @DecimalMin(value = "0.0000", message = "市级工会比例不能小于0")
    @DecimalMax(value = "1.0000", message = "市级工会比例不能大于1")
    @Digits(integer = 3, fraction = 4, message = "市级工会比例整数部分最多3位，小数部分最多4位")
    private BigDecimal sjghbl;

    @Schema(description = "省总工会比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "省总工会比例不能为空")
    @DecimalMin(value = "0.0000", message = "省总工会比例不能小于0")
    @DecimalMax(value = "1.0000", message = "省总工会比例不能大于1")
    @Digits(integer = 3, fraction = 4, message = "省总工会比例整数部分最多3位，小数部分最多4位")
    private BigDecimal szghbl;

    @Schema(description = "全总工会比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "全总工会比例不能为空")
    @DecimalMin(value = "0.0000", message = "全总工会比例不能小于0")
    @DecimalMax(value = "1.0000", message = "全总工会比例不能大于1")
    @Digits(integer = 3, fraction = 4, message = "全总工会比例整数部分最多3位，小数部分最多4位")
    private BigDecimal qgzghbl;

    @Schema(description = "省稽查局比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "省稽查局比例不能为空")
    @DecimalMin(value = "0.0000", message = "省稽查局比例不能小于0")
    @DecimalMax(value = "1.0000", message = "省稽查局比例不能大于1")
    @Digits(integer = 3, fraction = 4, message = "省稽查局比例整数部分最多3位，小数部分最多4位")
    private BigDecimal sjcjbl;

    @Schema(description = "省税局比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "省税局比例不能为空")
    @DecimalMin(value = "0.0000", message = "省税局比例不能小于0")
    @DecimalMax(value = "1.0000", message = "省税局比例不能大于1")
    @Digits(integer = 3, fraction = 4, message = "省税局比例整数部分最多3位，小数部分最多4位")
    private BigDecimal sdsjbl;

    @Schema(description = "主管税务机关比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "主管税务机关比例不能为空")
    @DecimalMin(value = "0.0000", message = "主管税务机关比例不能小于0")
    @DecimalMax(value = "1.0000", message = "主管税务机关比例不能大于1")
    @Digits(integer = 3, fraction = 4, message = "主管税务机关比例整数部分最多3位，小数部分最多4位")
    private BigDecimal swjgbl;

    @Schema(description = "TJ", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "TJ不能为空")
    @Size(max = 5000, message = "TJ长度不能超过5000个字符")
    private String tj;

    @Schema(description = "优先级")
    @Min(value = -99999, message = "优先级不能小于-99999")
    @Max(value = 99999, message = "优先级不能大于99999")
    @Digits(integer = 5, fraction = 0, message = "优先级必须是5位整数")
    private Integer yxj;

    @Schema(description = "默认标志", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "MRBZ不能为空")
    @Size(min = 1, max = 1, message = "默认标志长度必须为1个字符")
    @Pattern(regexp = "^[01YN]$", message = "默认标志只能是0、1、Y或N")
    private String mrbz;

    @Schema(description = "校验码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "JYM不能为空")
    @Size(min = 1, max = 100, message = "校验码长度必须在1-100个字符之间")
    private String jym;

    // 新增：自定义校验方法（需要在Controller层或Service层调用）
    public void validateBusinessRules() {
        // 1. 校验比例总和不超过100%（如果需要）
//        validateProportionSum();

        // 2. 校验有效期逻辑：结束时间必须大于开始时间
        if (yxqz != null && yxqq != null && !yxqz.isAfter(yxqq)) {
            throw new IllegalArgumentException("有效期止必须大于有效期起");
        }

        // 3. 校验标志字段的逻辑一致性（根据业务需求）
        validateFlagsConsistency();
    }



    private void validateFlagsConsistency() {
        // 根据业务逻辑校验标志字段
        // 例如：如果XYBZ为N，某些比例可能应该为0等
    }

    // 分组校验接口（用于区分新增和更新操作）
    public interface CreateGroup {}
    public interface UpdateGroup {}
}