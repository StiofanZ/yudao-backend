package cn.iocoder.yudao.module.lghjft.service.cxtj.top;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.top.vo.TopPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.top.TopDO;

public interface TopService {
    TopDO getTop(String id);

    PageResult<TopDO> getTopPage(TopPageReqVO pageReqVO);
}
