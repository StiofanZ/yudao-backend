package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bszn.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 办事指南 Base VO，提供给添加、修改、详情的子 VO 使用
 */
@Data
public class BsznBaseVO {

    @Schema(description = "父编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "父编号不能为空")
    private Long parentId;

    @Schema(description = "事项名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "办事指南")
    @NotBlank(message = "事项名称不能为空")
    private String sxmc;

    @Schema(description = "内容", example = "<p>HTML Content</p>")
    private String content;

    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "排序不能为空")
    private Integer sort;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态不能为空")
    private Integer status;

    @Schema(description = "可见范围", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "可见范围不能为空")
    private Integer kjfw;

    @Schema(description = "办理部门", example = "A大厅3号窗口")
    private String blbm;

    @Schema(description = "下架原因(1:已失效政策,2:新政策替代)", example = "1")
    private String xjyy;

    @Schema(description = "业务分类(1:缴费管理,2:返拨管理,3:退费管理,4:缓交管理)", example = "1")
    private Integer ywfl;

    @Schema(description = "办理主体(1:全总工会,2:省总工会,3:基层工会,4:缴费单位)", example = "1")
    private Integer blzt;

    @Schema(description = "发布时间", example = "2023-01-01")
    @org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.time.LocalDate fbsj;

    @Schema(description = "咨询电话", example = "010-12345678")
    private String zxdh;

    @Schema(description = "法定时限", example = "20个工作日")
    private String fdsx;

    @Schema(description = "承诺时限", example = "5个工作日")
    private String cnsx;

    @Schema(description = "收费标准", example = "免费")
    private String sfbz;

}
