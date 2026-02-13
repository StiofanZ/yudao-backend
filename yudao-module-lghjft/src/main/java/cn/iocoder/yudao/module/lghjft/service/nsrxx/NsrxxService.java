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
     * 根据社会信用代码或纳税人识别号查询纳税人信息列表
     *
     * @param shxydm 社会信用代码或纳税人识别号
     * @return 纳税人信息列表
     */
    List<NsrxxDO> getNsrxxList(String shxydm);

    /**
     * 根据纳税人识别号查询纳税人信息
     *
     * @param nsrsbh 纳税人识别号
     * @return 纳税人信息
     */
    NsrxxDO getNsrxxByNsrsbh(String nsrsbh);

    NsrxxDO getNsrxxByDjxh(String djxh);

    List<NsrxxDO> getNsrxxListByDjxhs(Collection<String> djxhs);

}
