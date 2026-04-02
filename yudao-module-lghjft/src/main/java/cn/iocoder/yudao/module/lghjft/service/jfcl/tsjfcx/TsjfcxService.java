package cn.iocoder.yudao.module.lghjft.service.jfcl.tsjfcx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.tsjfcx.vo.TsjfcxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.tsjfcx.vo.TsjfcxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.tsjfcx.TsjfcxDO;
import jakarta.validation.Valid;

public interface TsjfcxService {

    Long createTsjfcx(@Valid TsjfcxSaveReqVO createReqVO);

    void updateTsjfcx(@Valid TsjfcxSaveReqVO updateReqVO);

    void deleteTsjfcx(Long id);

    TsjfcxDO getTsjfcx(Long id);

    PageResult<TsjfcxDO> getTsjfcxPage(TsjfcxPageReqVO pageReqVO);
}
