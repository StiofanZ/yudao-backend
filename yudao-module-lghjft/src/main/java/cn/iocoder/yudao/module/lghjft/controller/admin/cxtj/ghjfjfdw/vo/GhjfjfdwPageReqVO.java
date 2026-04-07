package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ghjfjfdw.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 近三年缴费情况分页 Request VO")
@Data
public class GhjfjfdwPageReqVO extends PageParam {

    @Schema(description = "登记序号")
    private String djxh;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人名称")
    private String nsrmc;

    @Schema(description = "工会机构")
    private String deptId;

    @Schema(description = "税务机关代码")
    private String zsswjgDm;

    @Schema(description = "登记序号本年")
    private String djxhbn;

    @Schema(description = "本年笔数")
    private Long bsbn;

    @Schema(description = "本年月数")
    private Long ysbn;

    @Schema(description = "本年金额")
    private BigDecimal jfjebn;

    @Schema(description = "登记序号上年")
    private String djxhsn;

    @Schema(description = "上年笔数")
    private Long bssn;

    @Schema(description = "上年月数")
    private Long yssn;

    @Schema(description = "上年金额")
    private BigDecimal jfjesn;

    @Schema(description = "登记序号前年")
    private String djxhqn;

    @Schema(description = "前年笔数")
    private Long bsqn;

    @Schema(description = "前年月数")
    private Long ysqn;

    @Schema(description = "前年金额")
    private BigDecimal jfjeqn;
}
