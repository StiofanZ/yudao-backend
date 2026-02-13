package cn.iocoder.yudao.module.lghjft.dal.mysql.nsrxx;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.nsrxx.NsrxxDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * 纳税人信息 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface NsrxxMapper extends BaseMapperX<NsrxxDO> {

    default List<NsrxxDO> selectListByShxydm(String shxydm) {
        return selectList(
                new LambdaQueryWrapperX<NsrxxDO>()
                        .lt(NsrxxDO::getNsrztDm, "07")
                        .and(w -> w.eq(NsrxxDO::getNsrsbh, shxydm)
                                .or()
                                .eq(NsrxxDO::getShxydm, shxydm))
        );
    }

    default NsrxxDO selectOneByShxydm(String shxydm) {
        List<NsrxxDO> nsrxxDOList = selectListByShxydm(shxydm);
        if (CollectionUtils.isEmpty(nsrxxDOList)) return null;
        return nsrxxDOList.get(0);
    }

    default NsrxxDO selectOneByDjxh(String djxh) {
        return selectOne(
                new LambdaQueryWrapperX<NsrxxDO>()
                        .eq(NsrxxDO::getDjxh,djxh)
        );
    }

    default List<NsrxxDO> selectListByDjxhs(Collection<String> djxhs) {
        return selectList(new LambdaQueryWrapper<NsrxxDO>().in(NsrxxDO::getDjxh, djxhs));
    }
}
