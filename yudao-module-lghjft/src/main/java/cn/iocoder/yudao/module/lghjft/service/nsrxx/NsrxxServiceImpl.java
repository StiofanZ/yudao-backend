package cn.iocoder.yudao.module.lghjft.service.nsrxx;

import cn.iocoder.yudao.module.lghjft.dal.dataobject.nsrxx.NsrxxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.nsrxx.NsrxxMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 纳税人信息 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class NsrxxServiceImpl implements NsrxxService {

    @Resource
    private NsrxxMapper nsrxxMapper;

    @Override
    public NsrxxDO getNsrxxByNsrsbh(String nsrsbh) {
        return nsrxxMapper.selectByNsrsbh(nsrsbh);
    }

}
