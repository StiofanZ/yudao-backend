package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jftzfswjg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 分税务机关统计新增/修改 Request VO")
@Data
public class JftzfswjgSaveReqVO {

    private Long ghjfId;
    private String deptId;
}
