package cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fyfcqk.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 分月分成情况新增/修改 Request VO")
@Data
public class FyfcqkSaveReqVO {

    private String deptId;
    private LocalDateTime rkrq;
    private String dsyf;
    private String rkjf;
    private String tkjf;
    private String ygfcjf;
    private String yfcjf;
    private String wfcjf;
    private String szfc;
    private String znj;
    private String qzfc;
    private String hj;
}
