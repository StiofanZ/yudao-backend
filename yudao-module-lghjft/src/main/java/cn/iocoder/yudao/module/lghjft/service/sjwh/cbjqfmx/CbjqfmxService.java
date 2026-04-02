package cn.iocoder.yudao.module.lghjft.service.sjwh.cbjqfmx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjqfmx.vo.CbjqfmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.cbjqfmx.vo.CbjqfmxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.cbjqfmx.CbjqfmxDO;
import jakarta.validation.Valid;

import java.util.List;

public interface CbjqfmxService {
    Long createCbjqfmx(@Valid CbjqfmxSaveReqVO createReqVO);

    void updateCbjqfmx(@Valid CbjqfmxSaveReqVO updateReqVO);

    void deleteCbjqfmx(Long id);

    void deleteCbjqfmxListByIds(List<Long> ids);

    CbjqfmxDO getCbjqfmx(Long id);

    PageResult<CbjqfmxDO> getCbjqfmxPage(CbjqfmxPageReqVO pageReqVO);
}
