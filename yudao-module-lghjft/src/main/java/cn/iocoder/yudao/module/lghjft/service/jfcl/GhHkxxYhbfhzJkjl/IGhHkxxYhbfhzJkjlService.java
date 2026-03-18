package cn.iocoder.yudao.module.lghjft.service.jfcl.GhHkxxYhbfhzJkjl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.GhHkxxYhbfhzJkjl.vo.GhHkxxYhbfhzJkjlPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.GhHkxxYhbfhzJkjl.vo.GhHkxxYhbfhzJkjlSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.hkxxyhbfhzjkjl.GhHkxxYhbfhzJkjl;

import java.util.List;

public interface IGhHkxxYhbfhzJkjlService {

    GhHkxxYhbfhzJkjl selectGhHkxxYhbfhzJkjlByYhbfhzJkjlId(Long yhbfhzJkjlId);

//    List<GhHkxxYhbfhzJkjl> selectGhHkxxYhbfhzJkjlList(GhHkxxYhbfhzJkjl ghHkxxYhbfhzJkjl);

    int insertGhHkxxYhbfhzJkjl(GhHkxxYhbfhzJkjl ghHkxxYhbfhzJkjl);

    int updateGhHkxxYhbfhzJkjl(GhHkxxYhbfhzJkjl ghHkxxYhbfhzJkjl);

    int deleteGhHkxxYhbfhzJkjlByYhbfhzJkjlIds(Long[] yhbfhzJkjlIds);

    int deleteGhHkxxYhbfhzJkjlByYhbfhzJkjlId(Long yhbfhzJkjlId);

    // 适配分页
    PageResult<GhHkxxYhbfhzJkjl> selectGhHkxxYhbfhzJkjlPage(GhHkxxYhbfhzJkjlPageReqVO pageReqVO);

    // 适配导出
    List<GhHkxxYhbfhzJkjl> selectGhHkxxYhbfhzJkjlList(GhHkxxYhbfhzJkjlPageReqVO pageReqVO);

    // 适配VO
    Long insertGhHkxxYhbfhzJkjl(GhHkxxYhbfhzJkjlSaveReqVO createReqVO);

    void updateGhHkxxYhbfhzJkjl(GhHkxxYhbfhzJkjlSaveReqVO updateReqVO);

    void deleteGhHkxxYhbfhzJkjlByYhbfhzJkjlIds(List<Long> ids);
}