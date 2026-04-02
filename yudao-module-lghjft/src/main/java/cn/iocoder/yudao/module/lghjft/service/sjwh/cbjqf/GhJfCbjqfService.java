package cn.iocoder.yudao.module.lghjft.service.sjwh.cbjqf;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjqf.vo.GhJfCbjqfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjqf.vo.GhJfCbjqfSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.cbjqf.GhJfCbjqfDO;
import jakarta.validation.Valid;

import java.util.List;

public interface GhJfCbjqfService {
    Long createGhJfCbjqf(@Valid GhJfCbjqfSaveReqVO createReqVO);

    void updateGhJfCbjqf(@Valid GhJfCbjqfSaveReqVO updateReqVO);

    void deleteGhJfCbjqf(Long id);

    void deleteGhJfCbjqfListByIds(List<Long> ids);

    GhJfCbjqfDO getGhJfCbjqf(Long id);

    PageResult<GhJfCbjqfDO> getGhJfCbjqfPage(GhJfCbjqfPageReqVO pageReqVO);
}
