package cn.iocoder.yudao.module.lghjft.service.cxtj.zswzgdw;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zswzgdw.vo.ZswzgdwPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.zswzgdw.ZswzgdwDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.zswzgdw.ZswzgdwMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ZswzgdwServiceImpl implements ZswzgdwService {

    @Resource
    private ZswzgdwMapper zswzgdwMapper;

    @Override
    public ZswzgdwDO getZswzgdw(String id) {
        return zswzgdwMapper.selectById(id);
    }

    @Override
    public PageResult<ZswzgdwDO> getZswzgdwPage(ZswzgdwPageReqVO pageReqVO) {
        return zswzgdwMapper.selectPage(pageReqVO);
    }
}
