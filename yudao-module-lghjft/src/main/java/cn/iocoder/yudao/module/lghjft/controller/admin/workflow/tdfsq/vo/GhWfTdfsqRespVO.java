package cn.iocoder.yudao.module.lghjft.controller.admin.workflow.tdfsq.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Schema(description = "管理后台 - 退抵费申请 Response VO")
public class GhWfTdfsqRespVO {

    private Long id;
    private String shxydm;
    private String dwmc;
    private String qksm;
    private String dwfzr;
    private String jbr;
    private String lxdh;
    private String hm;
    private String khyhmc;
    private String zh;
    private String khyhhh;
    private LocalDate sqrq;
    private String zgghspyj;
    private String zgghfzr;
    private String zgghjbr;
    private LocalDate zgghsprq;
    private String sghspyj;
    private String sghfzr;
    private String sghjbr;
    private LocalDate sghsprq;
    private Integer thfs;
    private List<FjItem> fjList;

    @Data
    @Schema(description = "附件项")
    public static class FjItem {
        private String fjlx;
        private String wjlj;
        private String wjmc;
        private String ywjmc;
    }
    // ====================== 新增：退费类型 ======================
    private Integer sqtflxDm;

    // ====================== 新增：退费明细列表 ======================
    private List<TdfsqMxItem> mxList;

    @Data
    @Schema(description = "退费明细项")
    public static class TdfsqMxItem {
        private Long id;
        private String spuuid;
        private BigDecimal rkje;
        private BigDecimal tfsqJe;
        private String shxydm;
        private String nsrmc;
        private LocalDate skssqq;
        private LocalDate skssqz;
    }

}
