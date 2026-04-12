package cn.iocoder.yudao.module.lghjft.service.xwqy.xwqygl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqygl.vo.XwqyglSaveReqVO;

import java.util.List;

public interface XwqyglService {

    PageResult<XwqyglResVO> getXwqyglPage(XwqyglPageReqVO pageReqVO);

    List<XwqyglResVO> getXwqyglList(XwqyglPageReqVO pageReqVO);

    XwqyglResVO getXwqyglByDjxh(String djxh);

    void createXwqygl(XwqyglSaveReqVO reqVO);

    void updateXwqygl(XwqyglSaveReqVO reqVO);

    void deleteXwqyglByDjxhs(String[] djxhs);
}
