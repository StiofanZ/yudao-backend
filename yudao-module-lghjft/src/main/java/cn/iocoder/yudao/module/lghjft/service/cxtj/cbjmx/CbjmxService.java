package cn.iocoder.yudao.module.lghjft.service.cxtj.cbjmx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.cbjmx.vo.CbjmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.cbjmx.vo.CbjmxhzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.cbjmx.vo.CbjmxtjResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.cbjmx.CbjmxDO;

import java.util.List;

public interface CbjmxService {
    CbjmxDO getCbjmx(Long id);

    PageResult<CbjmxDO> getCbjmxPage(CbjmxPageReqVO pageReqVO);

    List<CbjmxtjResVO> getCbjmxTjList(CbjmxPageReqVO pageReqVO);

    List<CbjmxhzResVO> getCbjmxHzList(CbjmxPageReqVO pageReqVO);
}
