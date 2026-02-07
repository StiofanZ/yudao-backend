package cn.iocoder.yudao.module.lghjft.dal.dataobject.nsrxx;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.math.BigInteger;

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

}
