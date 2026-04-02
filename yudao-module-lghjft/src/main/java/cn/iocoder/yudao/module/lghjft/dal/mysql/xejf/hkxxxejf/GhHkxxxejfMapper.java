package cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.hkxxxejf;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejf.vo.GhHkxxxejfPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.hkxxxejf.GhHkxxxejfDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GhHkxxxejfMapper extends BaseMapperX<GhHkxxxejfDO> {

    default PageResult<GhHkxxxejfDO> selectPage(GhHkxxxejfPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GhHkxxxejfDO>()
                .eqIfPresent(GhHkxxxejfDO::getHkxxId, reqVO.getHkxxId())
                .eqIfPresent(GhHkxxxejfDO::getHkpch, reqVO.getHkpch())
                .eqIfPresent(GhHkxxxejfDO::getJfqj, reqVO.getJfqj())
                .eqIfPresent(GhHkxxxejfDO::getLx, reqVO.getLx())
                .eqIfPresent(GhHkxxxejfDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(GhHkxxxejfDO::getThbj, reqVO.getThbj())
                .orderByDesc(GhHkxxxejfDO::getHkxxId));
    }
}
