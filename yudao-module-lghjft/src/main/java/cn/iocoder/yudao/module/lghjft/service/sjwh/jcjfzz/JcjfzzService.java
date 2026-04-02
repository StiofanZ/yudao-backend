package cn.iocoder.yudao.module.lghjft.service.sjwh.jcjfzz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jcjfzz.vo.JcjfzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jcjfzz.vo.JcjfzzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.jcjfzz.JcjfzzDO;
import jakarta.validation.Valid;

import java.util.List;

public interface JcjfzzService {
    Long createJcjfzz(@Valid JcjfzzSaveReqVO createReqVO);

    void updateJcjfzz(@Valid JcjfzzSaveReqVO updateReqVO);

    void deleteJcjfzz(Long id);

    void deleteJcjfzzListByIds(List<Long> ids);

    JcjfzzDO getJcjfzz(Long id);

    PageResult<JcjfzzDO> getJcjfzzPage(JcjfzzPageReqVO pageReqVO);
}
