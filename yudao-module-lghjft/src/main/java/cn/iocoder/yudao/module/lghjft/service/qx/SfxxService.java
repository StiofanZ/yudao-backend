package cn.iocoder.yudao.module.lghjft.service.qx;

import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.KbdsfxxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxReqVO;

import java.util.List;

public interface SfxxService {
    List<KbdsfxxResVO> getKbdsfxx(SfxxReqVO reqVO);
}
