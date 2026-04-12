package cn.iocoder.yudao.module.lghjft.service.cxtj.ndrwwc;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ndrwwc.vo.NdrwwcPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.ndrwwc.NdrwwcDO;

public interface NdrwwcService {

    /**
     * 获得分上缴周期统计
     *
     * @param nd 年度
     * @return 分上缴周期统计
     */
    NdrwwcDO getNdrwwc(String nd);

    /**
     * 获得分上缴周期统计分页
     *
     * @param pageReqVO 分页查询
     * @return 分上缴周期统计分页
     */
    PageResult<NdrwwcDO> getNdrwwcPage(NdrwwcPageReqVO pageReqVO);
}
