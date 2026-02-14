package cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.bqgl.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 标签关联户籍分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BqglHjxxPageReqVO extends PageParam {

    @Schema(description = "标签ID", example = "1")
    private String bqId;

    @Schema(description = "纳税人名称", example = "张三")
    private String nsrmc;

}
