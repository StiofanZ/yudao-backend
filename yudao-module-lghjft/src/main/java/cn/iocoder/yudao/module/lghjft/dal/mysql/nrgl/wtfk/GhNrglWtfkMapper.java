package cn.iocoder.yudao.module.lghjft.dal.mysql.nrgl.wtfk;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.wtfk.vo.GhNrglWtfkPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.wtfk.GhNrglWtfkDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GhNrglWtfkMapper extends BaseMapperX<GhNrglWtfkDO> {

    default PageResult<GhNrglWtfkDO> selectPage(GhNrglWtfkPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GhNrglWtfkDO>()
                .likeIfPresent(GhNrglWtfkDO::getFkbh, reqVO.getFkbh())
                .eqIfPresent(GhNrglWtfkDO::getYhId, reqVO.getYhId())
                .likeIfPresent(GhNrglWtfkDO::getYhmc, reqVO.getYhmc())
                .eqIfPresent(GhNrglWtfkDO::getLx, reqVO.getLx())
                .likeIfPresent(GhNrglWtfkDO::getNr, reqVO.getNr())
                .eqIfPresent(GhNrglWtfkDO::getLxdh, reqVO.getLxdh())
                .eqIfPresent(GhNrglWtfkDO::getLxyx, reqVO.getLxyx())
                .inIfPresent(GhNrglWtfkDO::getZt, reqVO.getStatuses())
                .eqIfPresent(GhNrglWtfkDO::getClrId, reqVO.getClrId())
                .betweenIfPresent(GhNrglWtfkDO::getClsj, reqVO.getClsj())
                .likeIfPresent(GhNrglWtfkDO::getClsm, reqVO.getClsm())
                .betweenIfPresent(GhNrglWtfkDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GhNrglWtfkDO::getId));
    }
}
