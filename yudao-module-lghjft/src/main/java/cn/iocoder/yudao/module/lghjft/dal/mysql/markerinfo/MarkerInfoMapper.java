package cn.iocoder.yudao.module.lghjft.dal.mysql.markerinfo;

import java.util.*;

import cn.hutool.core.util.StrUtil;
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
        LambdaQueryWrapperX<MarkerInfoDO> wrapper = new LambdaQueryWrapperX<MarkerInfoDO>()
                .orderByDesc(MarkerInfoDO::getId);

        // 如果 searchKey 非空，则添加 (name LIKE ? OR address LIKE ?)
        if (StrUtil.isNotBlank(reqVO.getSearchKey())) {

            String key = "%" + reqVO.getSearchKey() + "%";
            wrapper.and(w -> w.like(MarkerInfoDO::getName, key)
                    .or()
                    .like(MarkerInfoDO::getAddress, key)
                   );
        }
        wrapper.eqIfPresent(MarkerInfoDO::getJobtime, reqVO.getJobtime())
                .eqIfPresent(MarkerInfoDO::getGrade, reqVO.getGrade())
                .eqIfPresent(MarkerInfoDO::getXzqhDm, reqVO.getXzqhDm());

        return selectPage(reqVO, wrapper);
    }

}