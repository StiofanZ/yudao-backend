package cn.iocoder.yudao.module.lghjft.service.jfcl.dqdssj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqdssj.vo.DqdssjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqdssj.vo.DqdssjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.dqdssj.JfclDqdssjDO;
import jakarta.validation.Valid;

public interface DqdssjService {

    Long createDqdssj(@Valid DqdssjSaveReqVO createReqVO);

    void updateDqdssj(@Valid DqdssjSaveReqVO updateReqVO);

    void deleteDqdssj(Long id);

    JfclDqdssjDO getDqdssj(Long id);

    PageResult<JfclDqdssjDO> getDqdssjPage(DqdssjPageReqVO pageReqVO);
}
