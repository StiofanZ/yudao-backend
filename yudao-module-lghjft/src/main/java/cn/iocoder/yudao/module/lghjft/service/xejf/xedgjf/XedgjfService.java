package cn.iocoder.yudao.module.lghjft.service.xejf.xedgjf;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xedgjf.vo.XedgjfDeptScopeResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xedgjf.vo.XedgjfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xedgjf.vo.XedgjfResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xedgjf.vo.XedgjfSaveReqVO;

public interface XedgjfService {

    XedgjfResVO getXedgjf(Long id);

    XedgjfDeptScopeResVO getDeptScope(Long deptId);

    PageResult<XedgjfResVO> getXedgjfPage(XedgjfPageReqVO pageReqVO);

    void updateXedgjf(XedgjfSaveReqVO updateReqVO);
}
