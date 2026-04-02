package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.treedict.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 树形字典 Response VO")
@Data
public class SysTreeDictResVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private String id;

    @Schema(description = "字典名称")
    private String name;

    @Schema(description = "字典编码")
    private String code;

    @Schema(description = "结构类型 0=平级 1=树形")
    private String struType;

    @Schema(description = "是否系统参数 0=系统 1=自定义")
    private String isSysParam;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "租户ID")
    private String tenantId;

    @Schema(description = "创建者")
    private String createBy;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}
