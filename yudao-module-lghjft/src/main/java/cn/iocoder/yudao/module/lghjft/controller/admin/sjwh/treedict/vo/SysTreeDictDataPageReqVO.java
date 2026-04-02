package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.treedict.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 树形字典数据分页 Request VO")
@Data
public class SysTreeDictDataPageReqVO extends PageParam {

    @Schema(description = "所属字典编码")
    private String treeDict;

    @Schema(description = "数据标签")
    private String label;

    @Schema(description = "是否叶子节点 1=叶子")
    private String isLeaf;

}
