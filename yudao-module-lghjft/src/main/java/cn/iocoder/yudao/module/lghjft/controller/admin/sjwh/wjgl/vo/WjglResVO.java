package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.wjgl.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Schema(description = "管理后台 - 文件管理 Response VO")
@Data
public class WjglResVO {
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
    @Schema(description = "创建者")
    private String createBy;
    @Schema(description = "创建时间")
    private Date createTime;
    @Schema(description = "更新者")
    private String updateBy;
    @Schema(description = "更新时间")
    private Date updateTime;
}
