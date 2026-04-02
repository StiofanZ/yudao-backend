package cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.dmwh.fpbl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.fpbl.vo.CsFpblPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.dmwh.fpbl.CsFpblDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CsFpblMapper extends BaseMapperX<CsFpblDO> {

    default PageResult<CsFpblDO> selectPage(CsFpblPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CsFpblDO>()
                .likeIfPresent(CsFpblDO::getMs, reqVO.getMs())
                .likeIfPresent(CsFpblDO::getTj, reqVO.getTj())
                .eqIfPresent(CsFpblDO::getMrbz, reqVO.getMrbz())
                .orderByAsc(CsFpblDO::getYxj));
    }
}
