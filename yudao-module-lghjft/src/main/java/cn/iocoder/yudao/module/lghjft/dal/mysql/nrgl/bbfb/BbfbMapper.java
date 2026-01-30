package cn.iocoder.yudao.module.lghjft.dal.mysql.nrgl.bbfb;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.bbfb.vo.BbfbListReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.bbfb.BbfbDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 版本发布 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface BbfbMapper extends BaseMapperX<BbfbDO> {

    default List<BbfbDO> selectList(BbfbListReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<BbfbDO>()
                .likeIfPresent(BbfbDO::getTitle, reqVO.getTitle())
                .likeIfPresent(BbfbDO::getVersion, reqVO.getVersion())
                .eqIfPresent(BbfbDO::getStatus, reqVO.getStatus())
                .orderByDesc(BbfbDO::getId));
    }

}
