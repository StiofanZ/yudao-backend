package cn.iocoder.yudao.module.lghjft.dal.mysql.nrgl.nrxx;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.nrgl.nrxx.vo.NrxxListReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.nrxx.NrxxDO;

/**
 * 内容管理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface NrxxMapper extends BaseMapperX<NrxxDO> {

    default List<NrxxDO> selectList(NrxxListReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<NrxxDO>()
                .likeIfPresent(NrxxDO::getTitle, reqVO.getTitle())
                .eqIfPresent(NrxxDO::getType, reqVO.getType())
                .eqIfPresent(NrxxDO::getStatus, reqVO.getStatus())
                .orderByAsc(NrxxDO::getSort)
                .orderByDesc(NrxxDO::getId));
    }

}
