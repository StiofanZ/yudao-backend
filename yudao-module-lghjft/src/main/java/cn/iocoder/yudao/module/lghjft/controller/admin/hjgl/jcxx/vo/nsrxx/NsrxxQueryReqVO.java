package cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo.nsrxx;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 纳税人查询 Request VO")
@Data
public class NsrxxQueryReqVO {

    @Schema(description = "搜索关键字（纳税人识别号/名称/社会信用代码）")
    private String keyword;

}
