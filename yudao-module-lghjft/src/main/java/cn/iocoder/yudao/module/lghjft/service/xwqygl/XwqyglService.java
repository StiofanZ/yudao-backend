package cn.iocoder.yudao.module.lghjft.service.xwqygl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglQuery;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglSaveReqVO;

import java.util.List;

public interface XwqyglService {

    PageResult<XwqyglResVO> getXwqyglPage(XwqyglQuery query);

    List<XwqyglResVO> getXwqyglList(XwqyglQuery query);

    XwqyglResVO getXwqyglByDjxh(String djxh);

    void createXwqygl(XwqyglSaveReqVO reqVO);

    void updateXwqygl(XwqyglSaveReqVO reqVO);

    void deleteXwqyglByDjxhs(String[] djxhs);
}
