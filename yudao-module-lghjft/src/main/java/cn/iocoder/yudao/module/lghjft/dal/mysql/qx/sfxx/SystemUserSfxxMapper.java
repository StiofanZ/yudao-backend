package cn.iocoder.yudao.module.lghjft.dal.mysql.qx.sfxx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.qx.sfxx.vo.SfxxReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.qx.sfxx.SystemUserSfxxDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SystemUserSfxxMapper extends BaseMapperX<SystemUserSfxxDO> {

    default PageResult<SystemUserSfxxDO> selectPage(SfxxPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SystemUserSfxxDO>()
                .eqIfPresent(SystemUserSfxxDO::getDlzhId, reqVO.getDlzhId())
                .likeIfPresent(SystemUserSfxxDO::getDjxh, reqVO.getDjxh())
                .eqIfPresent(SystemUserSfxxDO::getSflx, reqVO.getSflx())
                .eqIfPresent(SystemUserSfxxDO::getQxlx, reqVO.getQxlx())
                .eqIfPresent(SystemUserSfxxDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(SystemUserSfxxDO::getStatus, reqVO.getStatus())
                .orderByDesc(SystemUserSfxxDO::getId));
    }

    default List<SystemUserSfxxDO> selectList(SfxxReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<SystemUserSfxxDO>()
                .eqIfPresent(SystemUserSfxxDO::getDlzhId, reqVO.getDlzhId())
                .likeIfPresent(SystemUserSfxxDO::getDjxh, reqVO.getDjxh())
                .eqIfPresent(SystemUserSfxxDO::getSflx, reqVO.getSflx())
                .eqIfPresent(SystemUserSfxxDO::getQxlx, reqVO.getQxlx())
                .eqIfPresent(SystemUserSfxxDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(SystemUserSfxxDO::getStatus, reqVO.getStatus())
                .orderByDesc(SystemUserSfxxDO::getId));
    }
}
