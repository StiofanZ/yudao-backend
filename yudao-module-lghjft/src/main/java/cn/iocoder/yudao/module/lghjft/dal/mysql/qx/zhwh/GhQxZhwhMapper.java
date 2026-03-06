package cn.iocoder.yudao.module.lghjft.dal.mysql.qx.zhwh;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.zhwh.vo.ZhwhPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.zhwh.GhQxZhwhDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GhQxZhwhMapper extends BaseMapperX<GhQxZhwhDO> {

    default PageResult<GhQxZhwhDO> selectPage(ZhwhPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GhQxZhwhDO>()
                .likeIfPresent(GhQxZhwhDO::getApplyNo, reqVO.getApplyNo())
                .eqIfPresent(GhQxZhwhDO::getDlzhId, reqVO.getDlzhId())
                .eqIfPresent(GhQxZhwhDO::getDeptId, reqVO.getDeptId())
                .likeIfPresent(GhQxZhwhDO::getDjxh, reqVO.getDjxh())
                .likeIfPresent(GhQxZhwhDO::getShxydm, reqVO.getShxydm())
                .likeIfPresent(GhQxZhwhDO::getDwmc, reqVO.getDwmc())
                .eqIfPresent(GhQxZhwhDO::getStatus, reqVO.getStatus())
                .eqIfPresent(GhQxZhwhDO::getSyncStatus, reqVO.getSyncStatus())
                .orderByDesc(GhQxZhwhDO::getCreateTime)
                .orderByDesc(GhQxZhwhDO::getId));
    }
}
