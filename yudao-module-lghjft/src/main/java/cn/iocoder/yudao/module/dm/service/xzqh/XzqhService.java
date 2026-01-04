package cn.iocoder.yudao.module.dm.service.xzqh;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.dm.controller.admin.xzqh.vo.*;
import cn.iocoder.yudao.module.dm.dal.dataobject.xzqh.XzqhDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 行政区划 Service 接口
 *
 * @author 李文军
 */
public interface XzqhService {

//    /**
//     * 创建行政区划
//     *
//     * @param createReqVO 创建信息
//     * @return 编号
//     */
//    Long createXzqh(@Valid XzqhSaveReqVO createReqVO);
//
//    /**
//     * 更新行政区划
//     *
//     * @param updateReqVO 更新信息
//     */
//    void updateXzqh(@Valid XzqhSaveReqVO updateReqVO);
//
//    /**
//     * 删除行政区划
//     *
//     * @param id 编号
//     */
//    void deleteXzqh(Long id);
//
//
//    /**
//     * 获得行政区划
//     *
//     * @param id 编号
//     * @return 行政区划
//     */
//    XzqhDO getXzqh(Long id);

    /**
     * 获得行政区划列表
     *
     * @param listReqVO 查询条件
     * @return 行政区划列表
     */
    List<XzqhDO> getXzqhList(XzqhListReqVO listReqVO);

}