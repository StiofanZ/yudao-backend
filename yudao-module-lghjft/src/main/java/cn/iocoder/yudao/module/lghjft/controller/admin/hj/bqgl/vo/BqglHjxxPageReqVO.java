package cn.iocoder.yudao.module.lghjft.controller.admin.hj.bqgl.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 标签户籍信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BqglHjxxPageReqVO extends PageParam {

    @Schema(description = "标签代码", requiredMode = Schema.RequiredMode.REQUIRED, example = "BQ001")
    private String bqDm;

    @Schema(description = "社会信用代码", example = "91110101123456789X")
    private String shxydm;

}
