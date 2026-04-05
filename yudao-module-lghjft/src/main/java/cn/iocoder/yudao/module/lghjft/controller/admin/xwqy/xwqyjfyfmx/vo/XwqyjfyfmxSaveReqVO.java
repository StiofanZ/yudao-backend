package cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjfyfmx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 小微企业经费60%明细新增/修改 Request VO")
@Data
public class XwqyjfyfmxSaveReqVO {

    private Long ghjfId;
    private String spuuid;
    private String djxh;
    private String shxydm;
    private String nsrmc;
    private String nsrjc;
    private String deptId;
}
