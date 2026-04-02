package cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.xwxe.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 小微小额 Response VO")
@Data
public class XwxeResVO {

    @Schema(description = "登记序号")
    private String djxh;

    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人名称")
    private String nsrmc;

    @Schema(description = "纳税人简称")
    private String nsrjc;

    @Schema(description = "联系电话")
    private String lxdh;

    @Schema(description = "基层工会名称")
    private String jcghmc;

    @Schema(description = "小额缴费标志")
    private String hjfl6Dm;

    @Schema(description = "小微上报时间")
    private LocalDateTime hjfl7Dm;

    @Schema(description = "是否已建会缴纳筹备金")
    private String hjfl8Dm;

    @Schema(description = "小微企业标志")
    private String hjfl9Dm;

    @Schema(description = "成立工会标志")
    private String clghbj;

    @Schema(description = "成立工会日期")
    private LocalDateTime clghrq;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
