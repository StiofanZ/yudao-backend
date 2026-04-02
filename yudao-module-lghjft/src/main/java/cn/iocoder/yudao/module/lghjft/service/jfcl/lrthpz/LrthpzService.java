package cn.iocoder.yudao.module.lghjft.service.jfcl.lrthpz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.lrthpz.vo.LrthpzImportVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.lrthpz.vo.LrthpzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.lrthpz.vo.LrthpzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.lrthpz.LrthpzDO;
import jakarta.validation.Valid;

import java.util.List;

public interface LrthpzService {

    Long createLrthpz(@Valid LrthpzSaveReqVO createReqVO);

    void updateLrthpz(@Valid LrthpzSaveReqVO updateReqVO);

    void deleteLrthpz(Long id);

    LrthpzDO getLrthpz(Long id);

    PageResult<LrthpzDO> getLrthpzPage(LrthpzPageReqVO pageReqVO);

    /**
     * 导入退回凭证
     *
     * @param list Excel导入的数据列表
     * @return 成功导入的条数
     */
    int importLrthpz(List<LrthpzImportVO> list);
}
