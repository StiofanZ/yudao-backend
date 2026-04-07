package cn.iocoder.yudao.module.lghjft.service.cxtj.hbsbjl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjl.vo.HbsbjlPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjl.vo.HbsbjlSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.hbsbjl.HbsbjlDO;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

public interface HbsbjlService {
    Long createHbsbjl(@Valid HbsbjlSaveReqVO createReqVO);

    void updateHbsbjl(@Valid HbsbjlSaveReqVO updateReqVO);

    void deleteHbsbjl(Long id);

    void deleteHbsbjlListByIds(List<Long> ids);

    HbsbjlDO getHbsbjl(Long id);

    PageResult<HbsbjlDO> getHbsbjlPage(HbsbjlPageReqVO pageReqVO);

    /**
     * 批量复审（还原 V1 fushenPlByhkxxIds）
     *
     * @param hkxxIds      需批量修改的记录 ID 列表
     * @param updateFields 需批量修改的字段 map（key=列名, value=值）
     * @return 影响行数
     */
    int fushenPlByhkxxIds(List<Long> hkxxIds, Map<String, Object> updateFields);
}
