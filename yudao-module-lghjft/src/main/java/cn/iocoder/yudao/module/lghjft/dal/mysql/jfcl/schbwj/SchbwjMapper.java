package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.schbwj;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.schbwj.vo.SchbwjPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.schbwj.JfclSchbwjDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * v1 JfclSchbwjMapper: 10 methods across gh_hkxx, gh_jf, gh_qsjshkrj, schbwj
 */
@Mapper
public interface SchbwjMapper extends BaseMapperX<JfclSchbwjDO> {

    /**
     * v1 selecList: paginated query gh_hkxx LEFT JOIN sys_user WHERE yxbj='Y' ORDER BY hkpch desc
     * Mapped in XML.
     */
    List<JfclSchbwjDO> selecList(SchbwjPageReqVO reqVO);

    /**
     * v1 selecList count for pagination
     */
    long selecListCount(SchbwjPageReqVO reqVO);

    /**
     * v1 selectGhjfhb: SELECT * FROM gh_jf WHERE jsrq BETWEEN AND jsbj IN (...) AND hkpch IS NULL
     */
    List<Map<String, Object>> selectGhjfhb(@Param("jsrqStart") String jsrqStart, @Param("jsrqEnd") String jsrqEnd);

    /**
     * v1 getSchbwjList: SELECT * FROM schbwj WHERE jsrq BETWEEN
     */
    List<Map<String, Object>> getSchbwjList(@Param("jsrqStart") String jsrqStart, @Param("jsrqEnd") String jsrqEnd);

    /**
     * v1 selectHkpch: MAX(HKPCH) FROM gh_jf WHERE jsbj IN ('Y','T','W') AND LEFT(HKPCH,8) = dqrq
     */
    String selectHkpch(@Param("dqrq") String dqrq);

    /**
     * v1 insertBatchGhHkxx: batch INSERT gh_hkxx
     */
    void insertBatchHkxx(@Param("list") List<Map<String, Object>> list);

    /**
     * v1 updateGhjfhb: batch UPDATE gh_jf SET HKPCH, UPDATE_BY, UPDATE_TIME WHERE spuuid IN (...)
     */
    void updateGhjfhb(@Param("list") List<Map<String, Object>> list);

    /**
     * v1 updateBatchQsjshkrj: batch UPDATE gh_qsjshkrj SET HKRQ, PCH, UPDATE_BY, UPDATE_TIME
     */
    void updateBatchQsjshkrj(@Param("list") List<Map<String, Object>> list);

    /**
     * v1 deleteGhHkxxByHkpch: DELETE FROM gh_hkxx WHERE HKPCH = hkpch
     */
    void deleteHkxxByHkpch(@Param("hkpch") String hkpch);

    /**
     * v1 selectQsjshkrjList: SELECT * FROM gh_qsjshkrj WHERE LX='1' AND RKRQ IN (...)
     */
    List<Map<String, Object>> selectQsjshkrjList(@Param("list") List<Date> list);
}
