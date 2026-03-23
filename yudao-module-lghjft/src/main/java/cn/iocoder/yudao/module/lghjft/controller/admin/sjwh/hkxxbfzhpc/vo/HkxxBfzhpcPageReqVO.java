package cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.hkxxbfzhpc.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 拨付信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class HkxxBfzhpcPageReqVO extends PageParam {

    @Schema(description = "登记序号", example = "123456")
    private String djxh;

    @Schema(description = "审批UUID", example = "abc123")
    private String spuuid;

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

    @Schema(description = "账户", example = "622202123456789")
    private String zh;

    @Schema(description = "户名", example = "张三")
    private String hm;

    @Schema(description = "行号", example = "102100099996")
    private String hh;

    @Schema(description = "状态", example = "1")
    private String zt;

    @Schema(description = "备注", example = "测试备注")
    private String bz;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "查询类型（1-账户排除 2-单位排除 3-经费排除）", example = "1")
    private Integer queryType;

    private  String skssqq;

    private  String skssqz;

    private  String beginRkrq;

    private  String endRkrq;

}