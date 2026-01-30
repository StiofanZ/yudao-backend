package cn.iocoder.yudao.module.lghjft.service.nrgl.zcjd;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcjd.vo.ZcjdCreateReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcjd.vo.ZcjdReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcjd.vo.ZcjdUpdateReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.zcjd.ZcjdDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 政策解读 Service 接口
 *
 * @author 芋道源码
 */
public interface ZcjdService {

    /**
     * 创建政策解读
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createZcjd(@Valid ZcjdCreateReqVO createReqVO);

    /**
     * 更新政策解读
     *
     * @param updateReqVO 更新信息
     */
    void updateZcjd(@Valid ZcjdUpdateReqVO updateReqVO);

    /**
     * 删除政策解读
     *
     * @param id 编号
     */
    void deleteZcjd(Long id);

    /**
     * 获得政策解读
     *
     * @param id 编号
     * @return 政策解读
     */
    ZcjdDO getZcjd(Long id);


    /**
     * 获得政策解读列表
     *
     * @param listReqVO 查询条件
     * @return 政策解读列表
     */
    List<ZcjdDO> getZcjdList(ZcjdReqVO listReqVO);

    /**
     * 获得政策解读分页列表
     *
     * @param reqVO 查询条件
     * @return 政策解读列表
     */
    PageResult<ZcjdDO> getZcjdPage(ZcjdReqVO reqVO);

    /**
     * 发布政策解读
     *
     * @param id 编号
     */
    void publishZcjd(Long id);

    /**
     * 下架政策解读
     *
     * @param id 编号
     * @param reason 下架原因
     */
    void offShelfZcjd(Long id, String reason);

    /**
     * 审核政策解读
     *
     * @param id 编号
     * @param status 状态
     */
    void auditZcjd(Long id, Integer status);

}
