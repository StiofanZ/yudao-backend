package cn.iocoder.yudao.module.lghjft.dal.mysql.hj.ghhj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.ghhj.vo.GhHjPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hj.ghhj.GhHjDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 基层账户空需维护对象 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface GhHjMapper extends BaseMapperX<GhHjDO> {

    default PageResult<GhHjDO> selectPage(GhHjPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GhHjDO>()
                .eqIfPresent(GhHjDO::getDeptId, reqVO.getDeptId())
                .likeIfPresent(GhHjDO::getNsrmc, reqVO.getNsrmc())
                .eqIfPresent(GhHjDO::getShxydm, reqVO.getShxydm())
                .likeIfPresent(GhHjDO::getNsrjc, reqVO.getNsrjc())
                .betweenIfPresent(GhHjDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GhHjDO::getDjxh));
    }

}
