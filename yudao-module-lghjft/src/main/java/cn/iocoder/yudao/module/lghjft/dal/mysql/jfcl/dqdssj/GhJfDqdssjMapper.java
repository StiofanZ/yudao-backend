package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.dqdssj;

import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.dqdssj.GhjfDqdssjVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GhJfDqdssjMapper {

    int insertBatch(@Param("list") List<GhjfDqdssjVo> partition);

    int deleteBatch(@Param("list") List<String> spuuidList);

    List<String> queryUpdateBySpuuidList(@Param("list") List<String> spuuidList);

    int updateBatch(@Param("list") List<GhjfDqdssjVo> updateList);
}
