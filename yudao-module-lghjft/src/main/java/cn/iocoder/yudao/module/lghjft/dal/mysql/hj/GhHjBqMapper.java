package cn.iocoder.yudao.module.lghjft.dal.mysql.hj;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.bqgl.vo.BqglHjxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hj.bqgl.vo.BqglHjxxRespVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hj.bqgl.GhHjBqDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.iocoder.yudao.framework.mybatis.core.util.MyBatisUtils;

import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * 户籍标签 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface GhHjBqMapper extends BaseMapperX<GhHjBqDO> {

    @Select("<script>" +
            "select * from gh_hj_bq " +
            "where bq_id = #{bqId} and djxh in " +
            "<foreach item='djxh' collection='djxhList' open='(' separator=',' close=')'>" +
            "#{djxh}" +
            "</foreach>" +
            "</script>")
    List<GhHjBqDO> selectListIgnoreDelete(@Param("bqId") String bqId, @Param("djxhList") List<String> djxhList);

    @Select("<script>" +
            "select t1.djxh, t1.shxydm, t1.nsrmc, t2.bq_id as bq_dm, t3.bq_mc, t2.yxqq, t2.yxqz " +
            "from gh_hj t1 " +
            "left join gh_hj_bq t2 on t1.djxh = t2.djxh and t1.nsrzt_dm &lt; '07' and t2.deleted = 0 and t2.yxqz &gt;= CURDATE() " +
            "left join gh_dm_hj_bq t3 on t2.bq_id = t3.id and t3.id = #{reqVO.bqDm} " +
            "where t1.dept_id = #{deptId} " +
            "<if test=\"reqVO.shxydm != null and reqVO.shxydm != ''\">" +
            "  and t1.shxydm like concat('%', #{reqVO.shxydm}, '%') " +
            "</if>" +
            "order by case when t2.bq_id is not null then 0 else 1 end, t1.djxh desc" +
            "</script>")
    IPage<BqglHjxxRespVO> getHjxxPage(IPage<BqglHjxxRespVO> page, @Param("reqVO") BqglHjxxPageReqVO reqVO, @Param("deptId") Long deptId);
    
    default PageResult<BqglHjxxRespVO> getHjxxPage(BqglHjxxPageReqVO reqVO, Long deptId) {
        IPage<BqglHjxxRespVO> page = getHjxxPage(MyBatisUtils.buildPage(reqVO), reqVO, deptId);
        return new PageResult<>(page.getRecords(), page.getTotal());
    }
}
