package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.wjgl.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 文件管理创建/更新 Request VO")
@Data
public class WjglSaveReqVO {
    @Schema(description = "文件编号")
    private Long fileid;
    @Schema(description = "部门ID")
    private String deptId;
    @Schema(description = "文件名称")
    private String filename;
    @Schema(description = "文件类别")
    private String filestatus;
    @Schema(description = "文件URL")
    private String fileurl;
    @Schema(description = "备注")
    private String bz;
}
