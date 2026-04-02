package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.dhjftz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dhjftz.vo.DhjftzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.dhjftz.DhjftzDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DhjftzMapper extends BaseMapperX<DhjftzDO> {

    default PageResult<DhjftzDO> selectPage(DhjftzPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DhjftzDO>()
                .eqIfPresent(DhjftzDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(DhjftzDO::getDjxh, reqVO.getDjxh())
                .likeIfPresent(DhjftzDO::getNsrmc, reqVO.getNsrmc())
                .eqIfPresent(DhjftzDO::getJsbj, reqVO.getJsbj())
                .orderByDesc(DhjftzDO::getGhjfId));
    }
}
