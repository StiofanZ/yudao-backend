package cn.iocoder.yudao.module.lghjft.service.workflow.jfhjsq;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.jfhjsq.vo.GhWfJfhjsqSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.app.workflow.jfhjsq.vo.GhWfJfhjsqAppPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.jfhjsq.GhWfJfhjsqDO;
import jakarta.validation.Valid;

public interface GhWfJfhjsqService {

    Long createGhWfJfhjsq(@Valid GhWfJfhjsqSaveReqVO createReqVO);

    GhWfJfhjsqDO getGhWfJfhjsq(Long id);

    PageResult<GhWfJfhjsqDO> getSelfPage(Long userId, @Valid GhWfJfhjsqAppPageReqVO pageReqVO);
}
