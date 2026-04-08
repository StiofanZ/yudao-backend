package cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.sxfzz;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.sxfzz.vo.SxfzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.sxfzz.vo.SxfzzResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.sxfzz.SxfzzDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SxfzzMapper extends BaseMapperX<SxfzzDO> {

    /** v1 selectSxfzzList — with dm_swjg join */
    List<SxfzzResVO> selectLegacyList(@Param("req") SxfzzPageReqVO reqVO);

    /** v1 selectSxfzzByHkxxId — detail with qrsz cascade */
    SxfzzResVO selectLegacyById(@Param("hkxxId") Long hkxxId);
}
