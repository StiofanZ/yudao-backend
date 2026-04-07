package cn.iocoder.yudao.module.lghjft.service.cxtj.jfmx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JfmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JfmxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JfmxSaveReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JftzmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.JftzmxResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.SzdzhdPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jfmx.vo.SzdzhdResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.jfmx.CxtjJfmxDO;
import jakarta.validation.Valid;
import java.util.List;

public interface CxtjJfmxService {

    /**
     * 创建经费明细
     *
     * @param createReqVO 创建信息
     * @return spuuid
     */
    String createJfmx(@Valid JfmxSaveReqVO createReqVO);

    /**
     * 更新经费明细
     *
     * @param updateReqVO 更新信息
     */
    void updateJfmx(@Valid JfmxSaveReqVO updateReqVO);

    /**
     * 删除经费明细
     *
     * @param spuuid spuuid
     */
    void deleteJfmx(String spuuid);

    /**
     * 批量删除经费明细
     *
     * @param spuuids spuuid 列表
     */
    void deleteJfmxList(List<String> spuuids);

    /**
     * 获得经费明细
     *
     * @param spuuid spuuid
     * @return 经费明细
     */
    CxtjJfmxDO getJfmx(String spuuid);

    /**
     * 获得经费明细分页
     *
     * @param pageReqVO 分页查询
     * @return 经费明细分页
     */
    PageResult<JfmxResVO> getJfmxPage(JfmxPageReqVO pageReqVO);

    /**
     * 获得经费台账明细分页
     *
     * @param pageReqVO 分页查询
     * @return 经费台账明细分页
     */
    PageResult<JftzmxResVO> getJftzmxPage(JftzmxPageReqVO pageReqVO);

    /**
     * 获得省总到账核对分页
     *
     * @param pageReqVO 分页查询
     * @return 省总到账核对分页
     */
    PageResult<SzdzhdResVO> getSzdzhdPage(SzdzhdPageReqVO pageReqVO);
}
