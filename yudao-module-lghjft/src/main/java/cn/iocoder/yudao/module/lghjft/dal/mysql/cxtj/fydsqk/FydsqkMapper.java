package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.fydsqk;

import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.fydsqk.FydsqkDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FydsqkMapper {

    /**
     * 分月代收情况聚合查询 -- GROUP BY WITH ROLLUP（全部）
     */
    List<FydsqkDO> selectAggregateList();

    /**
     * 分月代收情况聚合查询 -- GROUP BY WITH ROLLUP（按日期范围）
     */
    List<FydsqkDO> selectAggregateListByDateRange(@Param("beginRkrq") String beginRkrq,
                                                   @Param("endRkrq") String endRkrq);
}
