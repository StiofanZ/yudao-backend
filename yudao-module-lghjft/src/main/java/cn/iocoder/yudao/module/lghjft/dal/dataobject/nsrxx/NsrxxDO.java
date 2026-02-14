package cn.iocoder.yudao.module.lghjft.dal.dataobject.nsrxx;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

/**
 * 纳税人信息 DO
 *
 * @author 芋道源码
 */
@TableName("dj_nsrxx")
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NsrxxDO {

    /**
     * 纳税人识别号
     */
    private String nsrsbh;
    /**
     * 登记序号
     */
    private String djxh;
    /**
     * 纳税人名称
     */
    private String nsrmc;
    /**
     * 社会信用代码
     */
    private String shxydm;
    private String kzztdjlxDm;
    private String nsrztDm;
    /**
     * 法定代表人姓名 (用于匹配身份类型)
     */
    private String fddbrxm;

    // Added fields
    private String zgswjDm;
    private String zgswskfjDm;
    private String ssglyDm;
    private String zzjgDm;
    private String hyDm;
    private String djzclxDm;
    private String dwlsgxDm;
    private String zcdz;
    private String jdxzDm;
    private Date djrq;
    private String gdslxDm;
    private String ssdabh;
    private String fddbrsfzjlxDm;
    private String scjydz;
    private String fddbrsfzjhm;
    private String scjydzxzqhszDm;
    private String zcdzxzqhszDm;
    private String gdghlxDm;
    private String djjgDm;
    private String kqccsztdjbz;
    private String lrrDm;
    private Date lrrq;
    private String xgrDm;
    private Date xgrq;
    private String sjgsdq;
    private String fjmqybz;
    private String swdjblbz;
    private Date sjtbSj;
    private String nsrbm;
    private String yxbz;
    private String pgjgDm;
    private Date gszxrq;
    private String nsrztlxDm;
    private Short sjblbz;
    private String myqybz;

}
