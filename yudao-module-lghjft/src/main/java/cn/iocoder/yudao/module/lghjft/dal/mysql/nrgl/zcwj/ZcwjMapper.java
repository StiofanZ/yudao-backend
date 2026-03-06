package cn.iocoder.yudao.module.lghjft.dal.mysql.nrgl.zcwj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.zcwj.vo.ZcwjReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.zcwj.ZcwjDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.util.StringUtils;

import java.util.List;

@Mapper
public interface ZcwjMapper extends BaseMapperX<ZcwjDO> {

    default PageResult<ZcwjDO> selectPage(ZcwjReqVO reqVO) {
        LambdaQueryWrapperX<ZcwjDO> queryWrapper = new LambdaQueryWrapperX<ZcwjDO>()
                .likeIfPresent(ZcwjDO::getTitle, reqVO.getTitle())
                .likeIfPresent(ZcwjDO::getTags, reqVO.getTags())
                .likeIfPresent(ZcwjDO::getVersionNo, reqVO.getVersionNo())
                .eqIfPresent(ZcwjDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ZcwjDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(ZcwjDO::getFbbm, reqVO.getFbbm());
        if (StringUtils.hasText(reqVO.getKeyword())) {
            queryWrapper.and(wrapper -> wrapper.like(ZcwjDO::getTitle, reqVO.getKeyword())
                    .or().like(ZcwjDO::getSummary, reqVO.getKeyword())
                    .or().like(ZcwjDO::getTags, reqVO.getKeyword())
                    .or().like(ZcwjDO::getSearchKeywords, reqVO.getKeyword()));
        }
        return selectPage(reqVO, queryWrapper.orderByDesc(ZcwjDO::getSort)
                .orderByDesc(ZcwjDO::getUpdateTime)
                .orderByDesc(ZcwjDO::getId));
    }

    default List<ZcwjDO> selectPublishedList(ZcwjReqVO reqVO) {
        LambdaQueryWrapperX<ZcwjDO> queryWrapper = new LambdaQueryWrapperX<ZcwjDO>()
                .eq(ZcwjDO::getStatus, 2)
                .eqIfPresent(ZcwjDO::getFbbm, reqVO.getFbbm())
                .eqIfPresent(ZcwjDO::getDeptId, reqVO.getDeptId());
        if (StringUtils.hasText(reqVO.getKeyword())) {
            queryWrapper.and(wrapper -> wrapper.like(ZcwjDO::getTitle, reqVO.getKeyword())
                    .or().like(ZcwjDO::getSummary, reqVO.getKeyword())
                    .or().like(ZcwjDO::getTags, reqVO.getKeyword())
                    .or().like(ZcwjDO::getSearchKeywords, reqVO.getKeyword()));
        }
        return selectList(queryWrapper.orderByDesc(ZcwjDO::getSort)
                .orderByDesc(ZcwjDO::getReadCount)
                .orderByDesc(ZcwjDO::getId));
    }
}
