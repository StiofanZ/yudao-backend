package cn.iocoder.yudao.module.lghjft.dal.mysql.hjgl.bqgl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.bqgl.vo.BqglPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hjgl.bqgl.vo.BqglRespVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hjgl.bqgl.GhDmHjBqDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;
import java.util.List;

@Mapper
public interface GhDmHjBqMapper extends BaseMapperX<GhDmHjBqDO> {

    @Select("SELECT id, bq_mc as bqMc FROM gh_dm_hj_bq WHERE deleted = 0 AND (dept_id IS NULL OR dept_id = #{deptId})")
    List<BqglRespVO> selectBqxxList(@Param("deptId") Long deptId);

    default PageResult<GhDmHjBqDO> selectPage(BqglPageReqVO reqVO, Collection<Long> deptIds) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GhDmHjBqDO>()
                .likeIfPresent(GhDmHjBqDO::getBqMc, reqVO.getBqMc())
                .betweenIfPresent(GhDmHjBqDO::getCreateTime, reqVO.getCreateTime())
                .and(w -> w.isNull(GhDmHjBqDO::getDeptId) // 系统级标签
                        .or().in(deptIds != null && !deptIds.isEmpty(), GhDmHjBqDO::getDeptId, deptIds)) // 当前部门及上级
                .orderByDesc(GhDmHjBqDO::getId));
    }
}
