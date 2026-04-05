package cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjtz.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "管理后台 - 筹备金台账分页 Request VO")
@Data
public class CbjtzPageReqVO extends PageParam {

    @Schema(description = "deptId")
    private String deptId;

    @Schema(description = "nsrmc")
    private String nsrmc;

    @Schema(description = "zgswjDm")
    private String zgswjDm;

    @Schema(description = "jsbj")
    private String jsbj;

    @Schema(description = "zspmDm")
    private String zspmDm;

    private String shxydm;
    private String skssqq;
    private String skssqz;
    private String ybtse;
    private String cbjthbj;
    private String hkpzh;
    private String fbbj;
    private String bz;
    private String beginRkrq;
    private String endRkrq;
    private String beginJsrq;
    private String endJsrq;
    private String beginFbrq;
    private String endFbrq;
}
