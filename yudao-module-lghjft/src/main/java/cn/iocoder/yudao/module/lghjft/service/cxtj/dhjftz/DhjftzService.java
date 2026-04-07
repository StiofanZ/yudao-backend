package cn.iocoder.yudao.module.lghjft.service.cxtj.dhjftz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dhjftz.vo.DhjftzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dhjftz.vo.DhjftzSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.dhjftz.DhjftzDO;
import jakarta.validation.Valid;

public interface DhjftzService {

    /**
     * 创建到户经费台账
     *
     * @param createReqVO 创建信息
     * @return 登记序号
     */
    String createDhjftz(@Valid DhjftzSaveReqVO createReqVO);

    /**
     * 更新到户经费台账
     *
     * @param updateReqVO 更新信息
     */
    void updateDhjftz(@Valid DhjftzSaveReqVO updateReqVO);

    /**
     * 批量删除到户经费台账 — V1: deleteDhjftzByDeptIds
     *
     * @param deptIds 工会机构代码数组
     */
    void deleteDhjftzByDeptIds(String[] deptIds);

    /**
     * 获得到户经费台账 — V1: selectDhjftzByDeptId
     *
     * @param deptId 工会机构代码
     * @return 到户经费台账
     */
    DhjftzDO getDhjftzByDeptId(String deptId);

    /**
     * 获得到户经费台账分页
     *
     * @param pageReqVO 分页查询
     * @return 到户经费台账分页
     */
    PageResult<DhjftzDO> getDhjftzPage(DhjftzPageReqVO pageReqVO);
}
