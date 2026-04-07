package cn.iocoder.yudao.module.lghjft.service.cxtj.zswzgdw;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zswzgdw.vo.ZswzgdwPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zswzgdw.vo.ZswzgdwSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.zswzgdw.ZswzgdwDO;
import jakarta.validation.Valid;

public interface ZswzgdwService {

    /**
     * 创建征收未主管单位（含 zswzgdw_qr 级联新增）
     */
    String createZswzgdw(@Valid ZswzgdwSaveReqVO createReqVO);

    /**
     * 更新征收未主管单位（含 zswzgdw_qr 级联更新，ON DUPLICATE KEY UPDATE）
     */
    void updateZswzgdw(@Valid ZswzgdwSaveReqVO updateReqVO);

    /**
     * 批量删除征收未主管单位（含 zswzgdw_qr 级联删除）
     */
    void deleteZswzgdwByDjxhs(String[] djxhs);

    /**
     * 删除单个征收未主管单位（含 zswzgdw_qr 级联删除）
     */
    void deleteZswzgdw(String djxh);

    /**
     * 获得征收未主管单位（WITH cascade — 包含 zswzgdwQrList）
     */
    ZswzgdwDO getZswzgdw(String djxh);

    /**
     * 获得征收未主管单位分页
     */
    PageResult<ZswzgdwDO> getZswzgdwPage(ZswzgdwPageReqVO pageReqVO);
}
