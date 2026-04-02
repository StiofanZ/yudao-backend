package cn.iocoder.yudao.module.lghjft.service.sjwh.swrksj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.swrksj.vo.SwrksjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.swrksj.vo.SwrksjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.swrksj.SwrksjDO;
import jakarta.validation.Valid;

import java.util.List;

public interface SwrksjService {
    Long createSwrksj(@Valid SwrksjSaveReqVO createReqVO);

    void updateSwrksj(@Valid SwrksjSaveReqVO updateReqVO);

    void deleteSwrksj(Long id);

    void deleteSwrksjListByIds(List<Long> ids);

    SwrksjDO getSwrksj(Long id);

    PageResult<SwrksjDO> getSwrksjPage(SwrksjPageReqVO pageReqVO);
}
