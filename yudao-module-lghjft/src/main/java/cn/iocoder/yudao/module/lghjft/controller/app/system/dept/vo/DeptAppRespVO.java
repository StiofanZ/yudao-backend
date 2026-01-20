package cn.iocoder.yudao.module.lghjft.controller.app.system.dept.vo;

import cn.iocoder.yudao.module.system.controller.admin.dept.vo.dept.DeptRespVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "用户 App - 部门信息 Response VO")
@Data
public class DeptAppRespVO extends DeptRespVO {

    @Schema(description = "父部门 ID", example = "1024")
    private Long parentId;

    @Schema(description = "父部门名称", example = "1024")
    private String parentName;

    @Schema(description = "负责人的用户名称", example = "2048")
    private String leaderUserName;

    @Schema(description = "负责人的用户昵称", example = "2048")
    private String leaderNickname;

}
