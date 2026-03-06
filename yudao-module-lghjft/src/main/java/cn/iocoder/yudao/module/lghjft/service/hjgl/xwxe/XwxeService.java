package cn.iocoder.yudao.module.lghjft.service.hjgl.xwxe;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.xwxe.vo.XwxePageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hjgl.jcxx.GhHjJcxxDO;

public interface XwxeService {

    PageResult<GhHjJcxxDO> getPage(XwxePageReqVO reqVO);
}
