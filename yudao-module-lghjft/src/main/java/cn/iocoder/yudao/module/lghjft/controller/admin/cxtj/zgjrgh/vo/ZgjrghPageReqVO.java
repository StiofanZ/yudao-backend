package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zgjrgh.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 金融工会信息核对分页 Request VO")
@Data
public class ZgjrghPageReqVO extends PageParam {

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人名称")
    private String nsrmc;

    @Schema(description = "工会机构代码(部门)")
    private String deptId;

    @Schema(description = "成立工会标记")
    private String clghbj;

    @Schema(description = "行业工会标志")
    private String hyghbz;

    @Schema(description = "工会类别代码")
    private String ghlbDm;

    @Schema(description = "系统类别代码")
    private String xtlbDm;

    @Schema(description = "征收品目代码")
    private String zspmDm;

    @Schema(description = "工资总额")
    private BigDecimal gzze;

    @Schema(description = "税率")
    private BigDecimal sl;

    @Schema(description = "缴费金额(应补退税额)")
    private BigDecimal ybtse;

    @Schema(description = "税款所属期起")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime skssqq;

    @Schema(description = "税款所属期止")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime skssqz;

    @Schema(description = "入库日期-开始")
    private String beginRkrq;

    @Schema(description = "入库日期-结束")
    private String endRkrq;

    @Schema(description = "结算标记")
    private String jsbj;

    @Schema(description = "结算日期-开始")
    private String beginJsrq;

    @Schema(description = "结算日期-结束")
    private String endJsrq;

    @Schema(description = "基层工会账号")
    private String jcghzh;

    @Schema(description = "基层工会户名")
    private String jcghhm;

    @Schema(description = "基层工会比例")
    private BigDecimal jcghbl;

    @Schema(description = "基层工会金额")
    private BigDecimal jcghje;

    @Schema(description = "经费拨付状态(筹备金退回标记)")
    private String cbjthbj;

    @Schema(description = "划款批次号")
    private String hkpch;
}
