package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.szdzhd.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 省总到账核对分页 Request VO")
@Data
public class SzdzhdPageReqVO extends PageParam {

    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "划款批次号-开始")
    private String beginHkpch;

    @Schema(description = "划款批次号-结束")
    private String endHkpch;

    @Schema(description = "结算日期-开始")
    private String beginJsrq;

    @Schema(description = "结算日期-结束")
    private String endJsrq;
}
