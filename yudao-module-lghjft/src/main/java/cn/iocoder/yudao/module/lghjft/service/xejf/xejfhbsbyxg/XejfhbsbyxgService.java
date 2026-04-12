package cn.iocoder.yudao.module.lghjft.service.xejf.xejfhbsbyxg;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfhbsbyxg.vo.XejfhbsbyxgPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfhbsbyxg.vo.XejfhbsbyxgSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejfhbsbyxg.XejfhbsbyxgDO;
import jakarta.validation.Valid;

import java.util.List;

public interface XejfhbsbyxgService {

    Long createXejfhbsbyxg(@Valid XejfhbsbyxgSaveReqVO createReqVO);

    void updateXejfhbsbyxg(@Valid XejfhbsbyxgSaveReqVO updateReqVO);

    void deleteXejfhbsbyxg(Long id);

    void deleteXejfhbsbyxgListByIds(List<Long> ids);

    XejfhbsbyxgDO getXejfhbsbyxg(Long id);

    PageResult<XejfhbsbyxgDO> getXejfhbsbyxgPage(XejfhbsbyxgPageReqVO pageReqVO);
}
