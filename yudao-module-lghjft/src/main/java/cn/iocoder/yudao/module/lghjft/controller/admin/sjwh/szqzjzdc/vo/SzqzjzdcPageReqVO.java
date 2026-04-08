package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.szqzjzdc.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "管理后台 - 省总做账导出分页 Request VO")
@Data
public class SzqzjzdcPageReqVO extends PageParam {

    @Schema(description = "凭证编号")
    private String pzbh;

    @Schema(description = "会计年")
    private String kjn;

    @Schema(description = "凭证类型")
    private String pzlx;

    @Schema(description = "凭证日期")
    private String pzrq;

    @Schema(description = "会计体系")
    private String kjtx;

    @Schema(description = "摘要")
    private String zy;

    @Schema(description = "会计科目")
    private String kjkmm;

    @Schema(description = "贷方金额")
    private String dfje;

    @Schema(description = "附件张数")
    private String fjzs;

    @Schema(description = "制单人")
    private String zdr;

    @Schema(description = "资金性质")
    private String zjxz;

    @Schema(description = "下级工会")
    private String xjgh;

    // -- legacy query params for sd/hy/cbj list variants --

    @Schema(description = "划款批次号")
    private String hkpch;

    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "账号")
    private String zh;

    @Schema(description = "开始划款批次号")
    private String beginHkpch;

    @Schema(description = "结束划款批次号")
    private String endHkpch;
}
