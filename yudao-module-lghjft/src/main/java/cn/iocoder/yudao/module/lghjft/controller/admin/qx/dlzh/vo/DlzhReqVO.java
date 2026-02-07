package cn.iocoder.yudao.module.lghjft.controller.admin.qx.dlzh.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 登录账号分页 Request VO")
@Data
public class DlzhReqVO extends PageParam {

    @Schema(description = "用户账号", example = "zhangsan")
    private String yhzh;

    @Schema(description = "用户姓名", example = "张三")
    private String yhxm;

    @Schema(description = "联系电话", example = "13800000000")
    private String lxdh;

    @Schema(description = "用户邮箱", example = "a@b.com")
    private String yhyx;

    @Schema(description = "社会信用代码", example = "91320101MA1XXXXXXX")
    private String shxydm;

    @Schema(description = "帐号状态（0正常 1停用）", example = "0")
    private Integer status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
