package cn.iocoder.yudao.module.lghjft.service.hbzz.szdzhd;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.szdzhd.vo.SzdzhdPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.szdzhd.vo.SzdzhdResVO;

public interface SzdzhdService {

    PageResult<SzdzhdResVO> getSzdzhdPage(SzdzhdPageReqVO pageReqVO);
}
