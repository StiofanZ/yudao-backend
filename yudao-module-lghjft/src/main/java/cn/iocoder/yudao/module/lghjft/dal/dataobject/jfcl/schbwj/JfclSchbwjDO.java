package cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.schbwj;

import cn.idev.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 经费处理-生成划拨文件 DO (gh_hkxx 表)
 * v1 selecList: SELECT T.HKXX_ID,T.HKPCH,T.ZH,T.HM,T.HH,T.JE,t.DZ,t.FY,T.CREATE_TIME,
 *               U.USER_NAME AS CREATE_BY FROM gh_hkxx T LEFT JOIN sys_user U ON U.user_id = T.CREATE_BY
 * Does NOT extend BaseDO, no @TableLogic (v1 mapping rules).
 */
@TableName("gh_hkxx")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JfclSchbwjDO {

    @TableId(value = "HKXX_ID")
    private Long hkxxId;

    private String hkpch;

    private Long xh;

    private String lx;

    @ExcelProperty("收款人账号(必填)")
    private String zh;

    @ExcelProperty("收款人名称(必填)")
    private String hm;

    @ExcelProperty("收款人开户行行号(必填)")
    private String hh;

    @ExcelProperty("金额(必填)")
    private BigDecimal je;

    private String deptId;

    @ExcelProperty("收款人地址(可选)")
    private String dz;

    @ExcelProperty("附言(可选)")
    private String fy;

    private String jym;

    private String thbj;

    private Date thrq;

    private String thyy;

    private String schkpch;

    private String scbz;

    private String yxbj;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String bz;
}
