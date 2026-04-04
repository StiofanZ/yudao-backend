package cn.iocoder.yudao.module.lghjft.service.xejf.xebftz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.GhhkxxxejfszResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.XebfPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xebftz.XebfDO;

import java.util.List;

public interface XebfService {
    XebfDO getXebf(Long id);

    PageResult<XebfDO> getXebfPage(XebfPageReqVO pageReqVO);

    List<GhhkxxxejfszResVO> getGhHkxxxejfszList(String jfqj);
}
