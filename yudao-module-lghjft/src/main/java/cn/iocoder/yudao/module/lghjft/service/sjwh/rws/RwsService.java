package cn.iocoder.yudao.module.lghjft.service.sjwh.rws;

import java.util.*;

import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.rws.vo.RwsPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.rws.vo.RwsSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.rws.RwsDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 年度任务 Service 接口
 *
 * @author 李文军
 */
public interface RwsService {

    /**
     * 创建年度任务
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRws(@Valid RwsSaveReqVO createReqVO);

    /**
     * 更新年度任务
     *
     * @param updateReqVO 更新信息
     */
    void updateRws(@Valid RwsSaveReqVO updateReqVO);

    /**
     * 删除年度任务
     *
     * @param id 编号
     */
    void deleteRws(Long id);

    /**
    * 批量删除年度任务
    *
    * @param ids 编号
    */
    void deleteRwsListByIds(List<Long> ids);

    /**
     * 获得年度任务
     *
     * @param id 编号
     * @return 年度任务
     */
    RwsDO getRws(Long id);

    /**
     * 获得年度任务分页
     *
     * @param pageReqVO 分页查询
     * @return 年度任务分页
     */
    PageResult<RwsDO> getRwsPage(RwsPageReqVO pageReqVO);

}
