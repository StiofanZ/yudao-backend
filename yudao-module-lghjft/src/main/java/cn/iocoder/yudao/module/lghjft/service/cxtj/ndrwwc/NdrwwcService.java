package cn.iocoder.yudao.module.lghjft.service.cxtj.ndrwwc;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ndrwwc.vo.NdrwwcPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ndrwwc.vo.NdrwwcSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.ndrwwc.NdrwwcDO;
import jakarta.validation.Valid;

public interface NdrwwcService {

    /**
     * 创建分上缴周期统计
     *
     * @param createReqVO 创建信息
     * @return nd
     */
    String createNdrwwc(@Valid NdrwwcSaveReqVO createReqVO);

    /**
     * 更新分上缴周期统计
     *
     * @param updateReqVO 更新信息
     */
    void updateNdrwwc(@Valid NdrwwcSaveReqVO updateReqVO);

    /**
     * 删除分上缴周期统计
     *
     * @param nd 年度
     */
    void deleteNdrwwc(String nd);

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
