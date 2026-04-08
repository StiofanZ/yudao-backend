package cn.iocoder.yudao.module.lghjft.service.sjwh.szqzjzdc;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.szqzjzdc.vo.SzqzjzdcPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.szqzjzdc.vo.SzqzjzdcSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.szqzjzdc.SzqzjzdcDO;

import java.util.List;

public interface SzqzjzdcService {

    /** 获取单条 */
    SzqzjzdcDO getSzqzjzdc(String id);

    /** 分页查询（szqzjzdc 表） */
    PageResult<SzqzjzdcDO> getSzqzjzdcPage(SzqzjzdcPageReqVO pageReqVO);

    /** V1: selectSzqzjzdcList — main list (szqzdzzc view) */
    List<SzqzjzdcDO> getLegacyMainList(SzqzjzdcPageReqVO pageReqVO);

    /** V1: selectSzqzjzdcListsd — 属地 list */
    List<SzqzjzdcDO> getLegacySdjzList(SzqzjzdcPageReqVO pageReqVO);

    /** V1: selectSzqzjzdcListhy — 行业 list */
    List<SzqzjzdcDO> getLegacyHyjzList(SzqzjzdcPageReqVO pageReqVO);

    /** V1: selectSzqzjzdcListcbj — 筹备金 list */
    List<SzqzjzdcDO> getLegacyCbjList(SzqzjzdcPageReqVO pageReqVO);

    /** 新增 */
    String createSzqzjzdc(SzqzjzdcSaveReqVO createReqVO);

    /** 修改 */
    void updateSzqzjzdc(SzqzjzdcSaveReqVO updateReqVO);

    /** 批量删除 */
    void deleteSzqzjzdcByPzbhs(List<String> pzbhs);
}
