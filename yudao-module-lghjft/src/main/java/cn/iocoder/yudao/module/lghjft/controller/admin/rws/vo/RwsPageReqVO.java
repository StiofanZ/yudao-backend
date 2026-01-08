package cn.iocoder.yudao.module.lghjft.controller.admin.rws.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 年度任务分页 Request VO")
@Data
public class RwsPageReqVO extends PageParam {

    @Schema(description = "任务类型	")
    private String rwlx;

    @Schema(description = "年度")
    private String nd;

    @Schema(description = "单位代码")
    private String dwdm;

    @Schema(description = "单位名称")
    private String dwmc;

    @Schema(description = "任务数")
    private BigDecimal rws;

    @Schema(description = "完成数")
    private String wcs;

}