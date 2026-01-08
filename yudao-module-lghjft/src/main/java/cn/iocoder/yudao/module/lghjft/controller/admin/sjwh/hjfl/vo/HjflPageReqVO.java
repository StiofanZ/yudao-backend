package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.hjfl.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 户籍分类分页 Request VO")
@Data
public class HjflPageReqVO extends PageParam {

    @Schema(description = "工会机构代码", example = "76")
    private String deptId;

    @Schema(description = "备注")
    private String bz;

    @Schema(description = "户籍分类代码")
    private String hjflDm;

    @Schema(description = "户籍分类名称")
    private String hjflmc;

    @Schema(description = "顺序号")
    private Short sxh;

    @Schema(description = "户籍分类id", example = "27518")
    private Integer hjflid;

}