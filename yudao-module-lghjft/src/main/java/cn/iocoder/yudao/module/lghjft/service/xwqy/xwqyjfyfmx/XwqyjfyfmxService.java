package cn.iocoder.yudao.module.lghjft.service.xwqy.xwqyjfyfmx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjfyfmx.vo.XwqyjfyfmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjfyfmx.vo.XwqyjfyfmxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xwqy.xwqyjfyfmx.XwqyjfyfmxDO;
import jakarta.validation.Valid;

import java.util.List;

public interface XwqyjfyfmxService {
    Long createXwqyjfyfmx(@Valid XwqyjfyfmxSaveReqVO createReqVO);

    void updateXwqyjfyfmx(@Valid XwqyjfyfmxSaveReqVO updateReqVO);

    void deleteXwqyjfyfmx(Long id);

    void deleteXwqyjfyfmxListByIds(List<Long> ids);

    XwqyjfyfmxDO getXwqyjfyfmx(Long id);

    PageResult<XwqyjfyfmxDO> getXwqyjfyfmxPage(XwqyjfyfmxPageReqVO pageReqVO);

    List<XwqyjfyfmxDO> getXwqyjfyfmxList(XwqyjfyfmxPageReqVO pageReqVO);
}
