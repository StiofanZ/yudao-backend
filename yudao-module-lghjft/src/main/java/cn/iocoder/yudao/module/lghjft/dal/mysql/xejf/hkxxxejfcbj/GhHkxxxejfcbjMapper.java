package cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.hkxxxejfcbj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.hkxxxejfcbj.vo.GhHkxxxejfcbjPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.hkxxxejfcbj.GhHkxxxejfcbjDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GhHkxxxejfcbjMapper extends BaseMapperX<GhHkxxxejfcbjDO> {

    default PageResult<GhHkxxxejfcbjDO> selectPage(GhHkxxxejfcbjPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GhHkxxxejfcbjDO>()
                .eqIfPresent(GhHkxxxejfcbjDO::getHkxxId, reqVO.getHkxxId())
                .eqIfPresent(GhHkxxxejfcbjDO::getHkpch, reqVO.getHkpch())
                .eqIfPresent(GhHkxxxejfcbjDO::getJfqj, reqVO.getJfqj())
                .eqIfPresent(GhHkxxxejfcbjDO::getSjdm, reqVO.getSjdm())
                .eqIfPresent(GhHkxxxejfcbjDO::getDeptId, reqVO.getDeptId())
                .orderByDesc(GhHkxxxejfcbjDO::getHkxxId));
    }
}
