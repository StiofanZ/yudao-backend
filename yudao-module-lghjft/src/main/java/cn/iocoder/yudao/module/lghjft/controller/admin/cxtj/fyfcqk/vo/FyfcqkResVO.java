package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fyfcqk.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 分月分成情况 Response VO")
@Data
@ExcelIgnoreUnannotated
public class FyfcqkResVO {

    @Schema(description = "deptId")
    private String deptId;
    @Schema(description = "rkrq")
    private LocalDateTime rkrq;
    @Schema(description = "dsyf")
    private String dsyf;
    @Schema(description = "rkjf")
    private String rkjf;
    @Schema(description = "tkjf")
    private String tkjf;
    @Schema(description = "ygfcjf")
    private String ygfcjf;
    @Schema(description = "yfcjf")
    private String yfcjf;
    @Schema(description = "wfcjf")
    private String wfcjf;
    @Schema(description = "szfc")
    private String szfc;
    @Schema(description = "znj")
    private String znj;
    @Schema(description = "qzfc")
    private String qzfc;
    @Schema(description = "hj")
    private String hj;
}
