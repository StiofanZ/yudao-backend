package cn.iocoder.yudao.module.lghjft.controller.app.workflow.wfsqhzjf.vo;

import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfsqhzjf.vo.WfHzSaveReqVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "App - 工会经费汇总缴纳申请表（主表）新增/修改 Request VO")
@Data
public class WfHzSaveAppReqVO extends WfHzSaveReqVO {

}