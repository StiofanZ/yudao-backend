package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.top;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.top.vo.TopPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.top.TopDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TopMapper extends BaseMapperX<TopDO> {

    default PageResult<TopDO> selectPage(TopPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TopDO>()
                .eqIfPresent(TopDO::getDjxh, reqVO.getDjxh())
                .eqIfPresent(TopDO::getShxydm, reqVO.getShxydm())
                .eqIfPresent(TopDO::getNsrmc, reqVO.getNsrmc())
                .eqIfPresent(TopDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(TopDO::getDwmc, reqVO.getDwmc())
                .orderByDesc(TopDO::getDjxh));
    }
}
