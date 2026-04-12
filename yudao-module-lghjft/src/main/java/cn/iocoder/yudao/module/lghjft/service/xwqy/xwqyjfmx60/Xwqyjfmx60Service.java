package cn.iocoder.yudao.module.lghjft.service.xwqy.xwqyjfmx60;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjfmx60.vo.Xwqyjfmx60PageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xwqy.xwqyjfyfmx.XwqyjfyfmxDO;

import java.util.List;

public interface Xwqyjfmx60Service {

    PageResult<XwqyjfyfmxDO> getXwqyjfmx60Page(Xwqyjfmx60PageReqVO pageReqVO);

    List<XwqyjfyfmxDO> getXwqyjfmx60List(Xwqyjfmx60PageReqVO pageReqVO);
}
