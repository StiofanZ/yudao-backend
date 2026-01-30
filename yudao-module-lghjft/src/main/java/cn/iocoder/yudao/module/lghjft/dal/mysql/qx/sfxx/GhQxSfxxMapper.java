package cn.iocoder.yudao.module.lghjft.dal.mysql.qx.sfxx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.sfxx.GhQxSfxxDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GhQxSfxxMapper extends BaseMapperX<GhQxSfxxDO> {

    default PageResult<GhQxSfxxDO> selectPage(SfxxPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GhQxSfxxDO>()
                .eqIfPresent(GhQxSfxxDO::getDlzhId, reqVO.getDlzhId())
                .likeIfPresent(GhQxSfxxDO::getDjxh, reqVO.getDjxh())
                .eqIfPresent(GhQxSfxxDO::getSflx, reqVO.getSflx())
                .eqIfPresent(GhQxSfxxDO::getQxlx, reqVO.getQxlx())
                .eqIfPresent(GhQxSfxxDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(GhQxSfxxDO::getStatus, reqVO.getStatus())
                .orderByDesc(GhQxSfxxDO::getId));
    }

}
