package cn.iocoder.yudao.module.lghjft.controller.app.nrgl.zcwj.vo;

import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcwj.vo.ZcwjReqVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "用户 App - 政策文件分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class ZcwjPageAppReqVO extends ZcwjReqVO {

    @Schema(description = "推荐条数", example = "5")
    private Integer limit;
}
