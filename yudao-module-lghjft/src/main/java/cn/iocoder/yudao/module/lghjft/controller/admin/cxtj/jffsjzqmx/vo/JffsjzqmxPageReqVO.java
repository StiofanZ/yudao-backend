package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffsjzqmx.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 分年各级分成情况分页 Request VO")
@Data
public class JffsjzqmxPageReqVO extends PageParam {

    @Schema(description = "deptId")
    private String deptId;
    @Schema(description = "djxh")
    private String djxh;
    @Schema(description = "shxydm")
    private String shxydm;
    @Schema(description = "nsrsbh")
    private String nsrsbh;
    @Schema(description = "nsrmc")
    private String nsrmc;
    @Schema(description = "nsrjc")
    private String nsrjc;
    @Schema(description = "skssqq")
    private String skssqq;
    @Schema(description = "skssqz")
    private String skssqz;
    @Schema(description = "zgswjDm")
    private String zgswjDm;
    @Schema(description = "beginRkrq")
    private String beginRkrq;
    @Schema(description = "endRkrq")
    private String endRkrq;
}
