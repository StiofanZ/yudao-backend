package cn.iocoder.yudao.module.lghjft.service.cxtj.zswzgdw;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zswzgdw.vo.ZswzgdwPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zswzgdw.vo.ZswzgdwSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.zswzgdw.ZswzgdwDO;
import jakarta.validation.Valid;

public interface ZswzgdwService {

    /**
     * 创建征收未主管单位（含 zswzgdw_qr 级联新增）
     *
     * @param createReqVO 创建信息
     * @return djxh
     */
    String createZswzgdw(@Valid ZswzgdwSaveReqVO createReqVO);

    /**
     * 更新征收未主管单位（含 zswzgdw_qr 级联更新）
     *
     * @param updateReqVO 更新信息
     */
    void updateZswzgdw(@Valid ZswzgdwSaveReqVO updateReqVO);

    /**
     * 删除征收未主管单位（含 zswzgdw_qr 级联删除）
     *
     * @param djxh 登记序号
     */
    void deleteZswzgdw(String djxh);

    /**
     * 获得征收未主管单位
     *
     * @param djxh 登记序号
     * @return 征收未主管单位
     */
    ZswzgdwDO getZswzgdw(String djxh);

    /**
     * 获得征收未主管单位分页
     *
     * @param pageReqVO 分页查询
     * @return 征收未主管单位分页
     */
    PageResult<ZswzgdwDO> getZswzgdwPage(ZswzgdwPageReqVO pageReqVO);
}
