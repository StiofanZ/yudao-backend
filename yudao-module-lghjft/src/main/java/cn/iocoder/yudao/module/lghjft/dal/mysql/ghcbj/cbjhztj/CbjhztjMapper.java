package cn.iocoder.yudao.module.lghjft.dal.mysql.ghcbj.cbjhztj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjhztj.vo.CbjhztjPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjhztj.vo.CbjhztjhzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjhztj.vo.CbjhztjtjResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.cbjhztj.CbjhztjDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CbjhztjMapper extends BaseMapperX<CbjhztjDO> {

    default PageResult<CbjhztjDO> selectPage(CbjhztjPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CbjhztjDO>()
                .eqIfPresent(CbjhztjDO::getNd, reqVO.getNd())
                .eqIfPresent(CbjhztjDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(CbjhztjDO::getShxydm, reqVO.getShxydm())
                .eqIfPresent(CbjhztjDO::getDjxh, reqVO.getDjxh())
                .eqIfPresent(CbjhztjDO::getNsrmc, reqVO.getNsrmc())
                .orderByDesc(CbjhztjDO::getGhjfId));
    }

    List<CbjhztjtjResVO> selectTjList(@Param("req") CbjhztjPageReqVO req);

    List<CbjhztjhzResVO> selectHzList(@Param("req") CbjhztjPageReqVO req);
}
