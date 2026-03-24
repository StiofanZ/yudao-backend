package cn.iocoder.yudao.module.report.controller.admin.bbhc.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Schema(description = "管理后台 - 报表快照 Response VO")
@Data
public class BbhcRespVO {

    @Schema(description = "编号")
    private Long id;

    @Schema(description = "当前快照编号")
    private Long hcId;

    @Schema(description = "报表 ID")
    private String bbid;

    @Schema(description = "报表编码")
    private String bbbm;

    @Schema(description = "报表名称")
    private String bbmc;

    @Schema(description = "执行类型")
    private String zxlx;

    @Schema(description = "业务日期")
    private LocalDate ywrq;

    @Schema(description = "批次编号")
    private String pcbh;

    @Schema(description = "报表更新标识")
    private String bbgxbs;

    @Schema(description = "参数摘要")
    private String cszy;

    @Schema(description = "查询参数")
    private Map<String, Object> cxcs = new LinkedHashMap<>();

    @Schema(description = "结果 JSON")
    private String jgJson;

    @Schema(description = "生成时间")
    private LocalDateTime scsj;

    @Schema(description = "生成人")
    private String scr;

    @Schema(description = "归档时间")
    private LocalDateTime gdsj;
}
