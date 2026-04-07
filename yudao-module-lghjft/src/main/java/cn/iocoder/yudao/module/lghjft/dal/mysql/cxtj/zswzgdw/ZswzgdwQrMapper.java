package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.zswzgdw;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.zswzgdw.ZswzgdwQrDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ZswzgdwQrMapper extends BaseMapperX<ZswzgdwQrDO> {

    /**
     * 根据 djxh 查询关联的确认记录
     */
    default List<ZswzgdwQrDO> selectByDjxh(String djxh) {
        return selectList(new QueryWrapper<ZswzgdwQrDO>().eq("djxh", djxh));
    }

    /**
     * 根据 djxh 删除关联的确认记录
     */
    default int deleteByDjxh(String djxh) {
        return delete(new QueryWrapper<ZswzgdwQrDO>().eq("djxh", djxh));
    }

    /**
     * 根据多个 djxh 批量删除关联的确认记录
     */
    default int deleteByDjxhs(List<String> djxhs) {
        return delete(new QueryWrapper<ZswzgdwQrDO>().in("djxh", djxhs));
    }
}
