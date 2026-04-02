package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.treedict.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 树形字典分页 Request VO")
@Data
public class SysTreeDictPageReqVO extends PageParam {

    @Schema(description = "字典名称")
    private String name;

    @Schema(description = "结构类型 0=平级 1=树形")
    private String struType;

}
