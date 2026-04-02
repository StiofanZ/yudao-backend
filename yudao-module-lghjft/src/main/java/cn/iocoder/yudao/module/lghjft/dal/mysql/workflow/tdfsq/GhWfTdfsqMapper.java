package cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.tdfsq;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.tdfsq.vo.GhWfTdfsqKtfxxResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.tdfsq.GhWfTdfsqDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.tdfsq.GhWfTdfsqmxDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface GhWfTdfsqMapper extends BaseMapperX<GhWfTdfsqDO> {
    @Insert("insert into gh_wf_tdfsqmx (tdfsq_id, spuuid, rkje, tfsq_je, shxydm, nsrmc, skssqq, skssqz) " +
            "values (#{tdfsqId}, #{spuuid}, #{rkje}, #{tfsqJe}, #{shxydm}, #{nsrmc}, #{skssqq}, #{skssqz})")
    void insertMx(GhWfTdfsqmxDO mx);

    default void insertBatchMx(List<GhWfTdfsqmxDO> list) {
        list.forEach(this::insertMx);
    }

    @Select("select * from gh_wf_tdfsqmx where tdfsq_id = #{tdfsqId} order by id asc")
    List<GhWfTdfsqmxDO> selectMxListByTdfsqId(@Param("tdfsqId") Long tdfsqId);

    @Select("<script>" +
            "select jf.spuuid, " +
            "       jf.shxydm, " +
            "       jf.nsrmc, " +
            "       jf.skssqq, " +
            "       jf.skssqz, " +
            "       ifnull(jf.yjse, 0) as rkje, " +
            "       (ifnull(jf.ybtse, 0) - ifnull(jf.jmse, 0)) as ktfje " +
            "from gh_jf jf " +
            "where jf.djxh = #{djxh} " +
            "  and (ifnull(jf.ybtse, 0) - ifnull(jf.jmse, 0)) > 0 " +
            "<if test='skssqq != null'>and jf.skssqq &gt;= #{skssqq} </if>" +
            "<if test='skssqz != null'>and jf.skssqz &lt;= #{skssqz} </if>" +
            "order by jf.skssqz desc" +
            "</script>")
    List<GhWfTdfsqKtfxxResVO> getKtfxxList(
            @Param("djxh") String djxh,
            @Param("skssqq") LocalDate skssqq,
            @Param("skssqz") LocalDate skssqz);

}