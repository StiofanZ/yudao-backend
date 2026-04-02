package cn.iocoder.yudao.module.lghjft.service.xejf.xejfzzgl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfzzgl.vo.XejfghzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfzzgl.vo.XejfghzzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejfzzgl.XejfghzzDO;
import jakarta.validation.Valid;

import java.util.List;

public interface XejfghzzService {
    String createXejfghzz(@Valid XejfghzzSaveReqVO createReqVO);

    void updateXejfghzz(@Valid XejfghzzSaveReqVO updateReqVO);

    void deleteXejfghzz(String id);

    void deleteXejfghzzListByIds(List<String> ids);

    XejfghzzDO getXejfghzz(String id);

    PageResult<XejfghzzDO> getXejfghzzPage(XejfghzzPageReqVO pageReqVO);
}
