package cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfmx.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 银行拨付明细分页 Request VO")
@Data
public class YhbfmxPageReqVO extends PageParam {

    @Schema(description = "收款人编号", example = "7802")
    private Long hkxxId;

    @Schema(description = "*收款人账号")
    private String zh;

    @Schema(description = "*收款人名称")
    private String hm;

    @Schema(description = "*收方开户支行")
    private String yhmc;

    @Schema(description = "收款人开户地")
    private byte[] skrkhd;

    @Schema(description = "收方邮件地址")
    private byte[] sfyjdz;

    @Schema(description = "收方移动电话")
    private byte[] sfyddh;

    @Schema(description = "币种")
    private String rmb;

    @Schema(description = "付款分行")
    private byte[] fkfh;

    @Schema(description = "*结算方式")
    private String jsfs;

    @Schema(description = "业务种类")
    private byte[] ywzl;

    @Schema(description = "*付方账号")
    private String ffzh;

    @Schema(description = "付方记账子单元编号")
    private String ffjzzdy;

    @Schema(description = "期望日")
    private String qwr;

    @Schema(description = "期望时间")
    private String qwsj;

    @Schema(description = "*用途")
    private String yt;

    @Schema(description = "*金额")
    private BigDecimal je;

    @Schema(description = "部门", example = "10745")
    private String deptId;

    @Schema(description = "内部附言(摘要)")
    private String fy;

    @Schema(description = "划款批次号")
    private String hkpch;

    @Schema(description = "拨付汇总批次号")
    private String bfhzpch;

    @Schema(description = "拨付批次号")
    private String bfpch;

    @Schema(description = "分成类型")
    private String fclx;

    @Schema(description = "序号")
    private Integer xh;

    @Schema(description = "uuid序列号", example = "7315")
    private String uuid;

    @Schema(description = "拨付类型")
    private String bflx;

    @Schema(description = "拨付状态")
    private String bfzt;

    @Schema(description = "处理结果")
    private String bfjg;

    @Schema(description = "退回日期")
    private String thrq;

    @Schema(description = "备注")
    private String bz;

    @Schema(description = "生成汇总信息")
    private String schzxx;

    @Schema(description = "有效标记")
    private String yxbj;

    @Schema(description = "原始退回标记")
    private String ysthbj;

    @Schema(description = "原始退回日期")
    private LocalDateTime ysthrq;

    @Schema(description = "原始退回原因")
    private String ysthyy;

    @Schema(description = "原始创建人")
    private String yscjr;

    @Schema(description = "原始创建时间")
    private LocalDateTime yscjsj;

    @Schema(description = "ysxgr")
    private String ysxgr;

    @Schema(description = "原始修改时间")
    private LocalDateTime ysxgsj;

    @Schema(description = "创建人")
    private String createBy;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "修改人")
    private String updateBy;

    @Schema(description = "收方联行号")
    private  String sflhh;

//    beginThrq, endThrq      →  结果同步日期
    private  String beginThrq;
    private  String endThrq;
//    beginYsthrq, endYsthrq  →  原始退回日期
    private  String beginYsthrq;
    private  String endYsthrq;
//    beginYscjsj, endYscjsj  →  原始创建时间
    private  String beginYscjsj;
    private  String endYscjsj;

    private String beginYsxgsj;
    private String endYsxgsj;

//    beginCreateTime, endCreateTime  →  创建时间
    private  String beginCreateTime;
    private  String endCreateTime;
//req.beginUpdateTime/req.endUpdateTime
    private String beginUpdateTime;
    private String endUpdateTime;

}