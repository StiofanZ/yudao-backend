package cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xejf24;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf24.vo.GhHjXejf24PageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejf24.GhHjXejf24DO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GhHjXejf24Mapper extends BaseMapperX<GhHjXejf24DO> {

    default PageResult<GhHjXejf24DO> selectPage(GhHjXejf24PageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GhHjXejf24DO>()
                .eqIfPresent(GhHjXejf24DO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(GhHjXejf24DO::getDjxh, reqVO.getDjxh())
                .eqIfPresent(GhHjXejf24DO::getShxydm, reqVO.getShxydm())
                .likeIfPresent(GhHjXejf24DO::getNsrmc, reqVO.getNsrmc())
                .eqIfPresent(GhHjXejf24DO::getLxr, reqVO.getLxr())
                .eqIfPresent(GhHjXejf24DO::getLxdh, reqVO.getLxdh())
                .eqIfPresent(GhHjXejf24DO::getGhlbDm, reqVO.getGhlbDm())
                .eqIfPresent(GhHjXejf24DO::getXtlbDm, reqVO.getXtlbDm())
                .eqIfPresent(GhHjXejf24DO::getHjfl4Dm, reqVO.getHjfl4Dm())
                .eqIfPresent(GhHjXejf24DO::getHjfl5Dm, reqVO.getHjfl5Dm())
                .eqIfPresent(GhHjXejf24DO::getHjfl6Dm, reqVO.getHjfl6Dm())
                .eqIfPresent(GhHjXejf24DO::getJcghzh, reqVO.getJcghzh())
                .eqIfPresent(GhHjXejf24DO::getJcghhh, reqVO.getJcghhh())
                .orderByDesc(GhHjXejf24DO::getDeptId));
    }
}
