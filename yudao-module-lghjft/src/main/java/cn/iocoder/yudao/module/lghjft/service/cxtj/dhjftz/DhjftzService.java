package cn.iocoder.yudao.module.lghjft.service.cxtj.dhjftz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dhjftz.vo.DhjftzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.dhjftz.DhjftzDO;

public interface DhjftzService {

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
