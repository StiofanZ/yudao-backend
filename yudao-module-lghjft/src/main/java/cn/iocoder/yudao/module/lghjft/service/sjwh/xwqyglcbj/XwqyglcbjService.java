package cn.iocoder.yudao.module.lghjft.service.sjwh.xwqyglcbj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.xwqyglcbj.vo.XwqyglcbjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.xwqyglcbj.vo.XwqyglcbjResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.xwqyglcbj.vo.XwqyglcbjSaveReqVO;

import java.util.List;

public interface XwqyglcbjService {

    PageResult<XwqyglcbjResVO> getXwqyglcbjPage(XwqyglcbjPageReqVO pageReqVO);

    List<XwqyglcbjResVO> getXwqyglcbjList(XwqyglcbjPageReqVO pageReqVO);

    XwqyglcbjResVO getXwqyglcbjByDjxh(String djxh);

    void updateXwqyglcbj(XwqyglcbjSaveReqVO reqVO);
}
