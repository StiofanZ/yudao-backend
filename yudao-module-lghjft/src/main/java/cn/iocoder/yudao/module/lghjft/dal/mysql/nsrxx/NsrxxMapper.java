package cn.iocoder.yudao.module.lghjft.dal.mysql.nsrxx;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nsrxx.NsrxxDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 纳税人信息 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface NsrxxMapper extends BaseMapperX<NsrxxDO> {

    default NsrxxDO selectByNsrsbh(String nsrsbh) {
        return selectOne(
                new LambdaQueryWrapperX<NsrxxDO>()
                        .lt(NsrxxDO::getNsrztDm, "07")
                        .and(w -> w.eq(NsrxxDO::getNsrsbh, nsrsbh)
                                .or()
                                .eq(NsrxxDO::getShxydm, nsrsbh))
                        .orderByAsc(NsrxxDO::getKzztdjlxDm)
                        .last("limit 1")
        );
    }

}
