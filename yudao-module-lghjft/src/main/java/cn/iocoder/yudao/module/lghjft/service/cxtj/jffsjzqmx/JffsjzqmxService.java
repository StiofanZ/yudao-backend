package cn.iocoder.yudao.module.lghjft.service.cxtj.jffsjzqmx;

import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffsjzqmx.vo.JffsjzqmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jffsjzqmx.vo.JffsjzqmxResVO;

import java.util.List;

public interface JffsjzqmxService {

    List<JffsjzqmxResVO> getJffsjzqmxList(JffsjzqmxPageReqVO pageReqVO);
}
