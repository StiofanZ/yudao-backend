package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.yjhxx.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "管理后台 - 已建会信息新增/修改 Request VO")
@Data
public class GhYjhxxSaveReqVO {

    private Long jhxxId;
    private String nsrmc;
    private String shxydm;
    private String nsrsbh;
    private String yxbj;
}
