package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.ydsdw.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 应代收单位新增/修改 Request VO")
@Data
public class ydsdwSaveReqVO {

    @Schema(description = "建会单位代收ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24703")
    private Integer jhdwId;

    @Schema(description = "工会机构", requiredMode = Schema.RequiredMode.REQUIRED, example = "7269")
    private String deptId;

    @Schema(description = "登记序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String djxh;

    @Schema(description = "社会信用代码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String shxydm;

    @Schema(description = "纳税人名称", requiredMode = Schema.RequiredMode.REQUIRED)
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
    private Integer zgrs;

    @Schema(description = "工会登记职工人数")
    private Integer ghzgrs;

    @Schema(description = "工会法人统一社会信用代码")
    private String ghshxydm;

    @Schema(description = "工会组织名称", requiredMode = Schema.RequiredMode.REQUIRED)
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

    @Schema(description = "修改人")
    private String updateBy;

    @Schema(description = "街道乡镇代码")
    private String jdxzDm;

    @Schema(description = "税务数据同步时间")
    private LocalDateTime sjtbSj;

}