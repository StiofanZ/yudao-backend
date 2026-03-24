package cn.iocoder.yudao.module.report.dal.mysql.bbhc;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.report.controller.admin.bbhc.vo.BbhcPageReqVO;
import cn.iocoder.yudao.module.report.dal.dataobject.bbhc.GhBbsjHcLsDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GhBbsjHcLsMapper extends BaseMapperX<GhBbsjHcLsDO> {

    default GhBbsjHcLsDO selectByPcbh(Long tenantId, String pcbh) {
        return selectOne(new LambdaQueryWrapperX<GhBbsjHcLsDO>()
                .eq(GhBbsjHcLsDO::getTenantId, tenantId)
                .eq(GhBbsjHcLsDO::getPcbh, pcbh)
                .eq(GhBbsjHcLsDO::getDeleted, false));
    }

    default PageResult<GhBbsjHcLsDO> selectPage(BbhcPageReqVO reqVO, Long tenantId) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GhBbsjHcLsDO>()
                .eqIfPresent(GhBbsjHcLsDO::getTenantId, tenantId)
                .likeIfPresent(GhBbsjHcLsDO::getBbbm, reqVO.getBbbm())
                .likeIfPresent(GhBbsjHcLsDO::getBbmc, reqVO.getBbmc())
                .eqIfPresent(GhBbsjHcLsDO::getZxlx, reqVO.getZxlx())
                .eqIfPresent(GhBbsjHcLsDO::getYwrq, reqVO.getYwrq())
                .likeIfPresent(GhBbsjHcLsDO::getPcbh, reqVO.getPcbh())
                .eq(GhBbsjHcLsDO::getDeleted, false)
                .orderByDesc(GhBbsjHcLsDO::getGdsj)
                .orderByDesc(GhBbsjHcLsDO::getId));
    }
}
