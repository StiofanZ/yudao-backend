package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.schbwj.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 实查汇报无结分页 Request VO")
@Data
public class SchbwjPageReqVO extends PageParam {

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人名称")
    private String nsrmc;

    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "主管税务局代码")
    private String zgswjDm;

    @Schema(description = "结算标记")
    private String jsbj;

    @Schema(description = "征收品目代码")
    private String zspmDm;
    @Schema(description = "结算日期-起")
    private LocalDateTime jsrqStart;
    @Schema(description = "结算日期-止")
    private LocalDateTime jsrqEnd;

    private String hkpch;
    private String hkrq;
    private String beginHkpch;
    private String endHkpch;
    private String beginUpdateTime;
    private String endUpdateTime;
}
