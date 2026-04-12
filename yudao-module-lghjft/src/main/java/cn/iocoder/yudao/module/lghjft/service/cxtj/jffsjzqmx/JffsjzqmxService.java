package cn.iocoder.yudao.module.lghjft.service.cxtj.jffsjzqmx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffsjzqmx.vo.JffsjzqmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffsjzqmx.vo.JffsjzqmxResVO;

public interface JffsjzqmxService {

    PageResult<JffsjzqmxResVO> getJffsjzqmxPage(JffsjzqmxPageReqVO pageReqVO);
}
