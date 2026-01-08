package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.hjfl.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 户籍分类 Response VO")
@Data
@ExcelIgnoreUnannotated
public class HjflRespVO {

    @Schema(description = "工会机构代码", requiredMode = Schema.RequiredMode.REQUIRED, example = "76")
    @ExcelProperty("工会机构代码")
    private String deptId;

    @Schema(description = "大类标识", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty(value = "大类标识", converter = DictConvert.class)
    @DictFormat("sys_hjfl_gh") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private String bz;

    @Schema(description = "户籍分类代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("户籍分类代码")
    private String hjflDm;

    @Schema(description = "户籍分类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("户籍分类名称")
    private String hjflmc;

    @Schema(description = "顺序号")
    @ExcelProperty("顺序号")
    private Short sxh;

    @Schema(description = "户籍分类id", example = "27518")
    private Integer hjflid;


}