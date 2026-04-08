package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jhdwyds.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 应代收单位分页 Request VO")
@Data
public class JhdwydsPageReqVO extends PageParam {

    @Schema(description = "工会机构", example = "7269")
    private String deptId;

    @Schema(description = "登记序号")
    private String djxh;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人名称")
    private String nsrmc;

    @Schema(description = "主管税务机关代码")
    private String zgswjDm;

    @Schema(description = "科所分局代码")
    private String zgswskfjDm;

    @Schema(description = "科所分局名称")
    private String zgswskfjmc;

    @Schema(description = "单位地址")
    private String dz;

    @Schema(description = "单位联系人")
    private String dwcwlxr;

    @Schema(description = "单位联系电话")
    private String dwcwlxdh;

    @Schema(description = "纳税人状态代码")
    private String nsrztDm;

    @Schema(description = "税务登记职工人数")
    private String zgrs;

    @Schema(description = "工会登记职工人数")
    private String ghzgrs;

    @Schema(description = "工会法人统一社会信用代码")
    private String ghshxydm;

    @Schema(description = "工会组织名称")
    private String ghmc;

    @Schema(description = "工会类型代码")
    private String ghlxDm;

    @Schema(description = "工会联系人")
    private String ghjflxr;

    @Schema(description = "工会联系电话")
    private String ghjflxdh;

    @Schema(description = "ghzx")
    private String ghzx;

    @Schema(description = "ZXLXDH")
    private String zxlxdh;

    @Schema(description = "成立工会标记（C 筹建工会,Y 建立工会 ,N 待筹建）")
    private String clghbj;

    @Schema(description = "成立工会日期（筹备金建会日期）")
    private LocalDateTime clghrq;

    @Schema(description = "工会状态代码")
    private String ghztDm;

    @Schema(description = "省级工会名称")
    private String sjghmc;

    @Schema(description = "基层工会账户")
    private String jcghzh;

    @Schema(description = "基层工会户名")
    private String jcghhm;

    @Schema(description = "基层工会行号")
    private String jcghhh;

    @Schema(description = "基层工会银行")
    private String jcghyh;

    @Schema(description = "备注")
    private String bz;

    @Schema(description = "创建人")
    private String createBy;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "修改人")
    private String updateBy;

    @Schema(description = "街道乡镇代码")
    private String jdxzDm;

    @Schema(description = "税务数据同步时间")
    private LocalDateTime sjtbSj;

}