package cn.iocoder.yudao.module.lghjft.service.markerinfo;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.lghjft.controller.admin.markerinfo.vo.*;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.markerinfo.MarkerInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 高德地图标注点信息 Service 接口
 *
 * @author 李文军
 */
public interface MarkerInfoService {

    /**
     * 创建高德地图标注点信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMarkerInfo(@Valid MarkerInfoSaveReqVO createReqVO);

    /**
     * 更新高德地图标注点信息
     *
     * @param updateReqVO 更新信息
     */
    void updateMarkerInfo(@Valid MarkerInfoSaveReqVO updateReqVO);

    /**
     * 删除高德地图标注点信息
     *
     * @param id 编号
     */
    void deleteMarkerInfo(Long id);

    /**
    * 批量删除高德地图标注点信息
    *
    * @param ids 编号
    */
    void deleteMarkerInfoListByIds(List<Long> ids);

    /**
     * 获得高德地图标注点信息
     *
     * @param id 编号
     * @return 高德地图标注点信息
     */
    MarkerInfoDO getMarkerInfo(Long id);

    /**
     * 获得高德地图标注点信息分页
     *
     * @param pageReqVO 分页查询
     * @return 高德地图标注点信息分页
     */
    PageResult<MarkerInfoDO> getMarkerInfoPage(MarkerInfoPageReqVO pageReqVO);


    List<MarkerInfoDO> getCountyData(Integer xzqhDm);
}