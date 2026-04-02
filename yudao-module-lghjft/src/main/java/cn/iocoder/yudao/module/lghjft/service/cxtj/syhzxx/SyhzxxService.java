package cn.iocoder.yudao.module.lghjft.service.cxtj.syhzxx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.syhzxx.vo.SyhzxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.syhzxx.SyhzxxDO;

public interface SyhzxxService {
    SyhzxxDO getSyhzxx(String id);

    PageResult<SyhzxxDO> getSyhzxxPage(SyhzxxPageReqVO pageReqVO);
}
