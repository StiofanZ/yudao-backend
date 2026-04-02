package cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 户籍管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GhHjPageReqVO extends PageParam {

    @Schema(description = "登记序号", example = "123456")
    private String djxh;

    @Schema(description = "部门ID", example = "100")
    private String deptId;

    @Schema(description = "社会信用代码", example = "91110108772551611Y")
    private String shxydm;

    @Schema(description = "纳税人名称", example = "北京某某公司")
    private String nsrmc;

    @Schema(description = "行业工会标志")
    private String hyghbz;

    @Schema(description = "纳税人状态代码")
    private String nsrztDm;

    @Schema(description = "工会类别代码")
    private String ghlbDm;

    @Schema(description = "系统类别代码")
    private String xtlbDm;

    @Schema(description = "户籍分类9(复审标志)")
    private String hjfl9Dm;
}
