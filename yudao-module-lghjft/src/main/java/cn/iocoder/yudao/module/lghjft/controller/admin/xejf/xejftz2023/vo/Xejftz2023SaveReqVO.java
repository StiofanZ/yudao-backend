package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejftz2023.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 小额缴费统计新增/修改 Request VO")
@Data
public class Xejftz2023SaveReqVO {

    private Long ghjfId;
    private String deptId;
}
