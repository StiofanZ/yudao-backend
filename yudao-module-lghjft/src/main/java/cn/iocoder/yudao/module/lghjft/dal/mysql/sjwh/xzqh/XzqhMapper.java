package cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.xzqh;

import java.util.*;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.xzqh.vo.XzqhListReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.xzqh.XzqhDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 行政区划 Mapper
 *
 * @author 李文军
 */
@Mapper
public interface XzqhMapper extends BaseMapperX<XzqhDO> {

    default List<XzqhDO> selectList(XzqhListReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<XzqhDO>()
                .eqIfPresent(XzqhDO::getXzqhmc, reqVO.getXzqhmc())
                .eqIfPresent(XzqhDO::getSjxzqhDm, reqVO.getSjxzqhDm())
                .eqIfPresent(XzqhDO::getXzqhjb, reqVO.getXzqhjb())
                .eqIfPresent(XzqhDO::getXzqhDm,reqVO.getXzqhDm())
                );
    }

	default XzqhDO selectBySjxzqhDmAndXzqhmc(Long sjxzqhDm, String xzqhmc) {
	    return selectOne(XzqhDO::getSjxzqhDm, sjxzqhDm, XzqhDO::getXzqhmc, xzqhmc);
	}

    default Long selectCountBySjxzqhDm(String sjxzqhDm) {
        return selectCount(XzqhDO::getSjxzqhDm, sjxzqhDm);
    }

}