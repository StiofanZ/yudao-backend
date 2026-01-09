package cn.iocoder.yudao.module.lghjft.dal.mysql.auth;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.auth.GhCsSsoDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GhCsSsoMapper extends BaseMapperX<GhCsSsoDO> {

    default GhCsSsoDO selectByLghUser(String lghUser) {
        return selectOne(GhCsSsoDO::getLghUser, lghUser);
    }

}
