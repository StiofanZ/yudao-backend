package cn.iocoder.yudao.module.lghjft.service.xejf.xebftz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.GhhkxxxejfszResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.XebfPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.XebfResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.XebfSaveReqVO;

import java.util.List;

public interface XebfService {
    Long createXebf(XebfSaveReqVO createReqVO);

    void updateXebf(XebfSaveReqVO updateReqVO);

    void deleteXebf(Long id);

    void deleteXebfList(List<Long> ids);

    XebfResVO getXebf(Long id);

    PageResult<XebfResVO> getXebfPage(XebfPageReqVO pageReqVO);

    List<GhhkxxxejfszResVO> getGhHkxxxejfszList(String jfqj);
}
