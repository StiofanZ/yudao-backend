package cn.iocoder.yudao.module.lghjft.service.cxtj.ghjfjfdw;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ghjfjfdw.vo.GhjfjfdwPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.ghjfjfdw.GhjfjfdwDO;

public interface GhjfjfdwService {
    GhjfjfdwDO getGhjfjfdw(String id);

    PageResult<GhjfjfdwDO> getGhjfjfdwPage(GhjfjfdwPageReqVO pageReqVO);
}
