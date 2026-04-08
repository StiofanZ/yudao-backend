package cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.jhdwyds;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jhdwyds.vo.JhdwydsPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jhdwyds.vo.JhdwydsSaveReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.jhdwyds.JhdwydsDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 应代收单位 Mapper
 *
 * @author 李文军
 */
@Mapper
public interface JhdwydsMapper extends BaseMapperX<JhdwydsDO> {

    default PageResult<JhdwydsDO> selectPage(JhdwydsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<JhdwydsDO>()
                .eqIfPresent(JhdwydsDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(JhdwydsDO::getDjxh, reqVO.getDjxh())
                .eqIfPresent(JhdwydsDO::getShxydm, reqVO.getShxydm())
                .likeIfPresent(JhdwydsDO::getNsrmc, reqVO.getNsrmc())
                .eqIfPresent(JhdwydsDO::getZgswjDm, reqVO.getZgswjDm())
                .eqIfPresent(JhdwydsDO::getZgswskfjDm, reqVO.getZgswskfjDm())
                .likeIfPresent(JhdwydsDO::getZgswskfjmc, reqVO.getZgswskfjmc())
                .eqIfPresent(JhdwydsDO::getJdxzDm, reqVO.getJdxzDm())
                .eqIfPresent(JhdwydsDO::getDz, reqVO.getDz())
                .eqIfPresent(JhdwydsDO::getDwcwlxr, reqVO.getDwcwlxr())
                .eqIfPresent(JhdwydsDO::getDwcwlxdh, reqVO.getDwcwlxdh())
                .eqIfPresent(JhdwydsDO::getNsrztDm, reqVO.getNsrztDm())
                .eqIfPresent(JhdwydsDO::getZgrs, reqVO.getZgrs())
                .eqIfPresent(JhdwydsDO::getGhzgrs, reqVO.getGhzgrs())
                .eqIfPresent(JhdwydsDO::getGhshxydm, reqVO.getGhshxydm())
                .likeIfPresent(JhdwydsDO::getGhmc, reqVO.getGhmc())
                .eqIfPresent(JhdwydsDO::getGhlxDm, reqVO.getGhlxDm())
                .eqIfPresent(JhdwydsDO::getGhjflxr, reqVO.getGhjflxr())
                .eqIfPresent(JhdwydsDO::getGhjflxdh, reqVO.getGhjflxdh())
                .eqIfPresent(JhdwydsDO::getGhzx, reqVO.getGhzx())
                .eqIfPresent(JhdwydsDO::getZxlxdh, reqVO.getZxlxdh())
                .eqIfPresent(JhdwydsDO::getClghbj, reqVO.getClghbj())
                .eqIfPresent(JhdwydsDO::getClghrq, reqVO.getClghrq())
                .eqIfPresent(JhdwydsDO::getGhztDm, reqVO.getGhztDm())
                .eqIfPresent(JhdwydsDO::getSjghmc, reqVO.getSjghmc())
                .eqIfPresent(JhdwydsDO::getJcghzh, reqVO.getJcghzh())
                .eqIfPresent(JhdwydsDO::getJcghhm, reqVO.getJcghhm())
                .eqIfPresent(JhdwydsDO::getJcghhh, reqVO.getJcghhh())
                .eqIfPresent(JhdwydsDO::getJcghyh, reqVO.getJcghyh())
                .likeIfPresent(JhdwydsDO::getBz, reqVO.getBz())
                .eqIfPresent(JhdwydsDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(JhdwydsDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(JhdwydsDO::getUpdateBy, reqVO.getUpdateBy())
                .eqIfPresent(JhdwydsDO::getSjtbSj, reqVO.getSjtbSj())
                .orderByDesc(JhdwydsDO::getJhdwId));
    }

    default List<JhdwydsDO> selectList(JhdwydsSaveReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<JhdwydsDO>()
                .eqIfPresent(JhdwydsDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(JhdwydsDO::getShxydm, reqVO.getShxydm())
                .eqIfPresent(JhdwydsDO::getGhshxydm, reqVO.getGhshxydm())
                .eqIfPresent(JhdwydsDO::getGhmc, reqVO.getGhmc())
                .eqIfPresent(JhdwydsDO::getDwcwlxdh, reqVO.getDwcwlxdh())
                .eqIfPresent(JhdwydsDO::getClghbj, reqVO.getClghbj())
                .eqIfPresent(JhdwydsDO::getClghrq, reqVO.getClghrq())
                .eqIfPresent(JhdwydsDO::getGhztDm, reqVO.getGhztDm())
                .orderByDesc(JhdwydsDO::getJhdwId));
    }


}
