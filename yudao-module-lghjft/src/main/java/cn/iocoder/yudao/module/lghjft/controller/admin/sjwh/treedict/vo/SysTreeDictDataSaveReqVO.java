package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.treedict.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 树形字典数据新增/修改 Request VO")
@Data
public class SysTreeDictDataSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private String id;

    @Schema(description = "所属字典编码")
    private String treeDict;

    @Schema(description = "父节点ID，0=根节点")
    private String pid;

    @Schema(description = "数据编码")
    private String code;

    @Schema(description = "数据标签")
    private String label;

    @Schema(description = "层级编码")
    private String levelCode;

    @Schema(description = "层级深度")
    private Integer levelDepth;

    @Schema(description = "排序号")
    private Integer orderNum;

    @Schema(description = "是否叶子节点 1=叶子")
    private String isLeaf;

    @Schema(description = "路径")
    private String path;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "备注")
    private String remark;

}
