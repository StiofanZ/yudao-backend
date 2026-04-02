package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.czrj.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 历史日志新增/修改 Request VO")
@Data
public class QxCzrjSaveReqVO {

    private Long czrjid;
    private String czydm;
    private String czyxm;
    private LocalDateTime czsj;
    private String czms;
    private String cznr;
}
