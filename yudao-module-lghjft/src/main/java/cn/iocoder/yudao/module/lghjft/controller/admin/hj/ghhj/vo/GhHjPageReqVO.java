package cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 户籍管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GhHjPageReqVO extends PageParam {

    @Schema(description = "登记序号")
    private String djxh;

    @Schema(description = "部门ID")
    private String deptId;

    @Schema(description = "行业工会标志")
    private String hyghbz;

    @Schema(description = "社会信用代码")
    private String shxydm;

    @Schema(description = "纳税人识别号")
    private String nsrsbh;

    @Schema(description = "纳税人名称")
    private String nsrmc;

    @Schema(description = "街道乡镇代码")
    private String jdxzDm;

    @Schema(description = "主管税务机关名称")
    private String zgswjmc;

    @Schema(description = "科所分局代码")
    private String zgswskfjDm;

    @Schema(description = "科所分局名称")
    private String zgswskfjmc;

    @Schema(description = "税管员姓名")
    private String ssglyxm;

    @Schema(description = "行业名称")
    private String hymc;

    @Schema(description = "注册地址")
    private String zcdz;

    @Schema(description = "联系人")
    private String lxr;

    @Schema(description = "联系电话")
    private String lxdh;

    @Schema(description = "成立工会标志")
    private String clghbj;

    @Schema(description = "成立工会日期开始")
    private String beginClghrq;

    @Schema(description = "成立工会日期结束")
    private String endClghrq;

    @Schema(description = "工会类别代码")
    private String ghlbDm;

    @Schema(description = "系统类别代码")
    private String xtlbDm;

    @Schema(description = "基层工会名称")
    private String jcghmc;

    @Schema(description = "基层工会账号")
    private String jcghzh;

    @Schema(description = "基层工会户名")
    private String jcghhm;

    @Schema(description = "基层工会行号")
    private String jcghhh;

    @Schema(description = "基层工会银行")
    private String jcghyh;

    @Schema(description = "建会缴纳筹备金标志")
    private String hjfl8Dm;

    @Schema(description = "户籍分类9")
    private String hjfl9Dm;
}
