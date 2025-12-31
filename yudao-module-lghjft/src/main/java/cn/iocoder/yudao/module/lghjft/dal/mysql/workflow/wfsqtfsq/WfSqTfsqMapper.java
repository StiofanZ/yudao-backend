package cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.wfsqtfsq;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.workflow.wfsqtfsq.vo.WfSqTfsqKtfxxRespVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wfsqtfsq.WfSqTfsqDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.wfsqtfsq.WfSqTfsqmxDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * 申请-退费申请 Mapper
 *
 * @author 李文军
 */
@Mapper
public interface WfSqTfsqMapper extends BaseMapperX<WfSqTfsqDO> {

    @Select("<script>" +
            "select spuuid, shxydm, nsrmc, skssqq, skssqz, ifnull(ybtse, 0) - ifnull(jmse, 0) as ktfje " +
            "from gh_jf jf " +
            "where ifnull(ybtse, 0) - ifnull(jmse, 0) > 0 " +
            "and not exists(select 1 from gh_wf_sq_tfsqmx mx where jf.spuuid = mx.spuuid) " +
            "and jf.djxh = #{djxh} " +
            "<if test='skssqq != null'>and jf.skssqq &gt;= #{skssqq} </if>" +
            "<if test='skssqz != null'>and jf.skssqz &lt;= #{skssqz} </if>" +
            "order by skssqz" +
            "</script>")
    List<WfSqTfsqKtfxxRespVO> getKtfxxList(@Param("djxh") String djxh,
                                           @Param("skssqq") LocalDate skssqq,
                                           @Param("skssqz") LocalDate skssqz);

    @Insert("insert into gh_wf_sq_tfsqmx (tfsq_id, spuuid, rkje, tfsq_je, shxydm, nsrmc, skssqq, skssqz) values (#{tfsqId}, #{spuuid}, #{rkje}, #{tfsqJe}, #{shxydm}, #{nsrmc}, #{skssqq}, #{skssqz})")
    void insertMx(WfSqTfsqmxDO mx);

    @Select("select * from gh_wf_sq_tfsqmx where tfsq_id = #{tfsqId}")
    List<WfSqTfsqmxDO> selectMxList(@Param("tfsqId") Long tfsqId);

}
