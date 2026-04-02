package cn.iocoder.yudao.module.lghjft.service.sjwh.dmwh.fpbl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.fpbl.vo.CsFpblPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.fpbl.vo.CsFpblSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.dmwh.fpbl.CsFpblDO;
import jakarta.validation.Valid;

import java.util.List;

public interface CsFpblService {
    Long createCsFpbl(@Valid CsFpblSaveReqVO createReqVO);

    void updateCsFpbl(@Valid CsFpblSaveReqVO updateReqVO);

    void deleteCsFpbl(Long id);

    void deleteCsFpblListByIds(List<Long> ids);

    CsFpblDO getCsFpbl(Long id);

    PageResult<CsFpblDO> getCsFpblPage(CsFpblPageReqVO pageReqVO);
}
