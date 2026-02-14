package cn.iocoder.yudao.module.lghjft.service.nsrxx;

import cn.iocoder.yudao.module.lghjft.dal.dataobject.nsrxx.NsrxxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.nsrxx.NsrxxMapper;
import cn.iocoder.yudao.module.lghjft.dal.mysql.nsrxx.vo.NsrxxQueryParam;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.List;

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
    public List<NsrxxDO> getNsrxxList(String keyword) {
        return nsrxxMapper.getNsrxxList(new NsrxxQueryParam().setKeyword(keyword));
    }

    @Override
    public NsrxxDO getNsrxx(String id) {
        return nsrxxMapper.getNsrxx(id);
    }

    @Override
    public List<NsrxxDO> getNsrxxList(Collection<String> ids) {
        return nsrxxMapper.getNsrxxList(new NsrxxQueryParam().setDjxhList(ids).setOnlyValid(false));
    }

}
