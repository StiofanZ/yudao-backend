package cn.iocoder.yudao.module.lghjft.controller.admin.rws.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import cn.idev.excel.annotation.*;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 年度任务 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RwsRespVO {

    @Schema(description = "任务id", requiredMode = Schema.RequiredMode.REQUIRED, example = "9124")
    @ExcelProperty("任务id")
    private Integer rwid;

    @Schema(description = "任务类型	")
    @ExcelProperty(value = "任务类型	", converter = DictConvert.class)
    @DictFormat("sys_rwlx") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private String rwlx;

    @Schema(description = "年度")
    @ExcelProperty(value = "年度", converter = DictConvert.class)
    @DictFormat("sys_nd") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private String nd;

    @Schema(description = "单位代码")
    @ExcelProperty("单位代码")
    private String dwdm;

    @Schema(description = "单位名称")
    @ExcelProperty("单位名称")
    private String dwmc;

    @Schema(description = "任务数")
    @ExcelProperty("任务数")
    private BigDecimal rws;

    @Schema(description = "完成数")
    @ExcelProperty("完成数")
    private String wcs;

}