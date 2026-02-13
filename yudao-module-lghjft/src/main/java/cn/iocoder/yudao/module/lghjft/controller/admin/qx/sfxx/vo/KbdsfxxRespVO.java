package cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 可绑定身份信息 Response VO")
@Data
public class KbdsfxxRespVO {

    @Schema(description = "单位名称", example = "某某公司")
    private String dwmc;

    @Schema(description = "社会信用代码", example = "911101011234567890")
    private String shxydm;

    @Schema(description = "登记序号", example = "1000000000")
    private String djxh;

    @Schema(description = "人员姓名", example = "张三")
    private String ryxm;

    @Schema(description = "联系电话", example = "13800138000")
    private String lxdh;

    @Schema(description = "身份类型", example = "02")
    private String sflx;

    @Schema(description = "权限类型", example = "01")
    private String qxlx;

}
