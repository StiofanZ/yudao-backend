package cn.iocoder.yudao.module.lghjft.service.nsrxx;

import cn.iocoder.yudao.module.lghjft.dal.dataobject.nsrxx.NsrxxDO;

import java.util.Collection;
import java.util.List;

/**
 * 纳税人信息 Service 接口
 *
 * @author 芋道源码
 */
public interface NsrxxService {

    /**
     * 根据关键字查询纳税人信息列表
     *
     * @param keyword 纳税人识别号/名称/社会信用代码
     * @return 纳税人信息列表
     */
    List<NsrxxDO> getNsrxxList(String keyword);

    /**
     * 根据登记序号获得纳税人信息
     *
     * @param id 登记序号
     * @return 纳税人信息
     */
    NsrxxDO getNsrxx(String id);

    /**
     * 根据登记序号列表获得纳税人信息列表
     *
     * @param ids 登记序号列表
     * @return 纳税人信息列表
     */
    List<NsrxxDO> getNsrxxList(Collection<String> ids);

}
