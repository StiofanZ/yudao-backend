package cn.iocoder.yudao.module.lghjft.dal.dataobject.nsrxx;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@TableName("dj_nsrckzhzhxx")
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NsrckzhzhxxDO {

    /**
     * 存款账号UUID
     */
    private String CKZHUUID;

    /**
     * 登记序号（关联 dj_nsrxx 表的 DJXH）
     */
    private String DJXH;

    /**
     * 账户名称（工会账户户名）
     */
    private String ZHMC;

    /**
     * 银行行别代码（关联字典表查开户行名称）
     */
    @TableField("YHHB_DM")
    private String YHHBDM;

    /**
     * 银行账号（工会账户账号）
     */
    private String YHZH;

    /**
     * 开户行行号（网点代码/联行号）
     */
    @TableField("KHYHHH")
    private String KHYHHH;

    /**
     * 有效标志（Y=有效，N=无效）
     */
    @TableField("YXBZ")
    private String YXBZ;

    /**
     * 首选缴费账户标志（Y=是）
     */
    @TableField("SXJFZHBZ")
    private String SXJFZHBZ;

    /**
     * 社保费账户标志（Y=是）
     */
    @TableField("SBFZHBZ")
    private String SBFZHBZ;

    /**
     * 银行营业网点代码
     */
    @TableField("YHYYWD_DM")
    private String YHYYWDDM;
}
