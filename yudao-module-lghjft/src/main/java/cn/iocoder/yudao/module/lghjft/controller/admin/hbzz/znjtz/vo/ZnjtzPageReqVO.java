package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.znjtz.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "管理后台 - 滞纳金台账分页 Request VO")
@Data
public class ZnjtzPageReqVO extends PageParam {

    @Schema(description = "deptId")
    private String deptId;

    @Schema(description = "shxydm")
    private String shxydm;

    @Schema(description = "nsrmc")
    private String nsrmc;

    @Schema(description = "zgswjDm")
    private String zgswjDm;

    @Schema(description = "jsbj")
    private String jsbj;

    @Schema(description = "zspmDm")
    private String zspmDm;

    private String skssqq;
    private String skssqz;
    private String ybtse;
    private String beginRkrq;
    private String endRkrq;
}
