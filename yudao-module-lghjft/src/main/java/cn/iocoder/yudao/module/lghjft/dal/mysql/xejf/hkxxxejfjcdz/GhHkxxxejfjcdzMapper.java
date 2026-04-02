package cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.hkxxxejfjcdz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfjcdz.vo.GhHkxxxejfjcdzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.hkxxxejfjcdz.GhHkxxxejfjcdzDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GhHkxxxejfjcdzMapper extends BaseMapperX<GhHkxxxejfjcdzDO> {

    default PageResult<GhHkxxxejfjcdzDO> selectPage(GhHkxxxejfjcdzPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GhHkxxxejfjcdzDO>()
                .eqIfPresent(GhHkxxxejfjcdzDO::getHkxxId, reqVO.getHkxxId())
                .eqIfPresent(GhHkxxxejfjcdzDO::getHkpch, reqVO.getHkpch())
                .eqIfPresent(GhHkxxxejfjcdzDO::getJfqj, reqVO.getJfqj())
                .eqIfPresent(GhHkxxxejfjcdzDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(GhHkxxxejfjcdzDO::getThbj, reqVO.getThbj())
                .orderByDesc(GhHkxxxejfjcdzDO::getHkxxId));
    }
}
