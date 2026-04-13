package cn.iocoder.yudao.module.lghjft.service.jfcl.dqzldssj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqzldssj.vo.DqzldssjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.dqzldssj.vo.DqzldssjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.dqdssj.JfzcDqdssjVo;
import cn.iocoder.yudao.module.lghjft.service.jfcl.dqdssj.DqdssjService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class DqzldssjServiceImpl implements DqzldssjService {

    @Resource
    private DqdssjService dqdssjService;

    @Override
    public PageResult<JfzcDqdssjVo> getDqzldssjPage(DqzldssjPageReqVO pageReqVO) {
        return dqdssjService.getDqzldssjPage(pageReqVO);
    }

    @Override
    public String updateDqzldssjrk(DqzldssjSaveReqVO reqVO) {
        return dqdssjService.updateDqdssjrkzl(reqVO);
    }
}
