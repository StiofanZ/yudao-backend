package cn.iocoder.yudao.module.lghjft.dal.mysql.workflow.dwdbsq;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.workflow.dwdbsq.WfDbsqDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
 * 工会隶属关系调拨申请 Mapper
 *
 * @author 李文军
 */
@Mapper
public interface WfDbsqMapper extends BaseMapperX<WfDbsqDO> {

    //    判断本年度是否有欠缴费
    @Select("<script>" +
            "SELECT COUNT(*) " +
            "FROM gh_jf " +
            "WHERE djxh = #{djxh} " +
            "  AND (ynse - IFNULL(jmse, 0) - IFNULL(yjse, 0)) > 0.01 " +
            "  AND YEAR(skssqq) = YEAR(NOW()) " +
            "</script>")
    Integer selectArrearsCountByDjxh(@Param("djxh") String djxh);
}