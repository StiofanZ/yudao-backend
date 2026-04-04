package cn.iocoder.yudao.module.lghjft.service.jfcl.yhbfhzjkjl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfhzjkjl.vo.YhbfhzjkjlPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfhzjkjl.vo.YhbfhzjkjlSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.yhbfhzjkjl.YhbfhzjkjlDO;

import java.util.List;

public interface YhbfhzjkjlService {

    YhbfhzjkjlDO selectYhbfhzJkjlByYhbfhzJkjlId(Long yhbfhzJkjlId);

//    List<YhbfhzjkjlDO> selectYhbfhzJkjlList(YhbfhzjkjlDO yhbfhzjkjlDO);

    int insertYhbfhzJkjl(YhbfhzjkjlDO yhbfhzjkjlDO);

    int updateYhbfhzJkjl(YhbfhzjkjlDO yhbfhzjkjlDO);

    int deleteYhbfhzJkjlByYhbfhzJkjlIds(Long[] yhbfhzJkjlIds);

    int deleteYhbfhzJkjlByYhbfhzJkjlId(Long yhbfhzJkjlId);

    // 适配分页
    PageResult<YhbfhzjkjlDO> selectYhbfhzJkjlPage(YhbfhzjkjlPageReqVO pageReqVO);

    // 适配导出
    List<YhbfhzjkjlDO> selectYhbfhzJkjlList(YhbfhzjkjlPageReqVO pageReqVO);

    // 适配VO
    Long insertYhbfhzJkjl(YhbfhzjkjlSaveReqVO createReqVO);

    void updateYhbfhzJkjl(YhbfhzjkjlSaveReqVO updateReqVO);

    void deleteYhbfhzJkjlByYhbfhzJkjlIds(List<Long> ids);
}
