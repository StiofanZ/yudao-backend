package cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.ghjfzz;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.ghjfzz.vo.GhjfzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.ghjfzz.vo.GhjfzzResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.ghjfzz.GhjfzzDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GhjfzzMapper extends BaseMapperX<GhjfzzDO> {

    List<GhjfzzResVO> selectLegacyList(@Param("req") GhjfzzPageReqVO reqVO);

    GhjfzzResVO selectLegacyById(@Param("id") Long id);
}
