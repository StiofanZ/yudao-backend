package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.fyfcqk;

import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.fyfcqk.FyfcqkDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FyfcqkMapper {

    /**
     * 分月分成情况聚合查询 -- GROUP BY WITH ROLLUP（全部）
     */
    List<FyfcqkDO> selectAggregateList();

    /**
     * 分月分成情况聚合查询 -- GROUP BY WITH ROLLUP（按日期范围）
     */
    List<FyfcqkDO> selectAggregateListByDateRange(@Param("beginRkrq") String beginRkrq,
                                                   @Param("endRkrq") String endRkrq);
}
