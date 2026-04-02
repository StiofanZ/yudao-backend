package cn.iocoder.yudao.module.lghjft.dal.mysql.ghcbj.cbjqltz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjqltz.vo.CbjqltzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.cbjqltz.CbjqltzDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CbjqltzMapper extends BaseMapperX<CbjqltzDO> {

    default PageResult<CbjqltzDO> selectPage(CbjqltzPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CbjqltzDO>()
                .eqIfPresent(CbjqltzDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(CbjqltzDO::getShxydm, reqVO.getShxydm())
                .eqIfPresent(CbjqltzDO::getNsrmc, reqVO.getNsrmc())
                .eqIfPresent(CbjqltzDO::getDjxh, reqVO.getDjxh())
                .orderByDesc(CbjqltzDO::getDjxh));
    }
}
