package cn.iocoder.yudao.module.lghjft.service.ghcbj.cbjhztj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjhztj.vo.CbjhztjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjhztj.vo.CbjhztjhzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjhztj.vo.CbjhztjtjResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.cbjhztj.CbjhztjDO;

import java.util.List;

public interface CbjhztjService {
    CbjhztjDO getCbjhztj(Long id);

    PageResult<CbjhztjDO> getCbjhztjPage(CbjhztjPageReqVO pageReqVO);

    List<CbjhztjtjResVO> getCbjhztjTjList(CbjhztjPageReqVO pageReqVO);

    List<CbjhztjhzResVO> getCbjhztjHzList(CbjhztjPageReqVO pageReqVO);
}
