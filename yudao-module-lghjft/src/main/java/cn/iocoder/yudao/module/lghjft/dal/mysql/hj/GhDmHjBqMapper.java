package cn.iocoder.yudao.module.lghjft.dal.mysql.hj;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.bqgl.vo.BqglRespVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hj.bqgl.GhDmHjBqDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * 标签归类代码 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface GhDmHjBqMapper extends BaseMapperX<GhDmHjBqDO> {

    @Select("select t2.id as bq_dm, t2.bq_mc, t2.dept_id as dept_id, t1.name as dept_mc, t1.level " +
            "from v_system_dept_reverse t1 " +
            "inner join gh_dm_hj_bq t2 on t1.id = t2.dept_id " +
            "and t1.deleted = 0 " +
            "and t2.deleted = 0 " +
            "where t1.root_id = #{rootId}")
    List<BqglRespVO> selectBqxxList(Long rootId);
}
