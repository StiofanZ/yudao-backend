package cn.iocoder.yudao.module.lghjft.controller.app.bbsj.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Schema(description = "报表数据 Response VO")
@Data
public class BbsjResVO {

    @Schema(description = "报表编码")
    private String bbbm;

    @Schema(description = "报表名称")
    private String bbmc;

    @Schema(description = "查询参数")
    private Map<String, Object> cxcs = new LinkedHashMap<>();

    @Schema(description = "报表数据，保持积木报表原始结构")
    private Map<String, Object> sj = new LinkedHashMap<>();

    @Schema(description = "快照批次编号")
    private String pcbh;

    @Schema(description = "业务日期")
    private LocalDate ywrq;

    @Schema(description = "快照生成时间")
    private LocalDateTime scsj;
}
