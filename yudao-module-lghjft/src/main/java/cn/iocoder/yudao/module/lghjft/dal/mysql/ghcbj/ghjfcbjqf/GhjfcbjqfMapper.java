package cn.iocoder.yudao.module.lghjft.dal.mysql.ghcbj.ghjfcbjqf;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.ghjfcbjqf.vo.GhjfcbjqfPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.ghjfcbjqf.GhjfcbjqfDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GhjfcbjqfMapper extends BaseMapperX<GhjfcbjqfDO> {

    default PageResult<GhjfcbjqfDO> selectPage(GhjfcbjqfPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GhjfcbjqfDO>()
                .eqIfPresent(GhjfcbjqfDO::getDjxh, reqVO.getDjxh())
                .eqIfPresent(GhjfcbjqfDO::getShxydm, reqVO.getShxydm())
                .likeIfPresent(GhjfcbjqfDO::getNsrmc, reqVO.getNsrmc())
                .eqIfPresent(GhjfcbjqfDO::getDeptId, reqVO.getDeptId())
                .orderByDesc(GhjfcbjqfDO::getGhjfId));
    }
}
