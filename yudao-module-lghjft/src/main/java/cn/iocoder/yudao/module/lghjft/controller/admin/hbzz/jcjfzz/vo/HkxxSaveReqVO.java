package cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jcjfzz.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Schema(description = "管理后台 - 基层经费到账对象新增/修改 Request VO")
@Data
public class HkxxSaveReqVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "10568")
    private Integer hkxxId;

    @Schema(description = "划款批次号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String hkpch;

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long xh;

    @Schema(description = "类型", requiredMode = Schema.RequiredMode.REQUIRED)
    private String lx;

    @Schema(description = "账号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String zh;

    @Schema(description = "户名", requiredMode = Schema.RequiredMode.REQUIRED)
    private String hm;

    @Schema(description = "行号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String hh;

    @Schema(description = "正确账号")
    private String xzh;

    @Schema(description = "正确户名")
    private String xhm;

    @Schema(description = "正确行号")
    private String xhh;

    @Schema(description = "金额", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal je;

    @Schema(description = "工会机构", example = "20655")
    private String deptId;

    @Schema(description = "地址")
    private String dz;

    @Schema(description = "附言")
    private String fy;

    @Schema(description = "校验码")
    private String jym;

    @Schema(description = "退回标志", requiredMode = Schema.RequiredMode.REQUIRED)
    private String thbj;

    @Schema(description = "退回日期")
    private Date thrq;

    @Schema(description = "退回原因")
    private String thyy;

    @Schema(description = "生成划款批次号")
    private String schkpch;

    @Schema(description = "备注信息")
    private String bz;

    @Schema(description = "修改标志", requiredMode = Schema.RequiredMode.REQUIRED)

    private String xgbj;

    @Schema(description = "系统生成标志", requiredMode = Schema.RequiredMode.REQUIRED)
    private String scbz;

    @Schema(description = "有效标志", requiredMode = Schema.RequiredMode.REQUIRED)
    private String yxbj;

    @Schema(description = "修改人")
    private String xgr;

    @Schema(description = "修改时间")
    private Date xgsj;

    @Schema(description = "创建人")
    private String createBy;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改人")
    private String updateBy;

    @Schema(description = "修改时间")
    private Date updateTime;

    @Schema(description = "到账标记")
    private String dzbj;

    @Schema(description = "确认日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date qrrq;

    @Schema(description = "银行回单号")
    private String yhhdh;

    @Schema(description = "校验码")
    private String ghHkxxJym;
    // ==================== 内嵌子表：到账确认列表 ====================
    @Schema(description = "到账确认列表（子表）")
    private List<JcjfdzItem> jcjfdzList;

    // ==================== 子表 VO（内部类） ====================
    @Data
    @Schema(description = "基层经费到账确认子表")
    public static class JcjfdzItem {

        @Schema(description = "主键ID")
        private Integer id;

        @Schema(description = "校验码")
        private String ghHkxxJym;

        @Schema(description = "划款信息ID", requiredMode = Schema.RequiredMode.REQUIRED)
        private Integer hkxxId;

        @Schema(description = "到账标记")
        private String dzbj;

        @Schema(description = "确认日期")
        @JsonFormat(pattern = "yyyy-MM-dd")
        private Date qrrq;

        @Schema(description = "银行回单号")
        private String yhhdh;

        @Schema(description = "备注")
        private String bz;
    }
}