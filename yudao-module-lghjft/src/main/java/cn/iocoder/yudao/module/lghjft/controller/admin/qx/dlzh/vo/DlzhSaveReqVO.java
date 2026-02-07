package cn.iocoder.yudao.module.lghjft.controller.admin.qx.dlzh.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Schema(description = "管理后台 - 登录账号新增/修改 Request VO")
@Data
public class DlzhSaveReqVO {

    @Schema(description = "ID", example = "1024")
    private Long id;

    @Schema(description = "用户账号", example = "zhangsan")
    @Length(max = 30, message = "用户账号长度不能超过30个字符")
    private String yhzh;

    @Schema(description = "密码（新增必填，修改可为空表示不修改）", example = "123456")
    @Length(max = 100, message = "密码长度不能超过100个字符")
    private String password;

    @Schema(description = "用户姓名", example = "张三")
    @Length(max = 30, message = "用户姓名长度不能超过30个字符")
    private String yhxm;

    @Schema(description = "用户备注", example = "备注信息")
    @Length(max = 500, message = "用户备注长度不能超过500个字符")
    private String yhbz;

    @Schema(description = "联系电话", example = "13800000000")
    @Length(max = 11, message = "联系电话长度不能超过11个字符")
    private String lxdh;

    @Schema(description = "用户邮箱", example = "a@b.com")
    @Length(max = 50, message = "用户邮箱长度不能超过50个字符")
    private String yhyx;

    @Schema(description = "社会信用代码", example = "91320101MA1XXXXXXX")
    @Length(max = 20, message = "社会信用代码长度不能超过20个字符")
    private String shxydm;

    @Schema(description = "用户性别", example = "1")
    private Integer yhxb;

    @Schema(description = "头像地址", example = "https://xxx/avatar.png")
    @Length(max = 200, message = "头像地址长度不能超过200个字符")
    private String txdz;

    @Schema(description = "帐号状态（0正常 1停用）", example = "0")
    private Integer status;

}
