package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.czrj.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 历史日志 Response VO")
@Data
@ExcelIgnoreUnannotated
public class QxCzrjResVO {

    @Schema(description = "czrjid")
    private Long czrjid;
    @Schema(description = "czydm")
    private String czydm;
    @Schema(description = "czyxm")
    private String czyxm;
    @Schema(description = "czsj")
    private LocalDateTime czsj;
    @Schema(description = "czms")
    private String czms;
    @Schema(description = "cznr")
    private String cznr;
}
