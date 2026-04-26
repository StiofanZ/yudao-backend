package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.swrksj.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "管理后台 - 税务入库数据分页 Request VO")
@Data
public class SwrksjPageReqVO extends PageParam {

    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "SPUUID")
    private String spuuid;

    @Schema(description = "登记序号")
    private String djxh;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "nsrmc")
    private String nsrmc;

    @Schema(description = "zgswjDm")
    private String zgswjDm;

    @Schema(description = "jsbj")
    private String jsbj;

    @Schema(description = "zspmDm")
    private String zspmDm;

    @Schema(description = "所属期起")
    private String skssqq;

    @Schema(description = "所属期止")
    private String skssqz;

    @Schema(description = "入库日期起")
    private String rkrqq;

    @Schema(description = "入库日期止")
    private String rkrqz;
}
