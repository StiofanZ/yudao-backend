package cn.iocoder.yudao.module.lghjft.service.xejf.xejftz2023;

import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejftz2023.vo.Xejftz2023PageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejftz2023.vo.Xejftz2023ResVO;

import java.util.List;

public interface Xejftz2023Service {

    List<Xejftz2023ResVO> getXejftz2023List(Xejftz2023PageReqVO pageReqVO);
}
