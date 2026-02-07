package cn.iocoder.yudao.module.lghjft.service.nsrxx;

import java.util.List;

import cn.iocoder.yudao.module.lghjft.dal.dataobject.nsrxx.NsrxxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.nsrxx.NsrxxMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.math.BigInteger;
import java.util.Collections;
import java.util.stream.Collectors;
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
    public List<NsrxxDO> getNsrxxList(String code) {
        return nsrxxMapper.selectListByCode(code);
    }

    @Override
    public NsrxxDO getNsrxxByNsrsbh(String nsrsbh) {
        return nsrxxMapper.selectByNsrsbh(nsrsbh);
    }
    @Override
    public NsrxxDO getNsrxxByDjxh(String djxh) {
        return nsrxxMapper.selectByDjxh(djxh);
    }

    @Override
    public List<NsrxxDO> getNsrxxListByDjxhs(java.util.Collection<String> djxhs) {
        if (CollUtil.isEmpty(djxhs)) {
            return Collections.emptyList();
        }
        Set<BigInteger> djxhBigInts = djxhs.stream()
                .filter(StrUtil::isNotBlank)
                .map(BigInteger::new)
                .collect(Collectors.toSet());
        if (CollUtil.isEmpty(djxhBigInts)) {
            return Collections.emptyList();
        }
        return nsrxxMapper.selectList(new LambdaQueryWrapper<NsrxxDO>().in(NsrxxDO::getDjxh, djxhBigInts));
    }

}
