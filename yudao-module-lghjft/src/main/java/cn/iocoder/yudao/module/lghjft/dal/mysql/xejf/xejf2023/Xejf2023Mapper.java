package cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xejf2023;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf2023.vo.Xejf2023PageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejf2023.vo.XejftjResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejf2023.Xejf2023DO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Xejf2023Mapper extends BaseMapperX<Xejf2023DO> {

    default PageResult<Xejf2023DO> selectPage(Xejf2023PageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<Xejf2023DO>()
                .eqIfPresent(Xejf2023DO::getGhjfId, reqVO.getGhjfId())
                .eqIfPresent(Xejf2023DO::getDjxh, reqVO.getDjxh())
                .eqIfPresent(Xejf2023DO::getShxydm, reqVO.getShxydm())
                .likeIfPresent(Xejf2023DO::getNsrmc, reqVO.getNsrmc())
                .eqIfPresent(Xejf2023DO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(Xejf2023DO::getZspmDm, reqVO.getZspmDm())
                .orderByDesc(Xejf2023DO::getGhjfId));
    }

    List<Xejf2023DO> selectPageTz(@Param("req") Xejf2023PageReqVO req);

    List<XejftjResVO> selectXetjList(@Param("req") Xejf2023PageReqVO req);
}
