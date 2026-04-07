package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zswzgdw.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 征收未主管单位新增/修改 Request VO")
@Data
public class ZswzgdwSaveReqVO {

    @Schema(description = "序号")
    private String id;

    @Schema(description = "默认归属工会")
    private String dwdm;

    @Schema(description = "主管工会")
    private String deptId;

    @Schema(description = "主管税务局代码")
    private String zgswjDm;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人名称")
    private String nsrmc;

    @Schema(description = "登记序号（主键，修改时必传）")
    private String djxh;

    @Schema(description = "缴费笔数")
    private Long bs;

    @Schema(description = "缴费金额")
    private BigDecimal jfje;

    @Schema(description = "基层工会金额")
    private BigDecimal jcghje;

    @Schema(description = "确认结果代码")
    private String qrjgDm;

    @Schema(description = "征收未主管信息确认列表")
    private List<ZswzgdwQrVO> zswzgdwQrList;
}
