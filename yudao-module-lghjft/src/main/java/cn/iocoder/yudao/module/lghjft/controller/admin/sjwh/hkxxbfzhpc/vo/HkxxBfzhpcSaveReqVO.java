package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.hkxxbfzhpc.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 拨付信息创建/更新 Request VO")
@Data
public class HkxxBfzhpcSaveReqVO {

    @Schema(description = "账户排除ID", example = "1024")
    private Long zhpcid;

    @Schema(description = "审批UUID", example = "abc123")
    private String spuuid;

    @Schema(description = "登记序号", requiredMode = Schema.RequiredMode.REQUIRED, example = "123456")
    @NotEmpty(message = "登记序号不能为空")
    private String djxh;

    @Schema(description = "账户", example = "622202123456789")
    private String zh;

    @Schema(description = "户名", example = "张三")
    private String hm;

    @Schema(description = "行号", example = "102100099996")
    private String hh;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态不能为空")
    private String zt;

    @Schema(description = "备注", example = "测试备注")
    private String bz;

    // ========== 扩展字段（用于查询展示）==========

    @Schema(description = "部门ID", example = "100")
    private String deptId;

    @Schema(description = "统一社会信用代码", example = "91110108772551611Y")
    private String shxydm;

    @Schema(description = "纳税人名称", example = "北京某某公司")
    private String nsrmc;

    @Schema(description = "基本存款账户账号", example = "622202123456789")
    private String jcghzh;

    @Schema(description = "基本存款账户户名", example = "张三")
    private String jcghhm;

    @Schema(description = "基本存款账户行号", example = "102100099996")
    private String jcghhh;

    @Schema(description = "基本存款账户银行", example = "中国工商银行")
    private String jcghyh;

    @Schema(description = "校验码", example = "123456")
    private String jym;

    // ========== 经费相关字段 ==========

    @Schema(description = "入库日期", example = "2024-01-15")
    private String rkrq;

    @Schema(description = "应补退税额", example = "10000.00")
    private String ybtse;

    @Schema(description = "税款所属期起", example = "2024-01-01")
    private String skssqq;

    @Schema(description = "税款所属期止", example = "2024-01-31")
    private String skssqz;
}