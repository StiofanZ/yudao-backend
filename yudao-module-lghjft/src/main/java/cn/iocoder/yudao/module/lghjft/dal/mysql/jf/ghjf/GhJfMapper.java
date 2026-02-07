package cn.iocoder.yudao.module.lghjft.dal.mysql.jf.ghjf;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.jf.ghjf.vo.GhJfReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jf.ghjf.GhJfDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 税务入库 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface GhJfMapper extends BaseMapperX<GhJfDO> {

    default PageResult<GhJfDO> selectPage(GhJfReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GhJfDO>()
                .eqIfPresent(GhJfDO::getSpuuid, reqVO.getSpuuid())
                .eqIfPresent(GhJfDO::getDjxh, reqVO.getDjxh())
                .eqIfPresent(GhJfDO::getShxydm, reqVO.getShxydm())
                .likeIfPresent(GhJfDO::getNsrmc, reqVO.getNsrmc())
                .eqIfPresent(GhJfDO::getDeptId, reqVO.getDeptId())
                .betweenIfPresent(GhJfDO::getRkrq, reqVO.getRkrq())
                .eqIfPresent(GhJfDO::getPzhm, reqVO.getPzhm())
                .eqIfPresent(GhJfDO::getHkpch, reqVO.getHkpch())
                .orderByDesc(GhJfDO::getGhjfId));
    }

}
