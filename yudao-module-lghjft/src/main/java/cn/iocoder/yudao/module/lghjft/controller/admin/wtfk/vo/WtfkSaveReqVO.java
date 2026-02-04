package cn.iocoder.yudao.module.lghjft.controller.admin.wtfk.vo;

import cn.iocoder.yudao.module.file.dal.dataobject.vo.FileInfoVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import cn.iocoder.yudao.framework.common.validation.Mobile;

@Schema(description = "管理后台 - 工会经费通-问题反馈新增/修改 Request VO")
@Data
public class WtfkSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16018")
    private Long id;


    @Schema(description = "反馈类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "反馈类型")
    private String type;

    @Schema(description = "平台名称")
    private String platformName;

    @Schema(description = "反馈内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "反馈内容不能为空")
    private String content;

    @Mobile
    @Schema(description = "联系电话")
    private String contactPhone;

    @Schema(description = "联系邮箱")
    private String contactEmail;

    @Schema(description = "处理状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "处理状态")
    private Integer status;

    @Schema(description = "处理人ID", example = "3038")
    private Long processorId;

    @Schema(description = "处理时间")
    private LocalDateTime processTime;

    @Schema(description = "附件列表")
    private List<FileInfoVO> files;


}