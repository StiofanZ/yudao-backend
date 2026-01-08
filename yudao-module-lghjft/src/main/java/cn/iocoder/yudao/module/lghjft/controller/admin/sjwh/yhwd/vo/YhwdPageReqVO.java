package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.yhwd.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 银行网点分页 Request VO")
@Data
public class YhwdPageReqVO extends PageParam {

    @Schema(description = "银行行别代码")
    private String yhhbDm;

    @Schema(description = "网点代码")
    private String yhwdDm;

    @Schema(description = "网点名称")
    private String yhwdmc;

    @Schema(description = "网点简称")
    private String yhwdjc;

    @Schema(description = "网点行号")
    private String wdhh;

    @Schema(description = "清算行号")
    private String qshh;

    @Schema(description = "行政区划代码")
    private String xzqhDm;

    @Schema(description = "顺序号")
    private Integer sxh;

    @Schema(description = "有效期止")
    private LocalDateTime yxqz;

    @Schema(description = "网点地址")
    private String wddz;

    @Schema(description = "网点电话")
    private String wddh;

}