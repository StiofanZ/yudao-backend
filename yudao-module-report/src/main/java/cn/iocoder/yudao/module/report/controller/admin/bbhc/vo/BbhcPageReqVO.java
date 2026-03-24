package cn.iocoder.yudao.module.report.controller.admin.bbhc.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Schema(description = "管理后台 - 报表快照分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class BbhcPageReqVO extends PageParam {

    @Schema(description = "报表编码")
    private String bbbm;

    @Schema(description = "报表名称")
    private String bbmc;

    @Schema(description = "执行类型")
    private String zxlx;

    @Schema(description = "业务日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ywrq;

    @Schema(description = "批次编号")
    private String pcbh;
}
