package cn.gszs.yudao.module.lghjft.dal.mysql.wfsqtfsq;

import cn.gszs.yudao.module.lghjft.dal.dataobject.wfsqtfsq.WfSqTfsqmxDO;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 申请-退费申请明细 Mapper
 *
 * @author 李文军
 */
@Mapper
public interface WfSqTfsqmxMapper extends BaseMapperX<WfSqTfsqmxDO> {

    default List<WfSqTfsqmxDO> selectListById(Long id) {
        return selectList(WfSqTfsqmxDO::getId, id);
    }

    default int deleteById(Long id) {
        return delete(WfSqTfsqmxDO::getId, id);
    }

    default int deleteByIds(List<Long> ids) {
        return deleteBatch(WfSqTfsqmxDO::getId, ids);
    }

}