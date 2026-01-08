package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.fpblcopy.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 分配比例分页 Request VO")
@Data
public class FpblCopyPageReqVO extends PageParam {

    @Schema(description = "比例ID", example = "6988")
    private String bluuid;

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

    @Schema(description = "条件")
    private String tj;

    @Schema(description = "优先级")
    private Integer yxj;

    @Schema(description = "默认标注")
    private String mrbz;

    @Schema(description = "校验码")
    private String jym;

}