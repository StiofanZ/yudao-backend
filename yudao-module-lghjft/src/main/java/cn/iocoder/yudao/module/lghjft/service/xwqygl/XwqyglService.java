package cn.iocoder.yudao.module.lghjft.service.xwqygl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglQuery;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglRespVO;

import java.util.List;

public interface XwqyglService {

    PageResult<XwqyglRespVO> getXwqyglPage(XwqyglQuery query);

    List<XwqyglRespVO> getXwqyglList(XwqyglQuery query);
}
