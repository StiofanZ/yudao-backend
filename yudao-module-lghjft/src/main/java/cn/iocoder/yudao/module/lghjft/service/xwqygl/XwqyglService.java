package cn.iocoder.yudao.module.lghjft.service.xwqygl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglQuery;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglResVO;

import java.util.List;

public interface XwqyglService {

    PageResult<XwqyglResVO> getXwqyglPage(XwqyglQuery query);

    List<XwqyglResVO> getXwqyglList(XwqyglQuery query);
}
