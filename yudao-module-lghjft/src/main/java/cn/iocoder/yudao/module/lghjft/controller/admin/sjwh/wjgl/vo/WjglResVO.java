package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.wjgl.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 文件管理 Response VO")
@Data
public class WjglResVO {
    @Schema(description = "文件编号")
    private Long fileid;
    @Schema(description = "部门ID")
    @ExcelProperty("部门编号")
    private String deptId;
    @Schema(description = "文件名称")
    @ExcelProperty("文件大小")
    private String filename;
    @Schema(description = "文件类别")
    @ExcelProperty("文件状态(0-失效，1-生效)")
    private String filestatus;
    @Schema(description = "文件URL")
    @ExcelProperty("文件url")
    private String fileurl;
    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String bz;
    @Schema(description = "创建者")
    @ExcelProperty("创建人")
    private String createBy;
    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private Date createTime;
    @Schema(description = "更新者")
    @ExcelProperty("更新人")
    private String updateBy;
    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private Date updateTime;
}
