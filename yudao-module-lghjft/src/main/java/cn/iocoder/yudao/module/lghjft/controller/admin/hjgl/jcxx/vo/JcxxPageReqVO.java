package cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.jcxx.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 户籍管理/基础信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class JcxxPageReqVO extends PageParam {

    @Schema(description = "工会机构代码")
    private String deptId;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人名称")
    private String nsrmc;

    @Schema(description = "街道乡镇代码")
    private String jdxzDm;

    @Schema(description = "科所分局代码")
    private String zgswskfjDm;

    @Schema(description = "基层工会账户")
    private String jcghzh;

    @Schema(description = "基层工会户名")
    private String jcghhm;

    @Schema(description = "基层工会行号")
    private String jcghhh;

    @Schema(description = "基层工会银行")
    private String jcghyh;

    @Schema(description = "工会类别代码")
    private String ghlbDm;

    @Schema(description = "系统类别代码")
    private String xtlbDm;

    @Schema(description = "成立工会标志")
    private String clghbj;

    @Schema(description = "成立工会日期开始")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime beginClghrq;

    @Schema(description = "成立工会日期结束")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endClghrq;

}
