package cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.hkxx;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.hkxx.HkxxQrszDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HkxxQrszMapper extends BaseMapperX<HkxxQrszDO> {

    default HkxxQrszDO selectLatestByHkxxId(Integer hkxxId) {
        List<HkxxQrszDO> records = selectList(new LambdaQueryWrapperX<HkxxQrszDO>()
                .eq(HkxxQrszDO::getHkxxId, hkxxId)
                .orderByDesc(HkxxQrszDO::getId));
        return records.isEmpty() ? null : records.get(0);
    }
}
