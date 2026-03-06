package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcwj.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 政策文件分页 Request VO")
@Data
public class ZcwjReqVO extends PageParam {

    @Schema(description = "标题")
    private String title;

    @Schema(description = "关键字")
    private String keyword;

    @Schema(description = "标签")
    private String tags;

    @Schema(description = "版本号")
    private String versionNo;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "部门 ID")
    private Long deptId;

    @Schema(description = "发布部门")
    private Integer fbbm;
}
