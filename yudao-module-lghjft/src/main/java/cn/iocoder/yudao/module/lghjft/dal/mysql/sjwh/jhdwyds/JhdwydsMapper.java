package cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.jhdwyds;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jhdwyds.vo.JhdwydsPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.jhdwyds.vo.JhdwydsReqVO;
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
                .eqIfPresent(JhdwydsDO::getShxydm, reqVO.getShxydm())
                .eqIfPresent(JhdwydsDO::getGhshxydm, reqVO.getGhshxydm())
                .eqIfPresent(JhdwydsDO::getGhmc, reqVO.getGhmc())
                .eqIfPresent(JhdwydsDO::getGhlxr, reqVO.getGhlxr())
                .eqIfPresent(JhdwydsDO::getGhlxdh, reqVO.getGhlxdh())
                .eqIfPresent(JhdwydsDO::getClghbj, reqVO.getClghbj())
                .eqIfPresent(JhdwydsDO::getClghrq, reqVO.getClghrq())
                .eqIfPresent(JhdwydsDO::getGhztDm, reqVO.getGhztDm())
                .orderByDesc(JhdwydsDO::getJhdwId));
    }

    default List<JhdwydsDO> selectList(JhdwydsReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<JhdwydsDO>()
                .eqIfPresent(JhdwydsDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(JhdwydsDO::getShxydm, reqVO.getShxydm())
                .eqIfPresent(JhdwydsDO::getGhshxydm, reqVO.getGhshxydm())
                .eqIfPresent(JhdwydsDO::getGhmc, reqVO.getGhmc())
                .eqIfPresent(JhdwydsDO::getGhlxr, reqVO.getGhlxr())
                .eqIfPresent(JhdwydsDO::getGhlxdh, reqVO.getGhlxdh())
                .eqIfPresent(JhdwydsDO::getClghbj, reqVO.getClghbj())
                .eqIfPresent(JhdwydsDO::getClghrq, reqVO.getClghrq())
                .eqIfPresent(JhdwydsDO::getGhztDm, reqVO.getGhztDm())
                .orderByDesc(JhdwydsDO::getJhdwId));
    }

}