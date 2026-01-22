package cn.iocoder.yudao.module.lghjft.controller.admin.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 登录 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorizeResVO {

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long userId;

    @Schema(description = "用户账号", example = "tom")
    private String yhzh;

    @Schema(description = "用户昵称", example = "张三")
    private String yhnc;

    @Schema(description = "用户邮箱", example = "admin@admin.com")
    private String yhyx;

    @Schema(description = "用户头像", example = "http://test.yudao.iocoder.cn/20251216/blob_1765819846641.png")
    private String txdz;

    @Schema(description = "访问令牌", requiredMode = Schema.RequiredMode.REQUIRED, example = "eb...")
    private String accessToken;

    @Schema(description = "刷新令牌", requiredMode = Schema.RequiredMode.REQUIRED, example = "eb...")
    private String refreshToken;

    @Schema(description = "过期时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime expiresTime;
    @Schema(description = "登录账号，登录验证成功时所使用的账号信息", requiredMode = Schema.RequiredMode.REQUIRED)
    private String dlzh;
    @Schema(description = "登录类型", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer loginType;
    @Schema(description = "权限部门编号")
    private Long qxbmId;
    @Schema(description = "权限部门名称")
    private String qxbmMc;
    @Schema(description = "上级权限部门编号")
    private Long sjQxbmId;
    @Schema(description = "上级权限部门名称")
    private String sjQxbmMc;
    @Schema(description = "单位权限身份")
    private List<DwQxSf> dwQxSf;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DwQxSf {
        @Schema(description = "单位名称", requiredMode = Schema.RequiredMode.REQUIRED)
        private String dwmc;
        @Schema(description = "登记序号", requiredMode = Schema.RequiredMode.REQUIRED)
        private String djxh;
        @Schema(description = "人员姓名", requiredMode = Schema.RequiredMode.REQUIRED)
        private String ryxm;
        @Schema(description = "联系电话", requiredMode = Schema.RequiredMode.REQUIRED)
        private String lxdh;
        @Schema(description = "身份类型", requiredMode = Schema.RequiredMode.REQUIRED)
        private String sflx;
        @Schema(description = "权限类型", requiredMode = Schema.RequiredMode.REQUIRED)
        private String qxlx;
        @Schema(description = "主管工会编号", requiredMode = Schema.RequiredMode.REQUIRED)
        private Long zgghId;
        @Schema(description = "主管工会名称", requiredMode = Schema.RequiredMode.REQUIRED)
        private String zgghMc;
        @Schema(description = "上级主管工会编号", requiredMode = Schema.RequiredMode.REQUIRED)
        private Long sjZgghId;
        @Schema(description = "上级主管工会名称", requiredMode = Schema.RequiredMode.REQUIRED)
        private String sjZgghMc;
        @Schema(description = "工会账户", requiredMode = Schema.RequiredMode.REQUIRED)
        private String ghzh;
        @Schema(description = "工会户名", requiredMode = Schema.RequiredMode.REQUIRED)
        private String ghhm;
        @Schema(description = "工会行号", requiredMode = Schema.RequiredMode.REQUIRED)
        private String ghhh;
        @Schema(description = "工会银行", requiredMode = Schema.RequiredMode.REQUIRED)
        private String ghyh;
    }
}
