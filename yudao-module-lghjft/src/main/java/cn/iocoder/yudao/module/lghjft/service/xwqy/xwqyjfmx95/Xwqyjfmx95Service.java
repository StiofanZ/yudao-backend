package cn.iocoder.yudao.module.lghjft.service.xwqy.xwqyjfmx95;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjfmx95.vo.Xwqyjfmx95PageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xwqy.xwqyjf.XwqyjfDO;

import java.util.List;

public interface Xwqyjfmx95Service {

    PageResult<XwqyjfDO> getXwqyjfmx95Page(Xwqyjfmx95PageReqVO pageReqVO);

    List<XwqyjfDO> getXwqyjfmx95List(Xwqyjfmx95PageReqVO pageReqVO);
}
