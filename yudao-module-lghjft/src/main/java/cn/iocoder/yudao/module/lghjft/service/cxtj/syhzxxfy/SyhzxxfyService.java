package cn.iocoder.yudao.module.lghjft.service.cxtj.syhzxxfy;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.syhzxxfy.vo.SyhzxxfyPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.syhzxxfy.SyhzxxfyDO;

public interface SyhzxxfyService {
    SyhzxxfyDO getSyhzxxfy(String id);

    PageResult<SyhzxxfyDO> getSyhzxxfyPage(SyhzxxfyPageReqVO pageReqVO);
}
