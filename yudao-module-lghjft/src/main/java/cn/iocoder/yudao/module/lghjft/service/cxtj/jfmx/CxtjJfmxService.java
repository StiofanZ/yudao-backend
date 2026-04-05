package cn.iocoder.yudao.module.lghjft.service.cxtj.jfmx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JfmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JfmxResVO;

public interface CxtjJfmxService {

    PageResult<JfmxResVO> getJfmxPage(JfmxPageReqVO pageReqVO);
}
