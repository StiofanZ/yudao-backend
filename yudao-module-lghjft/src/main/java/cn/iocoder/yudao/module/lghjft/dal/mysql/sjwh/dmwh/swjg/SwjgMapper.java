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
                .likeIfPresent(SwjgDO::getSwjgmc, reqVO.getSwjgmc())
                .likeIfPresent(SwjgDO::getSwjgjc, reqVO.getSwjgjc())
                .eqIfPresent(SwjgDO::getDz, reqVO.getDz())
                .eqIfPresent(SwjgDO::getYzbm, reqVO.getYzbm())
                .eqIfPresent(SwjgDO::getLxr, reqVO.getLxr())
                .eqIfPresent(SwjgDO::getLxdh, reqVO.getLxdh())
                .eqIfPresent(SwjgDO::getSxfzh, reqVO.getSxfzh())
                .eqIfPresent(SwjgDO::getSxfhm, reqVO.getSxfhm())
                .eqIfPresent(SwjgDO::getSxfhh, reqVO.getSxfhh())
                .eqIfPresent(SwjgDO::getSxfyh, reqVO.getSxfyh())
                .eqIfPresent(SwjgDO::getSjswjgDm, reqVO.getSjswjgDm())
                .eqIfPresent(SwjgDO::getJcjbj, reqVO.getJcjbj())
                .eqIfPresent(SwjgDO::getGhjgDm, reqVO.getGhjgDm())
                .eqIfPresent(SwjgDO::getSxh, reqVO.getSxh())
                .eqIfPresent(SwjgDO::getJym, reqVO.getJym()));
    }

}
