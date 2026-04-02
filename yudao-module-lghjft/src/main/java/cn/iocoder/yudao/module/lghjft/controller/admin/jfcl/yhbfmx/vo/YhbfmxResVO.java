package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfmx.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 银行拨付明细 Response VO")
@Data
@ExcelIgnoreUnannotated
public class YhbfmxResVO {

    @Schema(description = "业务参考号", requiredMode = Schema.RequiredMode.REQUIRED, example = "16285")
    @ExcelProperty("业务参考号")
    private Integer bfid;

    @Schema(description = "收款人编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "7802")
    @ExcelProperty("收款人编号")
    private Long hkxxId;

    @Schema(description = "*收款人账号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("*收款人账号")
    private String zh;

    @Schema(description = "*收款人名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("*收款人名称")
    private String hm;

    @Schema(description = "*收方开户支行")
    @ExcelProperty("*收方开户支行")
    private String yhmc;

    @Schema(description = "收方联行号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("收方联行号")
    private String sflhh;

    @Schema(description = "收款人开户地")
    @ExcelProperty("收款人开户地")
    private byte[] skrkhd;

    @Schema(description = "收方邮件地址")
    @ExcelProperty("收方邮件地址")
    private byte[] sfyjdz;

    @Schema(description = "收方移动电话")
    @ExcelProperty("收方移动电话")
    private byte[] sfyddh;

    @Schema(description = "币种")
    @ExcelProperty("币种")
    private String rmb;

    @Schema(description = "付款分行")
    @ExcelProperty("付款分行")
    private byte[] fkfh;

    @Schema(description = "*结算方式")
    @ExcelProperty("*结算方式")
    private String jsfs;

    @Schema(description = "业务种类")
    @ExcelProperty("业务种类")
    private byte[] ywzl;

    @Schema(description = "*付方账号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("*付方账号")
    private String ffzh;

    @Schema(description = "付方记账子单元编号")
    @ExcelProperty("付方记账子单元编号")
    private String ffjzzdy;

    @Schema(description = "期望日")
    @ExcelProperty("期望日")
    private String qwr;

    @Schema(description = "期望时间")
    @ExcelProperty("期望时间")
    private String qwsj;

    @Schema(description = "*用途")
    @ExcelProperty("*用途")
    private String yt;

    @Schema(description = "*金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("*金额")
    private BigDecimal je;

    @Schema(description = "部门", example = "10745")
    @ExcelProperty("部门")
    private String deptId;

    @Schema(description = "内部附言(摘要)")
    @ExcelProperty("内部附言(摘要)")
    private String fy;

    @Schema(description = "划款批次号")
    @ExcelProperty("划款批次号")
    private String hkpch;

    @Schema(description = "拨付汇总批次号")
    @ExcelProperty("拨付汇总批次号")
    private String bfhzpch;

    @Schema(description = "拨付批次号")
    @ExcelProperty("拨付批次号")
    private String bfpch;

    @Schema(description = "分成类型")
    @ExcelProperty("分成类型")
    private String fclx;

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Integer xh;

    @Schema(description = "uuid序列号", example = "7315")
    @ExcelProperty("uuid序列号")
    private String uuid;

    @Schema(description = "拨付类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("拨付类型")
    private String bflx;

    @Schema(description = "拨付状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("拨付状态")
    private String bfzt;

    @Schema(description = "处理结果")
    @ExcelProperty("处理结果")
    private String bfjg;

    @Schema(description = "退回日期")
    @ExcelProperty("退回日期")
    private String thrq;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String bz;

    @Schema(description = "生成汇总信息")
    @ExcelProperty("生成汇总信息")
    private String schzxx;

    @Schema(description = "有效标记", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("有效标记")
    private String yxbj;

    @Schema(description = "原始退回标记")
    @ExcelProperty("原始退回标记")
    private String ysthbj;

    @Schema(description = "原始退回日期")
    @ExcelProperty("原始退回日期")
    private LocalDateTime ysthrq;

    @Schema(description = "原始退回原因")
    @ExcelProperty("原始退回原因")
    private String ysthyy;

    @Schema(description = "原始创建人")
    @ExcelProperty("原始创建人")
    private String yscjr;

    @Schema(description = "原始创建时间")
    @ExcelProperty("原始创建时间")
    private LocalDateTime yscjsj;

    @Schema(description = "ysxgr")
    @ExcelProperty("ysxgr")
    private String ysxgr;

    @Schema(description = "原始修改时间")
    @ExcelProperty("原始修改时间")
    private LocalDateTime ysxgsj;

    @Schema(description = "创建人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人")
    private String createBy;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "修改人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("修改人")
    private String updateBy;

}