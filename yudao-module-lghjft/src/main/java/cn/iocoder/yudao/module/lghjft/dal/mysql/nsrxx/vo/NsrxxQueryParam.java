package cn.iocoder.yudao.module.lghjft.dal.mysql.nsrxx.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;

/**
 * 纳税人信息查询参数对象
 * 用于封装 NsrxxMapper.getNsrxxList 方法的查询条件
 */
@Data
@Accessors(chain = true)
public class NsrxxQueryParam {

    /**
     * 关键字（匹配纳税人识别号、社会信用代码、纳税人名称）
     */
    private String keyword;

    /**
     * 纳税人识别号（精确匹配）
     */
    private String nsrsbh;

    /**
     * 社会信用代码（精确匹配）
     */
    private String shxydm;

    /**
     * 纳税人名称（模糊匹配）
     */
    private String nsrmc;

    /**
     * 登记序号列表（批量查询）
     */
    private Collection<String> djxhList;

    /**
     * 是否仅查询有效状态（nsrztDm < '07'）
     * 默认为 true
     */
    private boolean onlyValid = true;

}
