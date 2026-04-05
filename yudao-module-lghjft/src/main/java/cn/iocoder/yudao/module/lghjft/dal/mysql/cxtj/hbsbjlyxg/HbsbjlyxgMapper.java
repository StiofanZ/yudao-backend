package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.hbsbjlyxg;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.hbsbjlyxg.vo.HbsbjlyxgPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.hbsbjlyxg.HbsbjlyxgDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HbsbjlyxgMapper extends BaseMapperX<HbsbjlyxgDO> {

    default PageResult<HbsbjlyxgDO> selectPage(HbsbjlyxgPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<HbsbjlyxgDO>()
                .eq(HbsbjlyxgDO::getThbj, "Y")
                .eq(HbsbjlyxgDO::getXgbj, "1")
                .eqIfPresent(HbsbjlyxgDO::getDeptId, reqVO.getDeptId())
                .likeIfPresent(HbsbjlyxgDO::getHkpch, reqVO.getHkpch())
                .likeIfPresent(HbsbjlyxgDO::getHm, reqVO.getHm())
                .likeIfPresent(HbsbjlyxgDO::getXhm, reqVO.getXhm())
                .orderByDesc(HbsbjlyxgDO::getHkxxId));
    }
}
