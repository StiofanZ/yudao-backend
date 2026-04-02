package cn.iocoder.yudao.module.lghjft.service.xwqy.xwqyjf;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjf.vo.XwqyjfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjf.vo.XwqyjfSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xwqy.xwqyjf.XwqyjfDO;
import jakarta.validation.Valid;

import java.util.List;

public interface XwqyjfService {
    Long createXwqyjf(@Valid XwqyjfSaveReqVO createReqVO);

    void updateXwqyjf(@Valid XwqyjfSaveReqVO updateReqVO);

    void deleteXwqyjf(Long id);

    void deleteXwqyjfListByIds(List<Long> ids);

    XwqyjfDO getXwqyjf(Long id);

    PageResult<XwqyjfDO> getXwqyjfPage(XwqyjfPageReqVO pageReqVO);

    List<XwqyjfDO> getXwqyjfPageYf(XwqyjfPageReqVO pageReqVO);
}
