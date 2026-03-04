package cn.iocoder.yudao.module.lghjft.service.qx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.KbdsfxxRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxPageReqVO;

public interface SfxxService {
    PageResult<KbdsfxxRespVO> getKbdsfxx(SfxxPageReqVO pageReqVO);
}
