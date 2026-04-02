package cn.iocoder.yudao.module.lghjft.service.cxtj.zgjrgh;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zgjrgh.vo.ZgjrghPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.zgjrgh.ZgjrghDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.zgjrgh.ZgjrghMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ZgjrghServiceImpl implements ZgjrghService {

    @Resource
    private ZgjrghMapper zgjrghMapper;

    @Override
    public ZgjrghDO getZgjrgh(Long id) {
        return zgjrghMapper.selectById(id);
    }

    @Override
    public PageResult<ZgjrghDO> getZgjrghPage(ZgjrghPageReqVO pageReqVO) {
        return zgjrghMapper.selectPage(pageReqVO);
    }
}
