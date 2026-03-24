package cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.jfhzjnsq;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.jfhzjnsq.GhWfJfhzjnsqmxDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
 * 工会经费汇总缴纳申请明细表（分支机构） Mapper
 *
 * @author 李文军
 */
@Mapper
public interface GhWfJfhzjnsqmxMapper extends BaseMapperX<GhWfJfhzjnsqmxDO> {

    //    判断本年度是否有欠缴费
    @Select("<script>" +
            "SELECT COUNT(*) " +
            "FROM gh_jf " +
            "WHERE djxh = #{djxh} " +
            "  AND (ynse - IFNULL(jmse, 0) - IFNULL(yjse, 0)) > 0.01 " +
            "  AND YEAR(skssqq) = YEAR(NOW()) " +
            "</script>")
    Integer selectArrearsCountByDjxh(@Param("djxh") String djxh);

    /**
     * 根据登记序号 djxh 查询 gh_hj 表中的主管工会 deptid
     */
    @Select("SELECT dept_id FROM gh_hj WHERE djxh = #{djxh} LIMIT 1")
    String selectDeptIdByDjxh(@Param("djxh") String djxh);
}