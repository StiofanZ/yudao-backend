package cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.util.Date;

@Schema(description = "管理后台 - 小微企业管理分页 Request VO")
@Data
public class XwqyglPageReqVO {

    @Schema(description = "页码，从 1 开始")
    @Min(value = 1, message = "页码最小值为 1")
    private Integer pageNo = 1;

    @Schema(description = "每页条数")
    @Min(value = 1, message = "每页条数最小值为 1")
    @Max(value = 100, message = "每页条数最大值为 100")
    private Integer pageSize = 10;

    @Schema(description = "分页偏移量")
    private Integer offset;

    @Schema(description = "分页限制")
    private Integer limit;

    private String deptId;
    private String hyghbz;
    private String shxydm;
    private String nsrmc;
    private String nsrjc;
    private String zgswjDm;
    private String zgswjmc;
    private String zgswskfjDm;
    private String zgswskfjmc;
    private String ssglyDm;
    private String ssglyxm;
    private String zzjglxDm;
    private String zzjglxmc;
    private String hyDm;
    private String hymc;
    private String djzclxDm;
    private String djzclxmc;
    private String dwlsgxDm;
    private String dwlsgxmc;
    private Integer zgrs;
    private String nsrztDm;
    private String nsrztmc;
    private Date fzcrq;
    private Date zxrq;
    private String zcdz;
    private String yzbm;
    private String lxr;
    private String lxdh;
    private String ghlbDm;
    private String xtlbDm;
    private String hjfl1Dm;
    private String hjfl2Dm;
    private String hjfl3Dm;
    private String hjfl4Dm;
    private String hjfl5Dm;
    private String hjfl6Dm;
    private String hjfl7Dm;
    private String dclghbj;
    private String hjfl9Dm;
    private String sdghjgDm;
    private String clghbj;
    private Date beginClghrq;
    private Date endClghrq;
    private String jcghdm;
    private String jcghmc;
    private String jcghzh;
    private String jcghhm;
    private String jcghhh;
    private String jcghyh;
    private String bz;
    private String jym;
    private String nsrsbh;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
}
