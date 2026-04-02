package cn.iocoder.yudao.module.lghjft.service.xwqy.xwqyjftj;

import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjftj.vo.XwqyjftjAggVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjftj.vo.XwqyjftjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjftj.vo.XwqyjftjfhAggVO;

import java.util.List;

public interface XwqyjftjService {

    List<XwqyjftjAggVO> getXwqyjftjList(XwqyjftjPageReqVO req);

    List<XwqyjftjfhAggVO> getXwqyjftjfhList(XwqyjftjPageReqVO req);
}
