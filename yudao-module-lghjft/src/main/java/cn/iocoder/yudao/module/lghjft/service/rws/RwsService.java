package cn.iocoder.yudao.module.lghjft.service.rws;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.lghjft.controller.admin.rws.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.rws.RwsDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

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
    Integer createRws(@Valid RwsSaveReqVO createReqVO);

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
    void deleteRws(Integer id);

    /**
    * 批量删除年度任务
    *
    * @param ids 编号
    */
    void deleteRwsListByIds(List<Integer> ids);

    /**
     * 获得年度任务
     *
     * @param id 编号
     * @return 年度任务
     */
    RwsDO getRws(Integer id);

    /**
     * 获得年度任务分页
     *
     * @param pageReqVO 分页查询
     * @return 年度任务分页
     */
    PageResult<RwsDO> getRwsPage(RwsPageReqVO pageReqVO);

}