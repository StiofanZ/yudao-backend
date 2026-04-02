package cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.ghjfzz;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.ghjfzz.vo.GhjfzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.ghjfzz.vo.GhjfzzResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.ghjfzz.GhjfzzDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GhjfzzMapper extends BaseMapperX<GhjfzzDO> {

    List<GhjfzzResVO> selectLegacyList(@Param("req") GhjfzzPageReqVO reqVO);

    GhjfzzResVO selectLegacyById(@Param("id") Long id);
}
