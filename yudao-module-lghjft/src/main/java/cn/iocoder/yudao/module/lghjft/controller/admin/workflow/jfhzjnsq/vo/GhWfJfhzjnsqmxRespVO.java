package cn.iocoder.yudao.module.lghjft.controller.admin.workflow.jfhzjnsq.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 工会经费汇总缴纳申请明细表（分支机构）返回 VO")
@Data
public class GhWfJfhzjnsqmxRespVO {
    @Schema(description = "明细表主键ID", example = "20160")
    private Long id;

    @Schema(description = "关联汇总表主键ID（外键）", example = "3050")
    private Long jfhzjnsqId;

    @Schema(description = "分支机构社会信用代码", example = "91370200MA3TGYQY5S")
    private String shxydm;

    @Schema(description = "分支机构单位全称", example = "XX集团一分公司")
    private String dwmc;

    @Schema(description = "分支机构主管税务部门", example = "XX区税务局")
    private String zgsbm;

    @Schema(description = "分支机构职工人数", example = "200")
    private Integer zgzs;

    @Schema(description = "分支机构月工资总额", example = "1000000.00")
    private BigDecimal ygzze;

}