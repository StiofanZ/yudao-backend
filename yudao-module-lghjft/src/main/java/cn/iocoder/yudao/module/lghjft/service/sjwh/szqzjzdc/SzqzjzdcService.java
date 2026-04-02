package cn.iocoder.yudao.module.lghjft.service.sjwh.szqzjzdc;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.szqzjzdc.vo.SzqzjzdcPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.szqzjzdc.SzqzjzdcDO;

import java.util.List;

public interface SzqzjzdcService {
    SzqzjzdcDO getSzqzjzdc(String id);

    PageResult<SzqzjzdcDO> getSzqzjzdcPage(SzqzjzdcPageReqVO pageReqVO);

    List<SzqzjzdcDO> getLegacySdjzList(SzqzjzdcPageReqVO pageReqVO);

    List<SzqzjzdcDO> getLegacyHyjzList(SzqzjzdcPageReqVO pageReqVO);
}
