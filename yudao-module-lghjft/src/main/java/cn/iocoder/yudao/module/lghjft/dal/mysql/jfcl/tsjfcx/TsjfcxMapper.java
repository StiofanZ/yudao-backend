package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.tsjfcx;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.tsjfcx.vo.TsjfcxPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.tsjfcl.GhJfTsjfDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.tsjfcx.TsjfcxDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TsjfcxMapper extends BaseMapperX<TsjfcxDO> {

    /**
     * v1: selectTsjfcxList - LEFT JOIN gh_jf_tsjf + WHERE b.tsjfbj is not null
     * all 10 search filters
     */
    IPage<TsjfcxDO> selectTsjfcxPage(Page<TsjfcxDO> page, @Param("query") TsjfcxPageReqVO query);

    /**
     * v1: selectTsjfcxByGhjfId - detail with child gh_jf_tsjf via LEFT JOIN collection
     */
    TsjfcxDO selectTsjfcxByGhjfId(@Param("ghjfId") Long ghjfId);

    /**
     * v1: insertTsjfcx
     */
    int insertTsjfcx(TsjfcxDO tsjfcx);

    /**
     * v1: updateTsjfcx
     */
    int updateTsjfcx(TsjfcxDO tsjfcx);

    /**
     * v1: deleteTsjfcxByGhjfId
     */
    int deleteTsjfcxByGhjfId(@Param("ghjfId") Long ghjfId);

    /**
     * v1: deleteTsjfcxByGhjfIds (batch delete main table)
     */
    int deleteTsjfcxByGhjfIds(@Param("ghjfIds") Long[] ghjfIds);

    /**
     * v1: deleteGhJfTsjfByGhjfIds (batch delete child table)
     */
    int deleteGhJfTsjfByGhjfIds(@Param("ghjfIds") Long[] ghjfIds);

    /**
     * v1: deleteGhJfTsjfByGhjfId (delete child for single parent)
     */
    int deleteGhJfTsjfByGhjfId(@Param("ghjfId") Long ghjfId);

    /**
     * v1: batchGhJfTsjf (batch insert child records)
     */
    int batchGhJfTsjf(@Param("list") List<GhJfTsjfDO> list);
}
