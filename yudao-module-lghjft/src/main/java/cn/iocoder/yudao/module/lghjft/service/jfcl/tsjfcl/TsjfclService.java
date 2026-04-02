package cn.iocoder.yudao.module.lghjft.service.jfcl.tsjfcl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.tsjfcl.vo.TsjfclPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.tsjfcl.vo.TsjfclSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.tsjfcl.TsjfclDO;
import jakarta.validation.Valid;

public interface TsjfclService {

    Long createTsjfcl(@Valid TsjfclSaveReqVO createReqVO);

    void updateTsjfcl(@Valid TsjfclSaveReqVO updateReqVO);

    void deleteTsjfcl(Long id);

    TsjfclDO getTsjfcl(Long id);

    PageResult<TsjfclDO> getTsjfclPage(TsjfclPageReqVO pageReqVO);
}
