package cn.iocoder.yudao.module.lghjft.service.xejf.xetz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xetz.vo.XetzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xetz.XetzDO;

public interface XetzService {
    XetzDO getXetz(String id);

    PageResult<XetzDO> getXetzPage(XetzPageReqVO pageReqVO);
}
