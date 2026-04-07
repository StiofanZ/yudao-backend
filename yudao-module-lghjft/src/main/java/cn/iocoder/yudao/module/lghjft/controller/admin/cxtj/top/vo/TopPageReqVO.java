package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.top.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 缴费排行分页 Request VO")
@Data
public class TopPageReqVO extends PageParam {

    @Schema(description = "登记序号")
    private String djxh;
    @Schema(description = "社会信用代码")
    private String shxydm;
    @Schema(description = "纳税人名称")
    private String nsrmc;
    @Schema(description = "工会机构代码")
    private String deptId;
    @Schema(description = "主管工会")
    private String dwmc;
    @Schema(description = "当年缴费笔数")
    private Long bsdn;
    @Schema(description = "去年缴费笔数")
    private Long bswn;
    @Schema(description = "笔数差异")
    private Long bscy;
    @Schema(description = "当年缴费金额")
    private BigDecimal jfjedn;
    @Schema(description = "去年缴费金额")
    private BigDecimal jfjewn;
    @Schema(description = "缴费金额差异")
    private BigDecimal jfjecy;
    @Schema(description = "缴费金额增减率")
    private String jfbl;
    @Schema(description = "今年上缴金额")
    private BigDecimal sjjedn;
    @Schema(description = "去年上缴金额")
    private BigDecimal sjjewn;
    @Schema(description = "上缴金额差异")
    private BigDecimal sjjecy;
    @Schema(description = "上缴金额增减率")
    private String sjbl;
}
