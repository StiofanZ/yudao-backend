package cn.iocoder.yudao.module.lghjft.service.jfcl.sgbflr;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.sgbflr.vo.SgbflrPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.sgbflr.vo.SgbflrSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.sgbflr.SgbflrDO;
import jakarta.validation.Valid;

public interface SgbflrService {

    Long createSgbflr(@Valid SgbflrSaveReqVO createReqVO);

    void updateSgbflr(@Valid SgbflrSaveReqVO updateReqVO);

    void deleteSgbflr(Long id);

    SgbflrDO getSgbflr(Long id);

    PageResult<SgbflrDO> getSgbflrPage(SgbflrPageReqVO pageReqVO);
}
