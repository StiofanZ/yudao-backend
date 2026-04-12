package cn.iocoder.yudao.module.lghjft.service.ghcbj.cbjqltz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjqltz.vo.CbjqltzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjqltz.vo.CbjqltzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.cbjqltz.CbjqltzDO;
import jakarta.validation.Valid;

public interface CbjqltzService {
    void updateCbjqltz(@Valid CbjqltzSaveReqVO updateReqVO);

    CbjqltzDO getCbjqltz(String id);

    PageResult<CbjqltzDO> getCbjqltzPage(CbjqltzPageReqVO pageReqVO);
}
