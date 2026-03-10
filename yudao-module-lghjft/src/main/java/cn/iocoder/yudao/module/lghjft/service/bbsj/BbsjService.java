package cn.iocoder.yudao.module.lghjft.service.bbsj;

import cn.iocoder.yudao.module.lghjft.controller.app.bbsj.vo.BbsjRespVO;

import java.util.Map;

public interface BbsjService {

    BbsjRespVO hqBbsj(String bbbm, Map<String, Object> cxcs);

}
