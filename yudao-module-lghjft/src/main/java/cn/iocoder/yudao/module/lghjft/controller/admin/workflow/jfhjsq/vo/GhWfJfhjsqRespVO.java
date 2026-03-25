package cn.iocoder.yudao.module.lghjft.controller.admin.workflow.jfhjsq.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.dwdbsq.vo.WfDbsqRespVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 经费缓缴申请 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GhWfJfhjsqRespVO {

    @ExcelProperty("主键ID")
    private Long id;
    @ExcelProperty("社会信用代码")
    private String shxydm;
    @ExcelProperty("单位名称")
    private String dwmc;
    @ExcelProperty("联系人")
    private String lxr;
    @ExcelProperty("联系电话")
    private String lxdh;
    @ExcelProperty("适用费率")
    private BigDecimal syfl;
    @ExcelProperty("职工总数")
    private Integer zgzs;
    @ExcelProperty("月工资总额")
    private BigDecimal ygzze;
    @ExcelProperty("月拨缴金额")
    private BigDecimal ybjje;
    private String hjkssj;
    private String hjjssj;
    @ExcelProperty("缓缴月数")
    private Integer hjzys;
    @ExcelProperty("累计缓缴金额")
    private BigDecimal ljhjje;
    @ExcelProperty("缓缴情况说明")
    private String hjqksm;
    @ExcelProperty("单位负责人")
    private String dwfzr;
    @ExcelProperty("经办人")
    private String jbr;
    @ExcelProperty("申请日期")
    private LocalDate sqrq;
    @ExcelProperty("基层工会意见")
    private String jcghyj;
    @ExcelProperty("基层工会负责人")
    private String jcghfzr;
    @ExcelProperty("基层工会经办人")
    private String jcghjbr;
    @ExcelProperty("基层工会盖章日期")
    private LocalDate jcghgzrq;
    @ExcelProperty("主管工会审核意见")
    private String zgghspyj;
    @ExcelProperty("主管工会负责人")
    private String zgghfzr;
    @ExcelProperty("主管工会经办人")
    private String zgghjbr;
    @ExcelProperty("主管工会审核日期")
    private LocalDate zgghsprq;
    @ExcelProperty("主管工会财务负责人")
    private String zgghcwfzr;
    @ExcelProperty("流程实例ID")
    private String lcslId;
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;
    @Schema(description = "附件列表")
    private List<FjItem> fjList;

    //    存放文件的子表
    @Data
    @Schema(description = "附件项")
    public static class FjItem {
        private String wjmc;
        /**
         * 文件访问地址
         */
        private String wjlj;
        /**
         * 文件类型（pdf/png/jpg/docx）
         */
        private String fjlx;

        private  String ywjmc;

    }
}
