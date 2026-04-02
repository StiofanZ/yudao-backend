package cn.iocoder.yudao.module.lghjft.controller.app.xxzx.xxtx.vo;

import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.XxtxMessageResVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 消息响应 VO")
@Data
public class XxtxMessageAppResVO extends XxtxMessageResVO {

    @Schema(description = "阅读状态（0：未读，1：已读）", example = "0")
    private Integer readStatus;

}
