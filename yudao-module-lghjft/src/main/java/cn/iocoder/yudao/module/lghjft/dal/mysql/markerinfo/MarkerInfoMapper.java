package cn.iocoder.yudao.module.lghjft.dal.mysql.markerinfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.markerinfo.MarkerInfoDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.lghjft.controller.admin.markerinfo.vo.*;

/**
 * 高德地图标注点信息 Mapper
 *
 * @author 李文军
 */
@Mapper
public interface MarkerInfoMapper extends BaseMapperX<MarkerInfoDO> {

    default PageResult<MarkerInfoDO> selectPage(MarkerInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MarkerInfoDO>()
                .likeIfPresent(MarkerInfoDO::getName, reqVO.getName())
                .eqIfPresent(MarkerInfoDO::getPhone, reqVO.getPhone())
                .eqIfPresent(MarkerInfoDO::getAddress, reqVO.getAddress())
                .eqIfPresent(MarkerInfoDO::getLng, reqVO.getLng())
                .eqIfPresent(MarkerInfoDO::getLat, reqVO.getLat())
                .eqIfPresent(MarkerInfoDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(MarkerInfoDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MarkerInfoDO::getIsDeleted, reqVO.getIsDeleted())
                .orderByDesc(MarkerInfoDO::getId));
    }

}