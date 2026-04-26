package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfzzgl.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 小额缴费组织管理分页 Request VO")
@Data
public class XejfghzzPageReqVO extends PageParam {
    @Schema(description = "登记序号")
    private String djxh;
    @Schema(description = "工会机构代码")
    private String deptId;
    @Schema(description = "社会信用代码")
    private String shxydm;
    @Schema(description = "纳税人名称")
    private String nsrmc;
    @Schema(description = "税管员姓名")
    private String ssglyxm;
    @Schema(description = "财务联系人")
    private String lxr;
    @Schema(description = "联系电话")
    private String lxdh;
    @Schema(description = "工会类别代码")
    private String ghlbDm;
    @Schema(description = "系统类别代码")
    private String xtlbDm;
    @Schema(description = "25小额类型")
    private String[] xejfzz255;
    @Schema(description = "确认后的小额类型")
    private String[] xejfzz25;
    @Schema(description = "基层工会账户")
    private String jcghzh;
    @Schema(description = "基层工会行号")
    private String jcghhh;
}
