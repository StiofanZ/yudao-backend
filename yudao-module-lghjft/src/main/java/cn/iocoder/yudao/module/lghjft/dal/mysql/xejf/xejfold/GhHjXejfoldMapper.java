package cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xejfold;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfold.vo.GhHjXejfoldPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejfold.GhHjXejfoldDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GhHjXejfoldMapper extends BaseMapperX<GhHjXejfoldDO> {

    default PageResult<GhHjXejfoldDO> selectPage(GhHjXejfoldPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GhHjXejfoldDO>()
                .eqIfPresent(GhHjXejfoldDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(GhHjXejfoldDO::getDjxh, reqVO.getDjxh())
                .eqIfPresent(GhHjXejfoldDO::getShxydm, reqVO.getShxydm())
                .likeIfPresent(GhHjXejfoldDO::getNsrmc, reqVO.getNsrmc())
                .eqIfPresent(GhHjXejfoldDO::getLxr, reqVO.getLxr())
                .eqIfPresent(GhHjXejfoldDO::getLxdh, reqVO.getLxdh())
                .eqIfPresent(GhHjXejfoldDO::getGhlbDm, reqVO.getGhlbDm())
                .eqIfPresent(GhHjXejfoldDO::getXtlbDm, reqVO.getXtlbDm())
                .eqIfPresent(GhHjXejfoldDO::getHjfl4Dm, reqVO.getHjfl4Dm())
                .eqIfPresent(GhHjXejfoldDO::getHjfl5Dm, reqVO.getHjfl5Dm())
                .eqIfPresent(GhHjXejfoldDO::getHjfl6Dm, reqVO.getHjfl6Dm())
                .eqIfPresent(GhHjXejfoldDO::getJcghzh, reqVO.getJcghzh())
                .eqIfPresent(GhHjXejfoldDO::getJcghhh, reqVO.getJcghhh())
                .orderByDesc(GhHjXejfoldDO::getDeptId));
    }
}
