package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jhdwyds.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 应代收单位 Response VO")
@Data
@ExcelIgnoreUnannotated
public class JhdwydsResVO {

    @Schema(description = "建会单位代收ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24703")
    @ExcelProperty("建会单位代收ID")
    private Integer jhdwId;

    @Schema(description = "工会机构", requiredMode = Schema.RequiredMode.REQUIRED, example = "7269")
    @ExcelProperty("工会机构")
    private String deptId;

    @Schema(description = "登记序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("登记序号")
    private String djxh;

    @Schema(description = "社会信用代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("纳税人名称")
    private String nsrmc;

    @Schema(description = "主管税务机关代码")
    @ExcelProperty("主管税务机关代码")
    private String zgswjDm;

    @Schema(description = "科所分局代码")
    @ExcelProperty("科所分局代码")
    private String zgswskfjDm;

    @Schema(description = "科所分局名称")
    @ExcelProperty("科所分局名称")
    private String zgswskfjmc;

    @Schema(description = "单位地址")
    @ExcelProperty("单位地址")
    private String dz;

    @Schema(description = "单位联系人")
    @ExcelProperty("单位联系人")
    private String dwcwlxr;

    @Schema(description = "单位联系电话")
    @ExcelProperty("单位联系电话")
    private String dwcwlxdh;

    @Schema(description = "纳税人状态代码")
    @ExcelProperty("纳税人状态代码")
    private String nsrztDm;

    @Schema(description = "税务登记职工人数")
    @ExcelProperty("税务登记职工人数")
    private Integer zgrs;

    @Schema(description = "工会登记职工人数")
    @ExcelProperty("工会登记职工人数")
    private Integer ghzgrs;

    @Schema(description = "工会法人统一社会信用代码")
    @ExcelProperty("工会法人统一社会信用代码")
    private String ghshxydm;

    @Schema(description = "工会组织名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("工会组织名称")
    private String ghmc;

    @Schema(description = "工会类型代码")
    @ExcelProperty("工会类型代码")
    private String ghlxDm;

    @Schema(description = "工会联系人")
    @ExcelProperty("工会联系人")
    private String ghjflxr;

    @Schema(description = "工会联系电话")
    @ExcelProperty("工会联系电话")
    private String ghjflxdh;

    @Schema(description = "ghzx")
    @ExcelProperty("ghzx")
    private String ghzx;

    @Schema(description = "ZXLXDH")
    @ExcelProperty("ZXLXDH")
    private String zxlxdh;

    @Schema(description = "成立工会标记（C 筹建工会,Y 建立工会 ,N 待筹建）")
    @ExcelProperty(value = "成立工会标记（C 筹建工会,Y 建立工会 ,N 待筹建）", converter = DictConvert.class)
    @DictFormat("sys_jhbz") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private String clghbj;

    @Schema(description = "成立工会日期（筹备金建会日期）")
    @ExcelProperty("成立工会日期（筹备金建会日期）")
    private LocalDateTime clghrq;

    @Schema(description = "工会状态代码")
    @ExcelProperty("工会状态代码")
    private String ghztDm;

    @Schema(description = "省级工会名称")
    @ExcelProperty("省级工会名称")
    private String sjghmc;

    @Schema(description = "基层工会账户")
    @ExcelProperty("基层工会账户")
    private String jcghzh;

    @Schema(description = "基层工会户名")
    @ExcelProperty("基层工会户名")
    private String jcghhm;

    @Schema(description = "基层工会行号")
    @ExcelProperty("基层工会行号")
    private String jcghhh;

    @Schema(description = "基层工会银行")
    @ExcelProperty("基层工会银行")
    private String jcghyh;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String bz;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String createBy;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "修改人")
    @ExcelProperty("修改人")
    private String updateBy;

    @Schema(description = "街道乡镇代码")
    @ExcelProperty("街道乡镇代码")
    private String jdxzDm;

    @Schema(description = "税务数据同步时间")
    @ExcelProperty("税务数据同步时间")
    private LocalDateTime sjtbSj;

}