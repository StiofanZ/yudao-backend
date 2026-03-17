package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfmx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 银行拨付明细新增/修改 Request VO")
@Data
public class YhbfmxSaveReqVO {

    @Schema(description = "业务参考号", requiredMode = Schema.RequiredMode.REQUIRED, example = "16285")
    private Integer bfid;

    @Schema(description = "收款人编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "7802")
    @NotNull(message = "收款人编号不能为空")
    private Long hkxxId;

    @Schema(description = "*收款人账号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "*收款人账号不能为空")
    private String zh;

    @Schema(description = "*收款人名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "*收款人名称不能为空")
    private String hm;

    @Schema(description = "*收方开户支行")
    private String yhmc;

    @Schema(description = "收方联行号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String sflhh;

    @Schema(description = "收款人开户地")
    private byte[] skrkhd;

    @Schema(description = "收方邮件地址")
    private byte[] sfyjdz;

    @Schema(description = "收方移动电话")
    private byte[] sfyddh;

    @Schema(description = "币种")
    private String rmb;

    @Schema(description = "付款分行")
    private byte[] fkfh;

    @Schema(description = "*结算方式")
    private String jsfs;

    @Schema(description = "业务种类")
    private byte[] ywzl;

    @Schema(description = "*付方账号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "*付方账号不能为空")
    private String ffzh;

    @Schema(description = "付方记账子单元编号")
    private String ffjzzdy;

    @Schema(description = "期望日")
    private String qwr;

    @Schema(description = "期望时间")
    private String qwsj;

    @Schema(description = "*用途")
    private String yt;

    @Schema(description = "*金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "*金额不能为空")
    private BigDecimal je;

    @Schema(description = "部门", example = "10745")
    private String deptId;

    @Schema(description = "内部附言(摘要)")
    private String fy;

    @Schema(description = "划款批次号")
    private String hkpch;

    @Schema(description = "拨付汇总批次号")
    private String bfhzpch;

    @Schema(description = "拨付批次号")
    private String bfpch;

    @Schema(description = "分成类型")
    private String fclx;

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "序号不能为空")
    private Integer xh;

    @Schema(description = "uuid序列号", example = "7315")
    private String uuid;

    @Schema(description = "拨付类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "拨付类型不能为空")
    private String bflx;

    @Schema(description = "拨付状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "拨付状态不能为空")
    private String bfzt;

    @Schema(description = "处理结果")
    private String bfjg;

    @Schema(description = "退回日期")
    private String thrq;

    @Schema(description = "备注")
    private String bz;

    @Schema(description = "生成汇总信息")
    private String schzxx;

    @Schema(description = "有效标记", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "有效标记不能为空")
    private String yxbj;

    @Schema(description = "原始退回标记")
    private String ysthbj;

    @Schema(description = "原始退回日期")
    private LocalDateTime ysthrq;

    @Schema(description = "原始退回原因")
    private String ysthyy;

    @Schema(description = "原始创建人")
    private String yscjr;

    @Schema(description = "原始创建时间")
    private LocalDateTime yscjsj;

    @Schema(description = "ysxgr")
    private String ysxgr;

    @Schema(description = "原始修改时间")
    private LocalDateTime ysxgsj;

    @Schema(description = "创建人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人不能为空")
    private String createBy;

    @Schema(description = "修改人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "修改人不能为空")
    private String updateBy;

    private String beginUpdateTime;
    private  String endUpdateTime;
}