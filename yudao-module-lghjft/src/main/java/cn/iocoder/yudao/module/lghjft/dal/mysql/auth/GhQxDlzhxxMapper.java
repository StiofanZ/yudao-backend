package cn.iocoder.yudao.module.lghjft.dal.mysql.auth;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.auth.GhQxDlzhxxDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GhQxDlzhxxMapper extends BaseMapperX<GhQxDlzhxxDO> {

    default GhQxDlzhxxDO selectByYhzh(String yhzh) {
        return selectOne(GhQxDlzhxxDO::getYhzh, yhzh);
    }

    default GhQxDlzhxxDO selectByLxdh(String lxdh) {
        return selectOne(GhQxDlzhxxDO::getLxdh, lxdh);
    }

    default GhQxDlzhxxDO selectByYhyx(String yhyx) {
        return selectOne(GhQxDlzhxxDO::getYhyx, yhyx);
    }

    default GhQxDlzhxxDO selectByShxydm(String shxydm) {
        return selectOne(GhQxDlzhxxDO::getShxydm, shxydm);
    }
}
