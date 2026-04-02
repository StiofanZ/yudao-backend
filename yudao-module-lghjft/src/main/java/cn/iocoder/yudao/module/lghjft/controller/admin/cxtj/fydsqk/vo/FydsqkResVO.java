package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fydsqk.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 分月代收情况 Response VO")
@Data
@ExcelIgnoreUnannotated
public class FydsqkResVO {

    @Schema(description = "deptId")
    private String deptId;
    @Schema(description = "rkrq")
    private LocalDateTime rkrq;
    @Schema(description = "dsyf")
    private String dsyf;
    @Schema(description = "rkjf")
    private String rkjf;
    @Schema(description = "ghjf")
    private String ghjf;
    @Schema(description = "cbj")
    private String cbj;
    @Schema(description = "znj")
    private String znj;
}
