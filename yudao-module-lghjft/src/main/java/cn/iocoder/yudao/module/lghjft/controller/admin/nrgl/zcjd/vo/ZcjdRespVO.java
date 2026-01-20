package cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcjd.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 政策解读 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ZcjdRespVO extends ZcjdBaseVO {

    /**
     * 编号
     */
    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    /**
     * 父编号
     */
    @Schema(description = "父编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long parentId;

    /**
     * 标题
     */
    @Schema(description = "标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private String title;

    /**
     * 内容
     */
    @Schema(description = "内容", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private String content;

    /**
     * 排序
     */
    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Integer sort;

    /**
     * 状态
     * 0: 未审核, 1: 已审核, 2: 已发布, 3: 已过期, 4: 已下架
     */
    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Integer status;

    /**
     * 发布部门编号
     */
    @Schema(description = "发布部门编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long deptId;

    private String deptName;

    /**
     * 可见范围
     * 1: 完全可见, 2: 下级可见, 3: 本级可见
     */
    @Schema(description = "可见范围", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Integer kjfw;

    /**
     * 附件路径
     */
    @Schema(description = "附件路径", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private String fjlj;

    /**
     * 发布部门(0:全总,1:省总,2:市州)
     */
    @Schema(description = "发布部门(0:全总,1:省总,2:市州)", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Integer fbbm;

    /**
     * 下架原因(1:已失效政策,2:新政策替代)
     */
    @Schema(description = "下架原因(1:已失效政策,2:新政策替代)", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private String xjyy;

    /**
     * 原文件发布日期
     */
    @Schema(description = "原文件发布日期", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private java.time.LocalDateTime fbrq;

    /**
     * 关联政策ID
     */
    @Schema(description = "关联政策ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long glzcId;

}
