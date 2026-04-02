package cn.iocoder.yudao.module.lghjft.service.jfcl.thpzcf;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.thpzcf.vo.ThpzcfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.thpzcf.vo.ThpzcfSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.thpzcf.ThpzcfDO;
import jakarta.validation.Valid;

public interface ThpzcfService {

    Long createThpzcf(@Valid ThpzcfSaveReqVO createReqVO);

    void updateThpzcf(@Valid ThpzcfSaveReqVO updateReqVO);

    void deleteThpzcf(Long id);

    ThpzcfDO getThpzcf(Long id);

    PageResult<ThpzcfDO> getThpzcfPage(ThpzcfPageReqVO pageReqVO);
}
