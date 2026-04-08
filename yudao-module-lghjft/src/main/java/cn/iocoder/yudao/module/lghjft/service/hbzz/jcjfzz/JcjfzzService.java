package cn.iocoder.yudao.module.lghjft.service.hbzz.jcjfzz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jcjfzz.vo.JcjfzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jcjfzz.vo.JcjfzzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jcjfzz.vo.JcjfzzSaveReqVO;
import jakarta.validation.Valid;

import java.util.List;

public interface JcjfzzService {
    Long createJcjfzz(@Valid JcjfzzSaveReqVO createReqVO);

    void updateJcjfzz(@Valid JcjfzzSaveReqVO updateReqVO);

    void deleteJcjfzz(Long id);

    void deleteJcjfzzListByIds(List<Long> ids);

    JcjfzzResVO getJcjfzz(Long id);

    PageResult<JcjfzzResVO> getJcjfzzPage(JcjfzzPageReqVO pageReqVO);
}
