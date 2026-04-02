package cn.iocoder.yudao.module.lghjft.service.cxtj.jrbxzqdw;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jrbxzqdw.vo.JrbxzqdwPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jrbxzqdw.vo.JrbxzqdwSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.jrbxzqdw.JrbxzqdwDO;
import jakarta.validation.Valid;

import java.util.List;

public interface JrbxzqdwService {
    String createJrbxzqdw(@Valid JrbxzqdwSaveReqVO createReqVO);

    void updateJrbxzqdw(@Valid JrbxzqdwSaveReqVO updateReqVO);

    void deleteJrbxzqdw(String id);

    void deleteJrbxzqdwListByIds(List<String> ids);

    JrbxzqdwDO getJrbxzqdw(String id);

    PageResult<JrbxzqdwDO> getJrbxzqdwPage(JrbxzqdwPageReqVO pageReqVO);
}
