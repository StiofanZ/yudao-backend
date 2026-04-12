package cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.ghjfcbjqf.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 筹备金全返新增/修改 Request VO")
@Data
public class GhjfcbjqfSaveReqVO {

    @Schema(description = "工会经费ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long ghjfId;

    @Schema(description = "筹备金全返状态")
    private String cbjqfzt;

    @Schema(description = "筹备金全返日期")
    private String cbjqfrq;

    @Schema(description = "筹备金全返结果")
    private String cbjqfjg;

    @Schema(description = "筹备金结算拨付基层状态")
    private String cbjjsbfjczt;

    @Schema(description = "筹备金结算拨付基层日期")
    private String cbjjsbfjcrq;

    @Schema(description = "划款批次号")
    private String hkpch;

    @Schema(description = "全返批次号")
    private String qfpch;

    @Schema(description = "备注")
    private String bz;

    @Schema(description = "筹备金特殊经费子表")
    private List<GhJfCbjtsjfItem> ghJfCbjtsjfList;

    @Data
    public static class GhJfCbjtsjfItem {
        private Long ghjfId;
        private String spuuid;
        private String tsjfbj;
        private String tsjfsm;
        private String clsj;
        private String tsjfwj;
        private String tsjftp;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
    }
}
