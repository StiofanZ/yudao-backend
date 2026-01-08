package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.hjfl.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Schema(description = "管理后台 - 户籍分类新增/修改 Request VO")
@Data
public class HjflSaveReqVO {

    @Schema(description = "工会机构代码", requiredMode = Schema.RequiredMode.REQUIRED, example = "76")
    @NotEmpty(message = "工会机构代码不能为空")
    @Length(max = 6, message = "工会机构代码长度不能超过6个字符") // 对应数据库 dept_id char(6)
    private String deptId;

    @Schema(description = "备注", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "备注不能为空")
    @Length(max = 1, message = "备注长度不能超过1个字符") // 对应数据库 BZ char(1)
    private String bz;

    @Schema(description = "户籍分类代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "户籍分类代码不能为空")
    @Length(max = 4, message = "户籍分类代码长度不能超过4个字符") // 对应数据库 HJFL_DM varchar(4)
    private String hjflDm;

    @Schema(description = "户籍分类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "户籍分类名称不能为空")
    @Length(max = 40, message = "户籍分类名称长度不能超过40个字符") // 对应数据库 HJFLMC varchar(40)
    private String hjflmc;

    @Schema(description = "顺序号")
    @Digits(integer = 4, fraction = 0, message = "顺序号只能是整数，且长度不超过4位") // 对应数据库 SXH decimal(4,0)
    private Short sxh; // Short 类型天然适配 decimal(4,0)，无需额外长度限制（Short 最大值 32767，覆盖4位整数）
    @Schema(description = "户籍分类id", example = "27518")
    private Integer hjflid; // 数据库 int 类型，无需长度校验（Integer 覆盖 int 范围）
}