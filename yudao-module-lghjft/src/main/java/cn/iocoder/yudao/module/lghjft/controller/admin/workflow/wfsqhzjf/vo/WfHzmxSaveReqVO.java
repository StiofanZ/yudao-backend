package cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfsqhzjf.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 工会经费汇总缴纳申请明细表（分支机构）新增/修改 Request VO")
@Data
public class WfHzmxSaveReqVO {
    @Schema(description = "主键ID，新增时不传，修改时传", example = "20160")
    private Long id;

    // 注意：新增时前端不用传hzId，后端绑定主表ID后填充
    @Schema(description = "关联汇总表主键ID（外键），新增时无需传", hidden = true)
    private Long hzId;

    @Schema(description = "分支机构社会信用代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分支机构社会信用代码不能为空")
    private String fjgxyxdm;

    @Schema(description = "分支机构单位全称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分支机构单位全称不能为空")
    private String fjgdwqc;

    @Schema(description = "分支机构主管税务部门")
    private String fjgzgsbm;

    @Schema(description = "分支机构职工人数")
    private Integer fjggzs;

    @Schema(description = "分支机构月工资总额")
    private BigDecimal fjggzze;

    // 创建/更新时间前端不传，后端自动填充，隐藏掉
//    @Schema(hidden = true)
//    private LocalDateTime cjsj;
//    @Schema(hidden = true)
//    private LocalDateTime gxsj;
}