package cn.iocoder.yudao.module.lghjft.service.jfcl.schbwj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.schbwj.vo.SchbwjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.schbwj.vo.SchbwjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.schbwj.JfclSchbwjDO;
import jakarta.validation.Valid;

public interface SchbwjService {

    Long createSchbwj(@Valid SchbwjSaveReqVO createReqVO);

    void updateSchbwj(@Valid SchbwjSaveReqVO updateReqVO);

    void deleteSchbwj(Long id);

    JfclSchbwjDO getSchbwj(Long id);

    PageResult<JfclSchbwjDO> getSchbwjPage(SchbwjPageReqVO pageReqVO);

    /**
     * 生成划拨数据（v1 updateGhjfhb）
     */
    String generateHbData(String jsrqStart, String jsrqEnd, java.util.Date hkrq);
}
