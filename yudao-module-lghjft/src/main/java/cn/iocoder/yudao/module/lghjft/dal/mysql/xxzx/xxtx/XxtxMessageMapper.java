package cn.iocoder.yudao.module.lghjft.dal.mysql.xxzx.xxtx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.QueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.message.XxtxMessageMyPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xxzx.xxtx.vo.message.XxtxMessagePageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xxzx.xxtx.XxtxMessageDO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Mapper
public interface XxtxMessageMapper extends BaseMapperX<XxtxMessageDO> {

    default PageResult<XxtxMessageDO> selectPage(XxtxMessagePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<XxtxMessageDO>()
                .eqIfPresent(XxtxMessageDO::getUserId, reqVO.getUserId())
                .eqIfPresent(XxtxMessageDO::getUserType, reqVO.getUserType())
                .likeIfPresent(XxtxMessageDO::getTemplateCode, reqVO.getTemplateCode())
                .eqIfPresent(XxtxMessageDO::getTemplateType, reqVO.getTemplateType())
                .betweenIfPresent(XxtxMessageDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(XxtxMessageDO::getId));
    }

    default PageResult<XxtxMessageDO> selectPage(XxtxMessageMyPageReqVO reqVO, Long userId, Integer userType) {
        return selectPage(reqVO, new LambdaQueryWrapperX<XxtxMessageDO>()
                .eqIfPresent(XxtxMessageDO::getReadStatus, reqVO.getReadStatus())
                .betweenIfPresent(XxtxMessageDO::getCreateTime, reqVO.getCreateTime())
                .eq(XxtxMessageDO::getUserId, userId)
                .eq(XxtxMessageDO::getUserType, userType)
                .orderByDesc(XxtxMessageDO::getId));
    }

    default int updateListRead(Collection<Long> ids, Long userId, Integer userType) {
        return update(new XxtxMessageDO().setReadStatus(true).setReadTime(LocalDateTime.now()),
                new LambdaQueryWrapperX<XxtxMessageDO>()
                        .in(XxtxMessageDO::getId, ids)
                        .eq(XxtxMessageDO::getUserId, userId)
                        .eq(XxtxMessageDO::getUserType, userType)
                        .eq(XxtxMessageDO::getReadStatus, false));
    }

    default int updateListRead(Long userId, Integer userType) {
        return update(new XxtxMessageDO().setReadStatus(true).setReadTime(LocalDateTime.now()),
                new LambdaQueryWrapperX<XxtxMessageDO>()
                        .eq(XxtxMessageDO::getUserId, userId)
                        .eq(XxtxMessageDO::getUserType, userType)
                        .eq(XxtxMessageDO::getReadStatus, false));
    }

    default List<XxtxMessageDO> selectUnreadListByUserIdAndUserType(Long userId, Integer userType, Integer size) {
        return selectList(new QueryWrapperX<XxtxMessageDO>() // 由于要使用 limitN 语句，所以只能用 QueryWrapperX
                .eq("user_id", userId)
                .eq("user_type", userType)
                .eq("read_status", false)
                .orderByDesc("id").limitN(size));
    }

    default Long selectUnreadCountByUserIdAndUserType(Long userId, Integer userType) {
        return selectCount(new LambdaQueryWrapperX<XxtxMessageDO>()
                .eq(XxtxMessageDO::getReadStatus, false)
                .eq(XxtxMessageDO::getUserId, userId)
                .eq(XxtxMessageDO::getUserType, userType));
    }

}
