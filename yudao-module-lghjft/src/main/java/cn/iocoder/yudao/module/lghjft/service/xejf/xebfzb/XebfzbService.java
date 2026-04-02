package cn.iocoder.yudao.module.lghjft.service.xejf.xebfzb;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebfzb.vo.XebfzbPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebfzb.vo.XebfzbSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xebfzb.XebfzbDO;
import jakarta.validation.Valid;

import java.util.List;

public interface XebfzbService {
    String createXebfzb(@Valid XebfzbSaveReqVO createReqVO);

    void updateXebfzb(@Valid XebfzbSaveReqVO updateReqVO);

    void deleteXebfzb(String id);

    void deleteXebfzbListByIds(List<String> ids);

    XebfzbDO getXebfzb(String id);

    PageResult<XebfzbDO> getXebfzbPage(XebfzbPageReqVO pageReqVO);
}
