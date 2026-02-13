package cn.iocoder.yudao.module.lghjft.service.nsrxx;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nsrxx.NsrxxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.nsrxx.NsrxxMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.List;
import java.util.Set;

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
    public List<NsrxxDO> getNsrxxList(String shxydm) {
        return nsrxxMapper.selectListByShxydm(shxydm);
    }

    @Override
    public NsrxxDO getNsrxxByNsrsbh(String nsrsbh) {
        return nsrxxMapper.selectOneByShxydm(nsrsbh);
    }

    @Override
    public NsrxxDO getNsrxxByDjxh(String djxh) {
        List<NsrxxDO> nsrxxDOList = getNsrxxListByDjxhs(Set.of(djxh));
        if (CollUtil.isEmpty(nsrxxDOList)) return null;
        return nsrxxDOList.get(0);
    }

    @Override
    public List<NsrxxDO> getNsrxxListByDjxhs(Collection<String> djxhs) {
        return nsrxxMapper.selectListByDjxhs(djxhs.stream().toList());
    }

}
