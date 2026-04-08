package cn.iocoder.yudao.module.lghjft.dal.mysql.ghcbj.cbjtz;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjtz.vo.CbjtzBatchReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjtz.vo.CbjtzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.ghcbj.cbjtz.vo.CbjtzResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.ghcbj.cbjtz.CbjtzDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CbjtzMapper extends BaseMapperX<CbjtzDO> {

    /**
     * v1 selectCbjtzList: from cbjtz view + left join cxtj_cbjqrfb, jsbj='Y', order by rkrq desc
     */
    List<CbjtzResVO> selectCbjtzPageList(@Param("req") CbjtzPageReqVO req);

    /**
     * count for cbjtz page
     */
    long selectCbjtzPageCount(@Param("req") CbjtzPageReqVO req);

    /**
     * v1 selectDgjftzList: from gh_jf + left join cxtj_cbjqrfb, jsbj='Y' AND zspm_dm='399001010' AND jcghje!='0' AND cbjthbj IN ('D','Z')
     */
    List<CbjtzResVO> selectDgjftzPageList(@Param("req") CbjtzPageReqVO req);

    /**
     * count for dgjftz page
     */
    long selectDgjftzPageCount(@Param("req") CbjtzPageReqVO req);

    /**
     * v1 batchInsertOrUpdateChildData: insert/update cxtj_cbjqrfb
     */
    void batchInsertOrUpdateCbjqrfb(@Param("list") List<CbjtzBatchReqVO> list);

    /**
     * v1 batchUpdateMainTableModifyTime: update gh_jf set CBJTHBJ='Z'
     */
    void batchUpdateMainTable(@Param("ghjfIds") List<Long> ghjfIds, @Param("updateBy") String updateBy);

    /**
     * v1 deleteCbjqrfbByGhjfIds: cascade delete from cxtj_cbjqrfb
     */
    void deleteCbjqrfbByGhjfIds(@Param("ghjfIds") List<Long> ghjfIds);

    /**
     * v1 deleteCbjqrfbByGhjfId: cascade delete single
     */
    void deleteCbjqrfbByGhjfId(@Param("ghjfId") Long ghjfId);

    /**
     * v1 batchCbjqrfb: insert into cxtj_cbjqrfb (used by update/create)
     */
    void batchCbjqrfb(@Param("list") List<CbjtzBatchReqVO> list);

    /**
     * v1 updateCbjtz: update gh_jf set CBJTHBJ='Z', CBJTHCZY, CBJTHRQ, UPDATE_BY, UPDATE_TIME
     */
    void updateCbjtzById(@Param("ghjfId") Long ghjfId, @Param("updateBy") String updateBy);
}
