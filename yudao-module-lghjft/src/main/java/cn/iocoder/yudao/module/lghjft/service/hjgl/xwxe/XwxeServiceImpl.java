package cn.iocoder.yudao.module.lghjft.service.hjgl.xwxe;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.xwxe.vo.XwxePageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hjgl.jcxx.GhHjJcxxDO;
import cn.iocoder.yudao.module.lghjft.dal.mysql.hjgl.xwxe.XwxeMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class XwxeServiceImpl implements XwxeService {

    @Resource
    private XwxeMapper xwxeMapper;

    @Override
    public PageResult<GhHjJcxxDO> getPage(XwxePageReqVO reqVO) {
        return xwxeMapper.selectPage(reqVO);
    }
}
