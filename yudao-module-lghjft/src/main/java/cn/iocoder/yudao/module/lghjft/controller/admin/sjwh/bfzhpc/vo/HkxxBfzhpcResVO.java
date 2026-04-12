package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.bfzhpc.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 拨付信息 Response VO")
@Data
public class HkxxBfzhpcResVO {

    @ExcelProperty("账户排除ID")
    @Schema(description = "账户排除ID", example = "1024")
    private Long zhpcid;

    @ExcelProperty("审批UUID")
    @Schema(description = "审批UUID", example = "abc123")
    private String spuuid;

    @ExcelProperty("登记序号")
    @Schema(description = "登记序号", example = "123456")
    private String djxh;

    @ExcelProperty(value = "工会机构代码", converter = DictConvert.class)
    @DictFormat("sys_ghjg_type")
    @Schema(description = "部门ID", example = "100")
    private String deptId;

    @ExcelProperty("社会信用代码")
    @Schema(description = "统一社会信用代码", example = "91110108772551611Y")
    private String shxydm;

    @ExcelProperty("纳税人名称")
    @Schema(description = "纳税人名称", example = "北京某某公司")
    private String nsrmc;

    @ExcelProperty("基层工会账户")
    @Schema(description = "基本存款账户账号", example = "622202123456789")
    private String jcghzh;

    @ExcelProperty("基层工会户名")
    @Schema(description = "基本存款账户户名", example = "张三")
    private String jcghhm;

    @ExcelProperty("基层工会行号")
    @Schema(description = "基本存款账户行号", example = "102100099996")
    private String jcghhh;

    @ExcelProperty("基层工会银行")
    @Schema(description = "基本存款账户银行", example = "中国工商银行")
    private String jcghyh;

    @ExcelProperty("校验码")
    @Schema(description = "校验码", example = "123456")
    private String jym;

    @ExcelProperty("账户")
    @Schema(description = "账户", example = "622202123456789")
    private String zh;

    @ExcelProperty("户名")
    @Schema(description = "户名", example = "张三")
    private String hm;

    @ExcelProperty("行号")
    @Schema(description = "行号", example = "102100099996")
    private String hh;

    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat("sys_bfzhpc")
    @Schema(description = "状态", example = "1")
    private String zt;

    @ExcelProperty("备注")
    @Schema(description = "备注", example = "测试备注")
    private String bz;

    @ExcelProperty("创建者")
    @Schema(description = "创建者", example = "admin")
    private String createBy;

    @ExcelProperty("创建时间")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("更新者")
    @Schema(description = "更新者", example = "admin")
    private String updateBy;

    @ExcelProperty("更新时间")
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    // ========== 经费相关字段 ==========

    @ExcelProperty("入库日期")
    @Schema(description = "入库日期", example = "2024-01-15")
    private String rkrq;

    @ExcelProperty("缴费金额")
    @Schema(description = "应补退税额", example = "10000.00")
    private String ybtse;

    @ExcelProperty("所属期起")
    @Schema(description = "税款所属期起", example = "2024-01-01")
    private String skssqq;

    @ExcelProperty("所属期止")
    @Schema(description = "税款所属期止", example = "2024-01-31")
    private String skssqz;

    // ========== 级联子表（详情查询用） ==========
    @ExcelProperty("账户排除解除子表列表")
    @Schema(description = "账户排除解除子表列表")
    private List<HkxxBfzhpcSaveReqVO.GhHkxxBfzhpcItem> ghHkxxBfzhpcList;
}
