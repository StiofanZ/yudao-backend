package cn.iocoder.yudao.module.report.dal.mysql.bbhc;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.report.controller.admin.bbhc.vo.BbhcPageReqVO;
import cn.iocoder.yudao.module.report.dal.dataobject.bbhc.GhBbsjHcDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GhBbsjHcMapper extends BaseMapperX<GhBbsjHcDO> {

    default GhBbsjHcDO selectByZy(Long tenantId, String bbid, String zxlx, java.time.LocalDate ywrq, String cszy) {
        return selectOne(new LambdaQueryWrapperX<GhBbsjHcDO>()
                .eq(GhBbsjHcDO::getTenantId, tenantId)
                .eq(GhBbsjHcDO::getBbid, bbid)
                .eq(GhBbsjHcDO::getZxlx, zxlx)
                .eq(GhBbsjHcDO::getYwrq, ywrq)
                .eq(GhBbsjHcDO::getCszy, cszy)
                .eq(GhBbsjHcDO::getDeleted, false));
    }

    default GhBbsjHcDO selectByPcbh(Long tenantId, String pcbh) {
        return selectOne(new LambdaQueryWrapperX<GhBbsjHcDO>()
                .eq(GhBbsjHcDO::getTenantId, tenantId)
                .eq(GhBbsjHcDO::getPcbh, pcbh)
                .eq(GhBbsjHcDO::getDeleted, false));
    }

    default PageResult<GhBbsjHcDO> selectPage(BbhcPageReqVO reqVO, Long tenantId) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GhBbsjHcDO>()
                .eqIfPresent(GhBbsjHcDO::getTenantId, tenantId)
                .likeIfPresent(GhBbsjHcDO::getBbbm, reqVO.getBbbm())
                .likeIfPresent(GhBbsjHcDO::getBbmc, reqVO.getBbmc())
                .eqIfPresent(GhBbsjHcDO::getZxlx, reqVO.getZxlx())
                .eqIfPresent(GhBbsjHcDO::getYwrq, reqVO.getYwrq())
                .likeIfPresent(GhBbsjHcDO::getPcbh, reqVO.getPcbh())
                .eq(GhBbsjHcDO::getDeleted, false)
                .orderByDesc(GhBbsjHcDO::getScsj)
                .orderByDesc(GhBbsjHcDO::getId));
    }
}
