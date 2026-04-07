package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.yjhxx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.yjhxx.vo.GhYjhxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.yjhxx.GhYjhxxDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GhYjhxxMapper extends BaseMapperX<GhYjhxxDO> {

    default PageResult<GhYjhxxDO> selectPage(GhYjhxxPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GhYjhxxDO>()
                .likeIfPresent(GhYjhxxDO::getNsrmc, reqVO.getNsrmc())
                .eqIfPresent(GhYjhxxDO::getShxydm, reqVO.getShxydm())
                .eqIfPresent(GhYjhxxDO::getNsrsbh, reqVO.getNsrsbh())
                .eqIfPresent(GhYjhxxDO::getYxbj, reqVO.getYxbj())
                .orderByDesc(GhYjhxxDO::getJhxxId));
    }
}
