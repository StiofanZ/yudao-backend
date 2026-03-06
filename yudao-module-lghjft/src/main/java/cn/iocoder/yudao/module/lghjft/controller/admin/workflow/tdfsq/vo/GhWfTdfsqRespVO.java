package cn.iocoder.yudao.module.lghjft.controller.admin.workflow.tdfsq.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

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
}
