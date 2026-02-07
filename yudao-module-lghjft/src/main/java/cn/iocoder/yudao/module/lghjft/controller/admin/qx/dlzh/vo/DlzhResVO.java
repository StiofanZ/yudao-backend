package cn.iocoder.yudao.module.lghjft.controller.admin.qx.dlzh.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 登录账号 Response VO")
@Data
public class DlzhResVO {

    @Schema(description = "ID", example = "1024")
    private Long id;

    @Schema(description = "用户账号", example = "zhangsan")
    private String yhzh;

    @Schema(description = "用户姓名", example = "张三")
    private String yhxm;

    @Schema(description = "用户备注", example = "备注信息")
    private String yhbz;

    @Schema(description = "联系电话", example = "13800000000")
    private String lxdh;

    @Schema(description = "用户邮箱", example = "a@b.com")
    private String yhyx;

    @Schema(description = "社会信用代码", example = "91320101MA1XXXXXXX")
    private String shxydm;

    @Schema(description = "用户性别", example = "1")
    private Integer yhxb;

    @Schema(description = "头像地址", example = "https://xxx/avatar.png")
    private String txdz;

    @Schema(description = "帐号状态（0正常 1停用）", example = "0")
    private Integer status;

    @Schema(description = "最后登录IP", example = "127.0.0.1")
    private String loginIp;

    @Schema(description = "最后登录时间")
    private LocalDateTime loginDate;

    @Schema(description = "创建者", example = "1")
    private String creator;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新者", example = "1")
    private String updater;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}
