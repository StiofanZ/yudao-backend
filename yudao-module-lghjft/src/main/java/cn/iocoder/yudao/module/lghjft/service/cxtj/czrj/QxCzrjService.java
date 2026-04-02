package cn.iocoder.yudao.module.lghjft.service.cxtj.czrj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.czrj.vo.QxCzrjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.czrj.vo.QxCzrjSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.czrj.QxCzrjDO;
import jakarta.validation.Valid;

import java.util.List;

public interface QxCzrjService {
    Long createQxCzrj(@Valid QxCzrjSaveReqVO createReqVO);

    void updateQxCzrj(@Valid QxCzrjSaveReqVO updateReqVO);

    void deleteQxCzrj(Long id);

    void deleteQxCzrjListByIds(List<Long> ids);

    QxCzrjDO getQxCzrj(Long id);

    PageResult<QxCzrjDO> getQxCzrjPage(QxCzrjPageReqVO pageReqVO);
}
