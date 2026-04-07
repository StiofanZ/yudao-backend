package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.fydsqk;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.fydsqk.FydsqkDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FydsqkMapper extends BaseMapperX<FydsqkDO> {

    /**
     * 分月代收情况聚合查询 — 还原 V1 的 GROUP BY ... WITH ROLLUP 逻辑。
     *
     * <p>不带日期范围过滤（查全部）</p>
     */
    @Select("SELECT " +
            "CASE WHEN a.DSYF IS NOT NULL THEN a.DSYF ELSE '合计' END DSYF, " +
            "SUM(a.RKJF) RKJF, SUM(a.GHJF) GHJF, SUM(a.CBJ) CBJ, SUM(a.ZNJ) ZNJ " +
            "FROM fydsqk a WHERE 1=1 " +
            "GROUP BY a.DSYF WITH ROLLUP")
    List<FydsqkDO> selectAggregateList();

    /**
     * 分月代收情况聚合查询 — 还原 V1 的 GROUP BY ... WITH ROLLUP 逻辑。
     *
     * <p>带日期范围过滤：DSYF BETWEEN beginRkrq AND endRkrq</p>
     */
    @Select("SELECT " +
            "CASE WHEN a.DSYF IS NOT NULL THEN a.DSYF ELSE '合计' END DSYF, " +
            "SUM(a.RKJF) RKJF, SUM(a.GHJF) GHJF, SUM(a.CBJ) CBJ, SUM(a.ZNJ) ZNJ " +
            "FROM fydsqk a WHERE DSYF BETWEEN #{beginRkrq} AND #{endRkrq} " +
            "GROUP BY a.DSYF WITH ROLLUP")
    List<FydsqkDO> selectAggregateListByDateRange(@Param("beginRkrq") String beginRkrq,
                                                   @Param("endRkrq") String endRkrq);
}
