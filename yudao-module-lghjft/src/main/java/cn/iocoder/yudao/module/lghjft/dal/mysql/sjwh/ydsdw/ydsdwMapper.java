package cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.ydsdw;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.ydsdw.ydsdwDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.ydsdw.vo.*;

/**
 * 应代收单位 Mapper
 *
 * @author 李文军
 */
@Mapper
public interface ydsdwMapper extends BaseMapperX<ydsdwDO> {

    default PageResult<ydsdwDO> selectPage(ydsdwPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ydsdwDO>()
                .eqIfPresent(ydsdwDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(ydsdwDO::getShxydm, reqVO.getShxydm())
                .eqIfPresent(ydsdwDO::getNsrmc, reqVO.getNsrmc())
                .eqIfPresent(ydsdwDO::getZgswjDm, reqVO.getZgswjDm())
                .eqIfPresent(ydsdwDO::getZgswskfjDm, reqVO.getZgswskfjDm())
                .eqIfPresent(ydsdwDO::getZgswskfjmc, reqVO.getZgswskfjmc())
                .eqIfPresent(ydsdwDO::getDz, reqVO.getDz())
                .eqIfPresent(ydsdwDO::getDwcwlxr, reqVO.getDwcwlxr())
                .eqIfPresent(ydsdwDO::getDwcwlxdh, reqVO.getDwcwlxdh())
                .eqIfPresent(ydsdwDO::getNsrztDm, reqVO.getNsrztDm())
                .eqIfPresent(ydsdwDO::getZgrs, reqVO.getZgrs())
                .eqIfPresent(ydsdwDO::getGhzgrs, reqVO.getGhzgrs())
                .eqIfPresent(ydsdwDO::getGhshxydm, reqVO.getGhshxydm())
                .eqIfPresent(ydsdwDO::getGhlxDm, reqVO.getGhlxDm())
                .eqIfPresent(ydsdwDO::getGhjflxr, reqVO.getGhjflxr())
                .eqIfPresent(ydsdwDO::getGhjflxdh, reqVO.getGhjflxdh())
                .eqIfPresent(ydsdwDO::getGhzx, reqVO.getGhzx())
                .eqIfPresent(ydsdwDO::getZxlxdh, reqVO.getZxlxdh())
                .eqIfPresent(ydsdwDO::getClghbj, reqVO.getClghbj())
                .eqIfPresent(ydsdwDO::getClghrq, reqVO.getClghrq())
                .eqIfPresent(ydsdwDO::getGhztDm, reqVO.getGhztDm())
                .eqIfPresent(ydsdwDO::getSjghmc, reqVO.getSjghmc())
                .eqIfPresent(ydsdwDO::getJcghzh, reqVO.getJcghzh())
                .eqIfPresent(ydsdwDO::getJcghhm, reqVO.getJcghhm())
                .eqIfPresent(ydsdwDO::getJcghhh, reqVO.getJcghhh())
                .eqIfPresent(ydsdwDO::getJcghyh, reqVO.getJcghyh())
                .eqIfPresent(ydsdwDO::getBz, reqVO.getBz())
                .eqIfPresent(ydsdwDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(ydsdwDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ydsdwDO::getUpdateBy, reqVO.getUpdateBy())
                .eqIfPresent(ydsdwDO::getJdxzDm, reqVO.getJdxzDm())
                .eqIfPresent(ydsdwDO::getSjtbSj, reqVO.getSjtbSj())
                .orderByDesc(ydsdwDO::getJhdwId));
    }
    default List<ydsdwDO> selectList(ydsdwSaveReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ydsdwDO>()
                .eqIfPresent(ydsdwDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(ydsdwDO::getShxydm, reqVO.getShxydm())
                .eqIfPresent(ydsdwDO::getGhshxydm, reqVO.getGhshxydm())
                .eqIfPresent(ydsdwDO::getGhmc, reqVO.getGhmc())
                .eqIfPresent(ydsdwDO::getDwcwlxdh, reqVO.getDwcwlxdh())
                .eqIfPresent(ydsdwDO::getDwcwlxdh, reqVO.getDwcwlxdh())
                .eqIfPresent(ydsdwDO::getClghbj, reqVO.getClghbj())
                .eqIfPresent(ydsdwDO::getClghrq, reqVO.getClghrq())
                .eqIfPresent(ydsdwDO::getGhztDm, reqVO.getGhztDm())
                .orderByDesc(ydsdwDO::getJhdwId));
    }


}