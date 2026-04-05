package cn.iocoder.yudao.module.lghjft.service.hbzz.dgjftz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.dgjftz.vo.DgjftzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.dgjftz.vo.DgjftzResVO;

public interface DgjftzService {

    PageResult<DgjftzResVO> getDgjftzPage(DgjftzPageReqVO pageReqVO);
}
