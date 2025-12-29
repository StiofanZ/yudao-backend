package cn.gszs.yudao.module.lghjft.dal.mysql.wfsqtfsq;

import cn.gszs.yudao.module.lghjft.controller.admin.wfsqtfsq.vo.WfSqTfsqPageReqVO;
import cn.gszs.yudao.module.lghjft.dal.dataobject.wfsqtfsq.WfSqTfsqDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.apache.ibatis.annotations.Mapper;

/**
 * 申请-退费申请 Mapper
 *
 * @author 李文军
 */
@Mapper
public interface WfSqTfsqMapper extends BaseMapperX<WfSqTfsqDO> {

    default PageResult<WfSqTfsqDO> selectPage(WfSqTfsqPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WfSqTfsqDO>()
                .eqIfPresent(WfSqTfsqDO::getTfsqmxId, reqVO.getTfsqmxId())
                .eqIfPresent(WfSqTfsqDO::getSqtflxDm, reqVO.getSqtflxDm())
                .eqIfPresent(WfSqTfsqDO::getProcessInstanceId, reqVO.getProcessInstanceId())
                .eqIfPresent(WfSqTfsqDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(WfSqTfsqDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(WfSqTfsqDO::getId));
    }

}