package cn.iocoder.yudao.module.lghjft.service.jfcl.dqzldssj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqzldssj.vo.DqzldssjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqzldssj.vo.DqzldssjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.dqdssj.JfzcDqdssjVo;

public interface DqzldssjService {

    PageResult<JfzcDqdssjVo> getDqzldssjPage(DqzldssjPageReqVO pageReqVO);

    String updateDqzldssjrk(DqzldssjSaveReqVO reqVO);
}
