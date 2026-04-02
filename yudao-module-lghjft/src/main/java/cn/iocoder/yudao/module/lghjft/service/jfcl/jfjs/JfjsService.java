package cn.iocoder.yudao.module.lghjft.service.jfcl.jfjs;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfjs.vo.JfjsPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfjs.vo.JfjsSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.jfjs.JfclJfjsDO;
import jakarta.validation.Valid;

public interface JfjsService {

    Long createJfjs(@Valid JfjsSaveReqVO createReqVO);

    void updateJfjs(@Valid JfjsSaveReqVO updateReqVO);

    void deleteJfjs(Long id);

    JfclJfjsDO getJfjs(Long id);

    PageResult<JfclJfjsDO> getJfjsPage(JfjsPageReqVO pageReqVO);
}
