package cn.iocoder.yudao.module.lghjft.service.cxtj.yjhxx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.yjhxx.vo.GhYjhxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.yjhxx.vo.GhYjhxxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.yjhxx.GhYjhxxDO;
import jakarta.validation.Valid;

import java.util.List;

public interface GhYjhxxService {
    Long createGhYjhxx(@Valid GhYjhxxSaveReqVO createReqVO);

    void updateGhYjhxx(@Valid GhYjhxxSaveReqVO updateReqVO);

    void deleteGhYjhxx(Long id);

    void deleteGhYjhxxListByIds(List<Long> ids);

    GhYjhxxDO getGhYjhxx(Long id);

    PageResult<GhYjhxxDO> getGhYjhxxPage(GhYjhxxPageReqVO pageReqVO);
}
