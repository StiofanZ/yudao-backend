package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqdssj.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 读取增量代收数据分页 Request VO")
@Data
public class DqzldssjPageReqVO extends PageParam {

    @Schema(description = "税票ID")
    private String spuuid;

    @Schema(description = "登记序号")
    private String djxh;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人名称")
    private String nsrmc;

    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "所属期起")
    private LocalDateTime skssqq;

    @Schema(description = "所属期止")
    private LocalDateTime skssqz;

    @Schema(description = "征收品目代码")
    private String zspmDm;

    @Schema(description = "增量标记")
    private String zlbj;

    @Schema(description = "入库日期-起")
    private LocalDateTime rkrqStart;

    @Schema(description = "入库日期-止")
    private LocalDateTime rkrqEnd;
}
