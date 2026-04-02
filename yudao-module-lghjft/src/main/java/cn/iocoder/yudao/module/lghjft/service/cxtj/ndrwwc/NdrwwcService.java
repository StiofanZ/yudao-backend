package cn.iocoder.yudao.module.lghjft.service.cxtj.ndrwwc;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ndrwwc.vo.NdrwwcPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.ndrwwc.NdrwwcDO;

public interface NdrwwcService {
    NdrwwcDO getNdrwwc(String id);

    PageResult<NdrwwcDO> getNdrwwcPage(NdrwwcPageReqVO pageReqVO);
}
