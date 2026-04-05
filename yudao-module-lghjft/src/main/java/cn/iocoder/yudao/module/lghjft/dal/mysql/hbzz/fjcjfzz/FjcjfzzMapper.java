package cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.fjcjfzz;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.fjcjfzz.vo.FjcjfzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.fjcjfzz.vo.FjcjfzzResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.fjcjfzz.FjcjfzzDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FjcjfzzMapper extends BaseMapperX<FjcjfzzDO> {

    List<FjcjfzzResVO> selectLegacyList(@Param("req") FjcjfzzPageReqVO reqVO);
}
