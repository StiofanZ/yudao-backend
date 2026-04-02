package cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 户籍管理 - 查询纳税人信息结果中的户籍条目
 * 包含 dept_name（已维护机构名称）供前端展示
 */
@Schema(description = "管理后台 - 户籍纳税人查询结果 VO")
@Data
public class GhHjNsrxxResVO {

    @Schema(description = "登记序号")
    private String djxh;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人名称")
    private String nsrmc;

    @Schema(description = "部门ID")
    private String deptId;

    @Schema(description = "部门名称")
    private String deptName;
}
