package cn.iocoder.yudao.module.lghjft.service.qx.dwxxsp;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dwxxsp.vo.DwxxspAuditReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dwxxsp.vo.DwxxspDetailRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dwxxsp.vo.DwxxspPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dwxxsp.vo.DwxxspRespVO;

public interface DwxxspService {

    PageResult<DwxxspRespVO> getDwxxspPage(DwxxspPageReqVO reqVO);

    DwxxspDetailRespVO getDwxxspDetail(String businessType, Long businessId);

    void audit(DwxxspAuditReqVO reqVO);
}
