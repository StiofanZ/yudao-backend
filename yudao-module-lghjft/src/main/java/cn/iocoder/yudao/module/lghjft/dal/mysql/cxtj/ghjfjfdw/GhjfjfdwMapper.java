package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.ghjfjfdw;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ghjfjfdw.vo.GhjfjfdwPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.ghjfjfdw.GhjfjfdwDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GhjfjfdwMapper extends BaseMapperX<GhjfjfdwDO> {

    default PageResult<GhjfjfdwDO> selectPage(GhjfjfdwPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GhjfjfdwDO>()
                .eqIfPresent(GhjfjfdwDO::getDjxh, reqVO.getDjxh())
                .eqIfPresent(GhjfjfdwDO::getShxydm, reqVO.getShxydm())
                .eqIfPresent(GhjfjfdwDO::getNsrmc, reqVO.getNsrmc())
                .eqIfPresent(GhjfjfdwDO::getDeptId, reqVO.getDeptId())
                .orderByDesc(GhjfjfdwDO::getDjxh));
    }
}
