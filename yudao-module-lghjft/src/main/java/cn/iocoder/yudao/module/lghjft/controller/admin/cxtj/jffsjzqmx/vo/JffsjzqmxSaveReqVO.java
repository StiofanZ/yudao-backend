package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffsjzqmx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 分年各级分成情况新增/修改 Request VO")
@Data
public class JffsjzqmxSaveReqVO {

    private Long ghjfId;
    private String deptId;
}
