package cn.iocoder.yudao.module.lghjft.controller.admin.workflow.jfhzjnsq.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 工会经费汇总缴纳申请明细表（分支机构）新增/修改 Request VO")
@Data
public class GhWfJfhzjnsqmxSaveReqVO {
    @Schema(description = "主键ID，新增时不传，修改时传", example = "20160")
    private Long id;

    // 注意：新增时前端不用传jfhzjnsqId，后端绑定主表ID后填充
    @Schema(description = "关联汇总表主键ID（外键），新增时无需传", hidden = true)
    private Long jfhzjnsqId;


    @Schema(description = "分支机构社会信用代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分支机构社会信用代码不能为空")
    private String shxydm;

    @Schema(description = "分支机构单位全称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分支机构单位全称不能为空")
    private String dwmc;

    @Schema(description = "分支机构主管税务部门", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分支机构主管税务部门不能为空")
    private String zgsbm;

    @Schema(description = "分支机构职工人数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "分支机构职工人数不能为空")
    private Integer zgzs;

    @Schema(description = "分支机构月工资总额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "分支机构月工资总额不能为空")
    private BigDecimal ygzze;


    @Schema(description = "登记序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private  String djxh;


}