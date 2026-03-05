package cn.iocoder.yudao.module.lghjft.service.qx;

import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.KbdsfxxRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxReqVO;

import java.util.List;

public interface SfxxService {
    List<KbdsfxxRespVO> getKbdsfxx(SfxxReqVO reqVO);
}
