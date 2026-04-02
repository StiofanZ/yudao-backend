package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.yjhxx.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "管理后台 - 已建会信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GhYjhxxResVO {

    @Schema(description = "jhxxId")
    private Long jhxxId;
    @Schema(description = "nsrmc")
    private String nsrmc;
    @Schema(description = "shxydm")
    private String shxydm;
    @Schema(description = "nsrsbh")
    private String nsrsbh;
    @Schema(description = "yxbj")
    private String yxbj;
}
