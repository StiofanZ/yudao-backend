package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.hkxxbfzhpc.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 拨付单位排除 Response VO")
@Data
public class BfdwResVO {

    @Schema(description = "审批UUID")
    private String spuuid;

    @Schema(description = "登记序号")
    private String djxh;

    @Schema(description = "部门ID")
    private String deptId;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人名称")
    private String nsrmc;

    @Schema(description = "基层工会账号")
    private String jcghzh;

    @Schema(description = "基层工会户名")
    private String jcghhm;

    @Schema(description = "基层工会行号")
    private String jcghhh;

    @Schema(description = "入库日期")
    private String rkrq;

    @Schema(description = "应补退税额")
    private BigDecimal ybtse;

    @Schema(description = "税款所属期起")
    private String skssqq;

    @Schema(description = "税款所属期止")
    private String skssqz;

    @Schema(description = "排除状态")
    private String zt;

    @Schema(description = "备注")
    private String bz;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
