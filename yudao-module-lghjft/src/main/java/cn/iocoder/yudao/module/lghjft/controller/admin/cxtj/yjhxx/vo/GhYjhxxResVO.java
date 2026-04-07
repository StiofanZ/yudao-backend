package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.yjhxx.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 已建会信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GhYjhxxResVO {

    @Schema(description = "建会信息ID")
    @ExcelProperty("建会信息ID")
    private Long jhxxId;

    @Schema(description = "纳税人名称")
    @ExcelProperty("纳税人名称")
    private String nsrmc;

    @Schema(description = "社会信用代码")
    @ExcelProperty("社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人识别号")
    @ExcelProperty("纳税人识别号")
    private String nsrsbh;

    @Schema(description = "有效标记")
    @ExcelProperty("有效标记")
    private String yxbj;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String createBy;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "修改人")
    @ExcelProperty("修改人")
    private String updateBy;

    @Schema(description = "修改时间")
    @ExcelProperty("修改时间")
    private LocalDateTime updateTime;
}
