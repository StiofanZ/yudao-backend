package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.szqzjzdc.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "管理后台 - 省总做账导出分页 Request VO")
@Data
public class SzqzjzdcPageReqVO extends PageParam {

    @Schema(description = "hkpch")
    private String hkpch;

    @Schema(description = "deptId")
    private String deptId;

    @Schema(description = "zh")
    private String zh;

    @Schema(description = "kjn")
    private String kjn;
}
