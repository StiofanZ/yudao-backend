package cn.iocoder.yudao.module.lghjft.service.qx.dlzh;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dlzh.vo.DlzhPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dlzh.vo.DlzhResetPasswordReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.dlzh.vo.DlzhSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.dlzh.GhQxDlzhDO;
import jakarta.validation.Valid;

import java.util.List;

public interface GhQxDlzhService {

    Long createDlzh(@Valid DlzhSaveReqVO createReqVO);

    void updateDlzh(@Valid DlzhSaveReqVO updateReqVO);

    void resetPassword(@Valid DlzhResetPasswordReqVO reqVO);

    void deleteDlzh(Long id);

    void deleteDlzhListByIds(List<Long> ids);

    GhQxDlzhDO getDlzh(Long id);

    PageResult<GhQxDlzhDO> getDlzhPage(DlzhPageReqVO pageReqVO);

}

