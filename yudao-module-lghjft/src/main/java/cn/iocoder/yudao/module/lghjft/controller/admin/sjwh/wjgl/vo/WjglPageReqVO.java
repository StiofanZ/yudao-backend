package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.wjgl.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 文件管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WjglPageReqVO extends PageParam {
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
    @Schema(description = "更新者")
    private String updateBy;
    @Schema(description = "创建时间-开始")
    private String beginCreateTime;
    @Schema(description = "创建时间-结束")
    private String endCreateTime;
    @Schema(description = "更新时间-开始")
    private String beginUpdateTime;
    @Schema(description = "更新时间-结束")
    private String endUpdateTime;
}
