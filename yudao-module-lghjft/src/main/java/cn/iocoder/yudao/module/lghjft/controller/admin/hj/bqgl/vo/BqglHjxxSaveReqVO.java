package cn.iocoder.yudao.module.lghjft.controller.admin.hj.bqgl.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Schema(description = "管理后台 - 标签户籍信息保存 Request VO")
@Data
public class BqglHjxxSaveReqVO {

    @Schema(description = "标签代码", requiredMode = Schema.RequiredMode.REQUIRED, example = "BQ001")
    @NotBlank(message = "标签代码不能为空")
    private String bqDm;

    @Schema(description = "登记序号列表", requiredMode = Schema.RequiredMode.REQUIRED, example = "[10001, 10002]")
    @NotEmpty(message = "登记序号列表不能为空")
    private List<String> djxhList;

    @Schema(description = "是否删除(true:取消标记, false/null:标记)", example = "false")
    private Boolean deleted;
}
