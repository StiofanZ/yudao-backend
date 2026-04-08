package cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.szqzjzdc;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * 省总做账导出 DO
 *
 * 映射 v1 表 szqzjzdc，禁止继承 BaseDO，禁止 @TableField(exist = false)
 */
@TableName("szqzjzdc")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SzqzjzdcDO {

    @TableId(type = IdType.INPUT)
    private String pzbh;
    private String flbh;
    private String kjn;
    private String kjqj;
    private String pzlx;
    private String pzrq;
    private String kjtx;
    private String zy;
    private String kjkmm;
    private String jfje;
    private BigDecimal dfje;
    private String fjzs;
    private String ysdh;
    private String zt;
    private String zdr;
    private String shr;
    private String jzr;
    private String zfcbwz;
    private String zbtx;
    private String ysly;
    private String wldw;
    private String bm;
    private String ry;
    private String bmysjjfl;
    private String zfgnfl;
    private String zclx;
    private String jfly;
    private String zjxz;
    private String zfysjjfl;
    private String mxkm;
    private String zffs;
    private String xm;
    private String jsfs;
    private String xjgh;
    private String xmhd;
    private String ggjcss;
    private String jfsl;
    private String dfsl;
    private String dj;
}
