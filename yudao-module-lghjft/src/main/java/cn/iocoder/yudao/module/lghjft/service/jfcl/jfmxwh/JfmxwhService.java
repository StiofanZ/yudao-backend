package cn.iocoder.yudao.module.lghjft.service.jfcl.jfmxwh;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfmxwh.vo.JfmxwhPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.jfmxwh.vo.JfmxwhSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.jfmxwh.JfmxwhDO;
import jakarta.validation.Valid;

import java.util.List;

public interface JfmxwhService {

    Long createJfmxwh(@Valid JfmxwhSaveReqVO createReqVO);

    void updateJfmxwh(@Valid JfmxwhSaveReqVO updateReqVO);

    void deleteJfmxwh(Long id);

    void deleteJfmxwhList(List<Long> ids);

    JfmxwhDO getJfmxwh(Long id);

    PageResult<JfmxwhDO> getJfmxwhPage(JfmxwhPageReqVO pageReqVO);
}
