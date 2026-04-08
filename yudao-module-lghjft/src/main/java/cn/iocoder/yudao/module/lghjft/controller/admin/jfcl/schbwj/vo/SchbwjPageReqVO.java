package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.schbwj.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * v1 selecList query params: hkpch, beginHkpch/endHkpch (MID date range)
 * v1 selectGhjfhb query params: jsrqStart, jsrqEnd
 */
@Schema(description = "管理后台 - 生成划拨文件分页 Request VO")
@Data
public class SchbwjPageReqVO extends PageParam {

    @Schema(description = "划款批次号")
    private String hkpch;

    @Schema(description = "拨付文件日期-起(yyyyMMdd)")
    private String beginHkpch;

    @Schema(description = "拨付文件日期-止(yyyyMMdd)")
    private String endHkpch;

    @Schema(description = "分页偏移量(内部计算)", hidden = true)
    private Integer offset;
}
