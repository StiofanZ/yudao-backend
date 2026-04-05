package cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.znjzz;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.znjzz.vo.ZnjzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.znjzz.vo.ZnjzzResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.znjzz.ZnjzzDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ZnjzzMapper extends BaseMapperX<ZnjzzDO> {

    List<ZnjzzResVO> selectLegacyList(@Param("req") ZnjzzPageReqVO reqVO);
}
