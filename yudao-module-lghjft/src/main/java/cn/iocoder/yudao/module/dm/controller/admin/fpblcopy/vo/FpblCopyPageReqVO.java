package cn.iocoder.yudao.module.dm.controller.admin.fpblcopy.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 分配比例分页 Request VO")
@Data
public class FpblCopyPageReqVO extends PageParam {

    @Schema(description = "类型")
    private String lx;

    @Schema(description = "描述")
    private String ms;

    @Schema(description = "有效期起")
    private LocalDateTime yxqq;

    @Schema(description = "有效期止")
    private LocalDateTime yxqz;

    @Schema(description = "有效标志")
    private String xybz;

    @Schema(description = "基层工会比例")
    private BigDecimal jcghbl;

    @Schema(description = "行业工会比例")
    private BigDecimal hyghbl;

    @Schema(description = "属地工会比例")
    private BigDecimal sdghbl;

    @Schema(description = "县级工会比例")
    private BigDecimal xjghbl;

    @Schema(description = "市级工会比例")
    private BigDecimal sjghbl;

    @Schema(description = "省总工会比例")
    private BigDecimal szghbl;

    @Schema(description = "全总工会比例")
    private BigDecimal qgzghbl;

    @Schema(description = "省稽查局比例")
    private BigDecimal sjcjbl;

    @Schema(description = "省税局比例")
    private BigDecimal sdsjbl;

    @Schema(description = "主管税务机关比例")
    private BigDecimal swjgbl;

    @Schema(description = "YXJ")
    private Integer yxj;

    @Schema(description = "比例id")
    private Integer blId;
}