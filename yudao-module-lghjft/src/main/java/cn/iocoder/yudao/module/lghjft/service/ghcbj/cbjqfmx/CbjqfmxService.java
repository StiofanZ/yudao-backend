package cn.iocoder.yudao.module.lghjft.service.ghcbj.cbjqfmx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjqfmx.vo.CbjqfmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjqfmx.vo.CbjqfmxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.cbjqfmx.CbjqfmxDO;
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
