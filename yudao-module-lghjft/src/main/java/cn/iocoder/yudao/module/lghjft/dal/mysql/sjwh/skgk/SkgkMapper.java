package cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.skgk;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;

import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.skgk.vo.SkgkPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.skgk.SkgkDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 收款国库 Mapper
 *
 * @author 李文军
 */
@Mapper
public interface SkgkMapper extends BaseMapperX<SkgkDO> {

    default PageResult<SkgkDO> selectPage(SkgkPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SkgkDO>()
                .eqIfPresent(SkgkDO::getSkgkDm, reqVO.getSkgkDm())
                .eqIfPresent(SkgkDO::getSkgkmc, reqVO.getSkgkmc())
                .eqIfPresent(SkgkDO::getSkgkjc, reqVO.getSkgkjc())
                .eqIfPresent(SkgkDO::getXzqhDm, reqVO.getXzqhDm())
                .orderByDesc(SkgkDO::getGkId));
    }

}