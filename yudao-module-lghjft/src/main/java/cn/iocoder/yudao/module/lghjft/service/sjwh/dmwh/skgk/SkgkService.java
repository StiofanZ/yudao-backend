package cn.iocoder.yudao.module.lghjft.service.sjwh.dmwh.skgk;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.skgk.vo.SkgkPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.skgk.vo.SkgkSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.dmwh.skgk.SkgkDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 收款国库 Service 接口
 *
 * @author 李文军
 */
public interface SkgkService {

    Long createSkgk(@Valid SkgkSaveReqVO createReqVO);

    void updateSkgk(@Valid SkgkSaveReqVO updateReqVO);

    void deleteSkgk(Long id);

    void deleteSkgkListByIds(List<Long> ids);

    SkgkDO getSkgk(Long id);

    PageResult<SkgkDO> getSkgkPage(SkgkPageReqVO pageReqVO);

}
