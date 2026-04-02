package cn.iocoder.yudao.module.lghjft.service.bbsj;

import cn.iocoder.yudao.module.lghjft.controller.app.bbsj.vo.BbsjResVO;

import java.util.Map;

public interface BbsjService {

    BbsjResVO hqBbsj(String bbbm, Map<String, Object> cxcs);

}
