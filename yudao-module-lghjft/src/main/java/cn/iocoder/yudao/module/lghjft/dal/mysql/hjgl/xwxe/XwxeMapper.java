package cn.iocoder.yudao.module.lghjft.dal.mysql.hjgl.xwxe;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.xwxe.vo.XwxePageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hjgl.jcxx.GhHjJcxxDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface XwxeMapper extends BaseMapperX<GhHjJcxxDO> {

    default PageResult<GhHjJcxxDO> selectPage(XwxePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GhHjJcxxDO>()
                .eqIfPresent(GhHjJcxxDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(GhHjJcxxDO::getShxydm, reqVO.getShxydm())
                .likeIfPresent(GhHjJcxxDO::getNsrmc, reqVO.getNsrmc())
                .eqIfPresent(GhHjJcxxDO::getHjfl6Dm, reqVO.getHjfl6Dm())
                .eqIfPresent(GhHjJcxxDO::getHjfl8Dm, reqVO.getHjfl8Dm())
                .eqIfPresent(GhHjJcxxDO::getHjfl9Dm, reqVO.getHjfl9Dm())
                .betweenIfPresent(GhHjJcxxDO::getHjfl7Dm, reqVO.getBeginHjfl7Dm(), reqVO.getEndHjfl7Dm())
                .orderByDesc(GhHjJcxxDO::getHjfl7Dm)
                .orderByDesc(GhHjJcxxDO::getUpdateTime));
    }
}
