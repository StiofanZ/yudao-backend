package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.czrj.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 历史日志 Response VO")
@Data
public class QxCzrjResVO {

    @Schema(description = "czrjid")
    private Long czrjid;
    @Schema(description = "czydm")
    @ExcelProperty("操作员代码")
    private String czydm;
    @Schema(description = "czyxm")
    @ExcelProperty("操作员姓名")
    private String czyxm;
    @Schema(description = "czsj")
    @ExcelProperty("操作时间")
    private LocalDateTime czsj;
    @Schema(description = "czms")
    @ExcelProperty("操作模式")
    private String czms;
    @Schema(description = "cznr")
    @ExcelProperty("操作内容")
    private String cznr;
}
