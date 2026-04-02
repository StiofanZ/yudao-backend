package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fydsqk.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 分月代收情况新增/修改 Request VO")
@Data
public class FydsqkSaveReqVO {

    private String deptId;
    private LocalDateTime rkrq;
    private String dsyf;
    private String rkjf;
    private String ghjf;
    private String cbj;
    private String znj;
}
