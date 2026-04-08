package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.tsjfcl;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.tsjfcl.vo.TsjfclPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.tsjfcl.GhJfTsjfDO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.tsjfcl.TsjfclDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TsjfclMapper extends BaseMapperX<TsjfclDO> {

    /**
     * v1: selectTsjfclList - paginated list with all WHERE conditions
     */
    IPage<TsjfclDO> selectTsjfclPage(Page<TsjfclDO> page, @Param("query") TsjfclPageReqVO query);

    /**
     * v1: selectTsjfclByGhjfId - detail with child gh_jf_tsjf via LEFT JOIN collection
     */
    TsjfclDO selectTsjfclByGhjfId(@Param("ghjfId") Long ghjfId);

    /**
     * v1: insertTsjfcl
     */
    int insertTsjfcl(TsjfclDO tsjfcl);

    /**
     * v1: updateTsjfcl
     */
    int updateTsjfcl(TsjfclDO tsjfcl);

    /**
     * v1: deleteTsjfclByGhjfId
     */
    int deleteTsjfclByGhjfId(@Param("ghjfId") Long ghjfId);

    /**
     * v1: deleteTsjfclByGhjfIds (batch delete main table)
     */
    int deleteTsjfclByGhjfIds(@Param("ghjfIds") Long[] ghjfIds);

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

    /**
     * v1: batchInsertOrUpdateChildData (ON DUPLICATE KEY UPDATE)
     */
    void batchInsertOrUpdateChildData(@Param("ghJfTsjfList") List<GhJfTsjfDO> list);

    /**
     * v1: batchUpdateMainTableModifyTime
     */
    void batchUpdateMainTableModifyTime(@Param("ghJfTsjfList") List<GhJfTsjfDO> list);
}
