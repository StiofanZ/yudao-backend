package cn.iocoder.yudao.module.lghjft.service.hbzz.jfbfmx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jfbfmx.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.jfbfmx.JfbfmxDO;
import jakarta.validation.Valid;

import java.util.List;

public interface JfbfmxService {
    Long createJfbfmx(@Valid JfbfmxSaveReqVO createReqVO);

    void updateJfbfmx(@Valid JfbfmxSaveReqVO updateReqVO);

    void deleteJfbfmx(Long id);

    void deleteJfbfmxListByIds(List<Long> ids);

    JfbfmxDO getJfbfmx(Long id);

    PageResult<JfbfmxDO> getJfbfmxPage(JfbfmxPageReqVO pageReqVO);

    PageResult<JfbfmxSummaryResVO> getSzmxPage(JfbfmxPageReqVO pageReqVO);

    PageResult<JfbfmxDetailResVO> getHymxPage(JfbfmxPageReqVO pageReqVO);

    PageResult<JfbfmxDetailResVO> getXjmxPage(JfbfmxPageReqVO pageReqVO);

    PageResult<JfbfmxDetailResVO> getSjmxPage(JfbfmxPageReqVO pageReqVO);

    PageResult<JfbfmxDetailResVO> getSdmxPage(JfbfmxPageReqVO pageReqVO);

    List<JfbfmxtjResVO> getTjByDept(JfbfmxPageReqVO pageReqVO);

    List<JfbfmxjsbjResVO> getTjByJsbj(JfbfmxPageReqVO pageReqVO);

    List<JfbfmxcbjResVO> getTjByCbj(JfbfmxPageReqVO pageReqVO);

    List<JfbfmxzspmResVO> getTjByZspm(JfbfmxPageReqVO pageReqVO);

    List<JfbfmxhkResVO> getTjByHkpch(JfbfmxPageReqVO pageReqVO);
}
