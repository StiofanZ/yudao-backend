package cn.iocoder.yudao.module.lghjft.service.xwqy.xwqyjftj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjftj.vo.XwqyjftjAggVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjftj.vo.XwqyjftjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjftj.vo.XwqyjftjfhAggVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjftj.vo.XwqyjftjmxResVO;

import java.util.List;

public interface XwqyjftjService {

    /** v1: selectXwqyjftjList — no pagination (startPage() commented) */
    List<XwqyjftjAggVO> getXwqyjftjList(XwqyjftjPageReqVO req);

    /** v1: selectXwqyjftjfhList — paginated */
    PageResult<XwqyjftjfhAggVO> getXwqyjftjfhPage(XwqyjftjPageReqVO req);

    /** v1: selectXwqyjftjfhList — full list for export */
    List<XwqyjftjfhAggVO> getXwqyjftjfhList(XwqyjftjPageReqVO req);

    /** v1: selectXwqyjftjmxList — paginated */
    PageResult<XwqyjftjmxResVO> getXwqyjftjmxPage(XwqyjftjPageReqVO req);

    /** v1: selectXwqyjftjmxList — full list for export */
    List<XwqyjftjmxResVO> getXwqyjftjmxList(XwqyjftjPageReqVO req);
}
