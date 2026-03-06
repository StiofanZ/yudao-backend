package cn.iocoder.yudao.module.lghjft.dal.mysql.nrgl.wtfk;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nrgl.wtfk.GhNrglWtfkClmxDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GhNrglWtfkClmxMapper extends BaseMapperX<GhNrglWtfkClmxDO> {

    default List<GhNrglWtfkClmxDO> selectListByWtfkId(Long wtfkId) {
        return selectList(new LambdaQueryWrapperX<GhNrglWtfkClmxDO>()
                .eq(GhNrglWtfkClmxDO::getWtfkId, wtfkId)
                .orderByAsc(GhNrglWtfkClmxDO::getCreateTime));
    }

    default void deleteByWtfkId(Long wtfkId) {
        delete(new LambdaQueryWrapperX<GhNrglWtfkClmxDO>()
                .eq(GhNrglWtfkClmxDO::getWtfkId, wtfkId));
    }
}
