package cn.iocoder.yudao.module.lghjft.service.jfcl.dqdssj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqdssj.vo.DqdssjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqdssj.vo.DqdssjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqdssj.vo.DqzldssjPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.dqdssj.JfclDqdssjDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.dqdssj.JfzcDqdssjVo;

/**
 * V1: IJfclDqdssjService
 */
public interface DqdssjService {

    /**
     * V1: selecList - paginated list from gh_qsjshkrj
     */
    PageResult<JfclDqdssjDO> getDqdssjPage(DqdssjPageReqVO pageReqVO);

    /**
     * V1: selecListzl - paginated list from gh_jf WHERE jsbj='E'
     */
    PageResult<JfzcDqdssjVo> getDqzldssjPage(DqzldssjPageReqVO pageReqVO);

    /**
     * V1: updateDqdssjrk - async import dqdssj
     */
    String updateDqdssjrk(DqdssjSaveReqVO reqVO);

    /**
     * V1: updateDqdssjrkzl - async import dqzldssj
     */
    String updateDqdssjrkzl(DqdssjSaveReqVO reqVO);
}
