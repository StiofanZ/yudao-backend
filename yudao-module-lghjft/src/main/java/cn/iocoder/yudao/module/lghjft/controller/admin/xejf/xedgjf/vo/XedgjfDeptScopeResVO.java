package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xedgjf.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 小额代管经费做账部门查询范围 Response VO")
@Data
public class XedgjfDeptScopeResVO {

    @Schema(description = "实际查询部门ID，为空表示不按部门过滤")
    private String deptId;

    @Schema(description = "银行账号")
    private String yhzh;

    @Schema(description = "银行账号1")
    private String yhzh1;

    @Schema(description = "银行账号2")
    private String yhzh2;

    @Schema(description = "银行账号3")
    private String yhzh3;
}
