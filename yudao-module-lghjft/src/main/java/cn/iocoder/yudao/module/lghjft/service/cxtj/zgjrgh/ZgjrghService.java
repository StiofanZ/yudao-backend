package cn.iocoder.yudao.module.lghjft.service.cxtj.zgjrgh;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zgjrgh.vo.ZgjrghPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.zgjrgh.ZgjrghDO;

public interface ZgjrghService {
    ZgjrghDO getZgjrgh(Long id);

    PageResult<ZgjrghDO> getZgjrghPage(ZgjrghPageReqVO pageReqVO);
}
