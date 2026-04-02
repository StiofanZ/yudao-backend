package cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfcbj.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 小额筹备金做账分页 Request VO")
@Data
public class GhHkxxxejfcbjPageReqVO extends PageParam {
    @Schema(description = "划款信息ID")
    private Long hkxxId;
    @Schema(description = "划款批次号")
    private String hkpch;
    @Schema(description = "缴费期间")
    private Long jfqj;
    @Schema(description = "市州产业")
    private String sjdm;
    @Schema(description = "工会机构")
    private String deptId;
}
