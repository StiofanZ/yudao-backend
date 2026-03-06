package cn.iocoder.yudao.module.lghjft.service.qx.aqzx;

import cn.iocoder.yudao.module.lghjft.controller.admin.qx.aqzx.vo.AqzxProfileRespVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.aqzx.vo.AqzxUpdateNoticeMobileReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.aqzx.vo.AqzxUpdatePasswordReqVO;
import jakarta.validation.Valid;

public interface AqzxService {

    AqzxProfileRespVO getCurrentProfile();

    void sendSmsCode(String mobile);

    void updateNoticeMobile(@Valid AqzxUpdateNoticeMobileReqVO reqVO);

    void updatePassword(@Valid AqzxUpdatePasswordReqVO reqVO);
}
