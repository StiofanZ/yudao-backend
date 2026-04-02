package cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xebftz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebftz.vo.XebfPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xebftz.XebfDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface XebfMapper extends BaseMapperX<XebfDO> {

    default PageResult<XebfDO> selectPage(XebfPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<XebfDO>()
                .eqIfPresent(XebfDO::getGhjfId, reqVO.getGhjfId())
                .eqIfPresent(XebfDO::getJfqj, reqVO.getJfqj())
                .eqIfPresent(XebfDO::getDjxh, reqVO.getDjxh())
                .eqIfPresent(XebfDO::getDeptId, reqVO.getDeptId())
                .likeIfPresent(XebfDO::getNsrmc, reqVO.getNsrmc())
                .eqIfPresent(XebfDO::getFbbj, reqVO.getFbbj())
                .orderByDesc(XebfDO::getGhjfId));
    }
}
