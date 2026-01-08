package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.swjg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 税务机关列表 Request VO")
@Data
public class SwjgListReqVO {

    @Schema(description = "税务机关代码")
    private  String swjgDm;

    @Schema(description = "税务机关名称")
    private String swjgmc;

    @Schema(description = "税务机关简称")
    private String swjgjc;

    @Schema(description = "地址")
    private String dz;

    @Schema(description = "邮政编码")
    private String yzbm;

    @Schema(description = "联系人")
    private String lxr;

    @Schema(description = "联系电话")
    private String lxdh;

    @Schema(description = "手续费账号")
    private String sxfzh;

    @Schema(description = "户名")
    private String sxfhm;

    @Schema(description = "行号")
    private String sxfhh;

    @Schema(description = "银行")
    private String sxfyh;

    @Schema(description = "上级税务机关代码")
    private String sjswjgDm;

    @Schema(description = "稽查局标记")
    private String jcjbj;

    @Schema(description = "工会机构代码")
    private String ghjgDm;

    @Schema(description = "顺序号")
    private Integer sxh;

    @Schema(description = "校验码")
    private String jym;

}