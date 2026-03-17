package cn.iocoder.yudao.module.lghjft.service.sjwh.ydsdw;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.ydsdw.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.ydsdw.ydsdwDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 应代收单位 Service 接口
 *
 * @author 李文军
 */
public interface ydsdwService {

    /**
     * 创建应代收单位
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createydsdw(@Valid ydsdwSaveReqVO createReqVO);

    /**
     * 更新应代收单位
     *
     * @param updateReqVO 更新信息
     */
    void updateydsdw(@Valid ydsdwSaveReqVO updateReqVO);

    /**
     * 删除应代收单位
     *
     * @param id 编号
     */
    void deleteydsdw(Integer id);

    /**
    * 批量删除应代收单位
    *
    * @param ids 编号
    */
    void deleteydsdwListByIds(List<Integer> ids);

    /**
     * 获得应代收单位
     *
     * @param id 编号
     * @return 应代收单位
     */
    ydsdwDO getydsdw(Integer id);

    /**
     * 获得应代收单位分页
     *
     * @param pageReqVO 分页查询
     * @return 应代收单位分页
     */
    PageResult<ydsdwDO> getydsdwPage(ydsdwPageReqVO pageReqVO);

    List<ydsdwDO> getJhdwydsList(ydsdwSaveReqVO pageReqVO);
}