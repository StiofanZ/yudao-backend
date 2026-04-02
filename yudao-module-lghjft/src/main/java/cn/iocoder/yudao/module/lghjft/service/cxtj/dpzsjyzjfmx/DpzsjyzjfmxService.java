package cn.iocoder.yudao.module.lghjft.service.cxtj.dpzsjyzjfmx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dpzsjyzjfmx.vo.DpzsjyzjfmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.dpzsjyzjfmx.DpzsjyzjfmxDO;

public interface DpzsjyzjfmxService {
    DpzsjyzjfmxDO getDpzsjyzjfmx(String id);

    PageResult<DpzsjyzjfmxDO> getDpzsjyzjfmxPage(DpzsjyzjfmxPageReqVO pageReqVO);
}
