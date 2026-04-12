package cn.iocoder.yudao.module.lghjft.service.xejf.xejfhbsb;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfhbsb.vo.XejfhbsbPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfhbsb.vo.XejfhbsbSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejfhbsb.XejfhbsbDO;
import jakarta.validation.Valid;

import java.util.List;

public interface XejfhbsbService {

    Long createXejfhbsb(@Valid XejfhbsbSaveReqVO createReqVO);

    void updateXejfhbsb(@Valid XejfhbsbSaveReqVO updateReqVO);

    void deleteXejfhbsb(Long id);

    void deleteXejfhbsbListByIds(List<Long> ids);

    XejfhbsbDO getXejfhbsb(Long id);

    PageResult<XejfhbsbDO> getXejfhbsbPage(XejfhbsbPageReqVO pageReqVO);
}
