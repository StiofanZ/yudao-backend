package cn.iocoder.yudao.module.lghjft.service.cxtj.zswzgdw;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zswzgdw.vo.ZswzgdwPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.zswzgdw.ZswzgdwDO;

public interface ZswzgdwService {
    ZswzgdwDO getZswzgdw(String id);

    PageResult<ZswzgdwDO> getZswzgdwPage(ZswzgdwPageReqVO pageReqVO);
}
