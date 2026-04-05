package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jftzfn.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 经费台账分年分页 Request VO")
@Data
public class JftzfnPageReqVO extends PageParam {

    @Schema(description = "登记序号")
    private String djxh;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "工会机构行政级别")
    private String ghjgxzjb;

    @Schema(description = "成立工会标记")
    private String clghbj;

    @Schema(description = "行业工会标志")
    private String hyghbz;

    @Schema(description = "属地工会行政级别")
    private String sdghjgxzjb;

    @Schema(description = "主管税务局代码")
    private String zgswjDm;

    @Schema(description = "主管税务分局代码")
    private String zgswskfjDm;

    @Schema(description = "税管员代码")
    private String ssglyDm;

    @Schema(description = "工会类别代码")
    private String ghlbDm;

    @Schema(description = "系统类别代码")
    private String xtlbDm;

    @Schema(description = "征收品目代码")
    private String zspmDm;

    @Schema(description = "所属期起")
    private LocalDateTime skssqq;

    @Schema(description = "所属期止")
    private LocalDateTime skssqz;

    @Schema(description = "应补退税额")
    private BigDecimal ybtse;

    @Schema(description = "入库开始日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime beginRkrq;

    @Schema(description = "入库结束日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endRkrq;

    @Schema(description = "入库日期")
    private LocalDateTime rkrq;

    @Schema(description = "结算标记")
    private List<String> jsbj;

    @Schema(description = "结算开始日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime beginJsrq;

    @Schema(description = "结算结束日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endJsrq;

    @Schema(description = "划款批次号")
    private String hkpch;

    @Schema(description = "筹备金退回标记")
    private String cbjthbj;

    @Schema(description = "小微类型")
    private String xwlx;
}
