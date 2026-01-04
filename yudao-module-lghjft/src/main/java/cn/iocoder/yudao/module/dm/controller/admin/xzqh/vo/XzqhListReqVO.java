package cn.iocoder.yudao.module.dm.controller.admin.xzqh.vo;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 行政区划列表 Request VO")
@Data
public class XzqhListReqVO {

    @Schema(description = "行政区划名称")
    private String xzqhmc;

    @Schema(description = "上级行政区划代码")
    private String sjxzqhDm;

    @Schema(description = "行政区划级别")
    private String xzqhjb;

    @ExcelProperty("行政区划代码")
    private Long xzqhDm;
}