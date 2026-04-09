package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfhzjkjl.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 银行拨付汇总监控记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class YhbfhzjkjlPageReqVO extends PageParam {

    @Schema(description = "监控接口名称", example = "银行拨付查询")
    private String jkmc;

    @Schema(description = "付款ID/汇总信息ID", example = "1024")
    private Long fkId;

    @Schema(description = "请求报文", example = "{}")
    private String qqbw;

    @Schema(description = "响应报文", example = "{}")
    private String xybw;

    @Schema(description = "请求状态 S-成功 E-异常", example = "S")
    private String qqzt;

    @Schema(description = "请求结果信息", example = "调用成功")
    private String qqjgxx;

    @Schema(description = "创建者", example = "admin")
    private String createBy;

    @Schema(description = "更新者", example = "admin")
    private String updateBy;

    @Schema(description = "创建时间 - 开始")
    private String beginCreateTime;

    @Schema(description = "创建时间 - 结束")
    private String endCreateTime;

    @Schema(description = "更新时间")
    private String updateTime;
}
