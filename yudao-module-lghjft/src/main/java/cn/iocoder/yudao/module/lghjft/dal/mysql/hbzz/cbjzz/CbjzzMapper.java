package cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.cbjzz;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.cbjzz.vo.CbjzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.cbjzz.vo.CbjzzResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.cbjzz.CbjzzDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CbjzzMapper extends BaseMapperX<CbjzzDO> {

    /** v1 selectCbjzzList — list page */
    List<CbjzzResVO> selectLegacyList(@Param("req") CbjzzPageReqVO reqVO);

    /** v1 selectCbjzzListcbj — export grouped by sjdm */
    List<CbjzzResVO> selectLegacyListCbj(@Param("req") CbjzzPageReqVO reqVO);

    /** v1 selectCbjzzByHkxxId — get detail with qrsz cascade */
    CbjzzResVO selectLegacyById(@Param("hkxxId") Long hkxxId);
}
