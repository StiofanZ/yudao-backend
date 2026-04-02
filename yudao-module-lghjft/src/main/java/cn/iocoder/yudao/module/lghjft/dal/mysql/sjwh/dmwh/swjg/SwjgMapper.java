package cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.dmwh.swjg;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.swjg.vo.SwjgListReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.dmwh.swjg.SwjgDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 税务机关 Mapper
 *
 * @author 李文军
 */
@Mapper
public interface SwjgMapper extends BaseMapperX<SwjgDO> {

    default List<SwjgDO> selectList(SwjgListReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<SwjgDO>()
                .eqIfPresent(SwjgDO::getSwjgmc, reqVO.getSwjgmc())
                .eqIfPresent(SwjgDO::getSxfzh, reqVO.getSxfzh())
                .orderByDesc(SwjgDO::getSwjgDm));
    }

    default SwjgDO selectBySjswjgDmAndSwjgmc(Long sjswjgDm, String swjgmc) {
        return selectOne(SwjgDO::getSjswjgDm, sjswjgDm, SwjgDO::getSwjgmc, swjgmc);
    }

    default Long selectCountBySjswjgDm(String sjswjgDm) {
        return selectCount(SwjgDO::getSjswjgDm, sjswjgDm);
    }

}