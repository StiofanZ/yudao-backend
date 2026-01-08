package cn.iocoder.yudao.module.lghjft.service.sjwh.yhwd;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.yhwd.vo.YhwdPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.yhwd.vo.YhwdSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.yhwd.YhwdDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 银行网点 Service 接口
 *
 * @author 李文军
 */
public interface YhwdService {

    /**
     * 创建银行网点
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createYhwd(@Valid YhwdSaveReqVO createReqVO);

    /**
     * 更新银行网点
     *
     * @param updateReqVO 更新信息
     */
    void updateYhwd(@Valid YhwdSaveReqVO updateReqVO);

    /**
     * 删除银行网点
     *
     * @param id 编号
     */
    void deleteYhwd(Long id);

    /**
    * 批量删除银行网点
    *
    * @param ids 编号
    */
    void deleteYhwdListByIds(List<Long> ids);

    /**
     * 获得银行网点
     *
     * @param id 编号
     * @return 银行网点
     */
    YhwdDO getYhwd(Long id);

    /**
     * 获得银行网点分页
     *
     * @param pageReqVO 分页查询
     * @return 银行网点分页
     */
    PageResult<YhwdDO> getYhwdPage(YhwdPageReqVO pageReqVO);

}