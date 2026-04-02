package cn.iocoder.yudao.module.lghjft.service.qx.dwxxsp;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dwxxsp.vo.DwxxspAuditReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dwxxsp.vo.DwxxspDetailResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dwxxsp.vo.DwxxspPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dwxxsp.vo.DwxxspResVO;

public interface DwxxspService {

    PageResult<DwxxspResVO> getDwxxspPage(DwxxspPageReqVO reqVO);

    DwxxspDetailResVO getDwxxspDetail(String businessType, Long businessId);

    void audit(DwxxspAuditReqVO reqVO);
}
