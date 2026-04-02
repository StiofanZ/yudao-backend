package cn.iocoder.yudao.module.lghjft.service.jfcl.jfbjs;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfbjs.vo.JfbjsPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfbjs.vo.JfbjsSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.jfbjs.JfclJfbjsDO;
import jakarta.validation.Valid;

public interface JfbjsService {

    Long createJfbjs(@Valid JfbjsSaveReqVO createReqVO);

    void updateJfbjs(@Valid JfbjsSaveReqVO updateReqVO);

    void deleteJfbjs(Long id);

    JfclJfbjsDO getJfbjs(Long id);

    PageResult<JfclJfbjsDO> getJfbjsPage(JfbjsPageReqVO pageReqVO);
}
