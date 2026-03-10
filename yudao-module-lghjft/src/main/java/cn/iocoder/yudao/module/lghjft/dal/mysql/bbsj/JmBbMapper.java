package cn.iocoder.yudao.module.lghjft.dal.mysql.bbsj;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.bbsj.JmBbDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JmBbMapper extends BaseMapperX<JmBbDO> {

    default JmBbDO selectByBbbm(String bbbm) {
        return selectOne(new LambdaQueryWrapperX<JmBbDO>()
                .eq(JmBbDO::getCode, bbbm)
                .eq(JmBbDO::getDelFlag, 0));
    }
}
