package cn.iocoder.yudao.module.lghjft.service.cxtj.dhjftz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dhjftz.vo.DhjftzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.dhjftz.DhjftzDO;

public interface DhjftzService {

    DhjftzDO getDhjftz(String djxh);

    PageResult<DhjftzDO> getDhjftzPage(DhjftzPageReqVO pageReqVO);
}
