package cn.iocoder.yudao.module.lghjft.service.nrgl.zcwj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcwj.vo.ZcwjCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcwj.vo.ZcwjReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcwj.vo.ZcwjUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.zcwj.ZcwjDO;
import jakarta.validation.Valid;

import java.util.List;

public interface ZcwjService {

    Long createZcwj(@Valid ZcwjCreateReqVO createReqVO);

    void updateZcwj(@Valid ZcwjUpdateReqVO updateReqVO);

    void deleteZcwj(Long id);

    ZcwjDO getZcwj(Long id);

    PageResult<ZcwjDO> getZcwjPage(ZcwjReqVO reqVO);

    List<ZcwjDO> getPublishedList(ZcwjReqVO reqVO);

    List<ZcwjDO> getRecommendList(Long id, Integer limit);

    void publishZcwj(Long id);

    void offShelfZcwj(Long id, String reason);
}
