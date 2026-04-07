package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zgjrgh.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 金融工会信息核对分页 Request VO")
@Data
public class ZgjrghPageReqVO extends PageParam {

    @Schema(description = "djxh")
    private String djxh;

    @Schema(description = "shxydm")
    private String shxydm;

    @Schema(description = "nsrmc")
    private String nsrmc;

    @Schema(description = "nsrjc")
    private String nsrjc;

    @Schema(description = "deptId")
    private String deptId;

    @Schema(description = "税款所属期起")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime skssqq;

    @Schema(description = "税款所属期止")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime skssqz;

    @Schema(description = "入库日期-开始")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime beginRkrq;

    @Schema(description = "入库日期-结束")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endRkrq;

    @Schema(description = "结算日期-开始")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime beginJsrq;

    @Schema(description = "结算日期-结束")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endJsrq;

    @Schema(description = "结算标记")
    private String jsbj;
}
