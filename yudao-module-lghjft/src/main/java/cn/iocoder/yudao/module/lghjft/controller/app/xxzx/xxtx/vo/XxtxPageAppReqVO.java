package cn.iocoder.yudao.module.lghjft.controller.app.xxzx.xxtx.vo;

import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.XxtxMessagePageReqVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "用户 App - 消息分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class XxtxPageAppReqVO extends XxtxMessagePageReqVO {

}
