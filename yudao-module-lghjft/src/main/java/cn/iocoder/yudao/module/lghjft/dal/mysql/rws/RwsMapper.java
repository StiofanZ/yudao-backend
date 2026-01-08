package cn.iocoder.yudao.module.lghjft.dal.mysql.rws;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.rws.RwsDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.lghjft.controller.admin.rws.vo.*;

/**
 * 年度任务 Mapper
 *
 * @author 李文军
 */
@Mapper
public interface RwsMapper extends BaseMapperX<RwsDO> {

    default PageResult<RwsDO> selectPage(RwsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RwsDO>()
                .eqIfPresent(RwsDO::getRwlx, reqVO.getRwlx())
                .eqIfPresent(RwsDO::getNd, reqVO.getNd())
                .eqIfPresent(RwsDO::getDwdm, reqVO.getDwdm())
                .eqIfPresent(RwsDO::getDwmc, reqVO.getDwmc())
                .eqIfPresent(RwsDO::getRws, reqVO.getRws())
                .eqIfPresent(RwsDO::getWcs, reqVO.getWcs())
                .orderByDesc(RwsDO::getRwid));
    }

}