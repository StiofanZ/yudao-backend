package cn.iocoder.yudao.module.lghjft.service.sjwh.jczhkxwh;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jczhkxwh.vo.JczhkxwhPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jczhkxwh.vo.JczhkxwhSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.jczhkxwh.JczhkxwhDO;
import jakarta.validation.Valid;

import java.util.List;

public interface JczhkxwhService {
    String createJczhkxwh(@Valid JczhkxwhSaveReqVO createReqVO);

    void updateJczhkxwh(@Valid JczhkxwhSaveReqVO updateReqVO);

    void deleteJczhkxwh(String id);

    void deleteJczhkxwhListByIds(List<String> ids);

    JczhkxwhDO getJczhkxwh(String id);

    PageResult<JczhkxwhDO> getJczhkxwhPage(JczhkxwhPageReqVO pageReqVO);
}
