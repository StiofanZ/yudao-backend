package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.zgjrgh;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zgjrgh.vo.ZgjrghPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.zgjrgh.ZgjrghDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ZgjrghMapper extends BaseMapperX<ZgjrghDO> {

    default PageResult<ZgjrghDO> selectPage(ZgjrghPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ZgjrghDO>()
                .eqIfPresent(ZgjrghDO::getDjxh, reqVO.getDjxh())
                .eqIfPresent(ZgjrghDO::getShxydm, reqVO.getShxydm())
                .eqIfPresent(ZgjrghDO::getNsrmc, reqVO.getNsrmc())
                .eqIfPresent(ZgjrghDO::getDeptId, reqVO.getDeptId())
                .orderByDesc(ZgjrghDO::getGhjfId));
    }
}
