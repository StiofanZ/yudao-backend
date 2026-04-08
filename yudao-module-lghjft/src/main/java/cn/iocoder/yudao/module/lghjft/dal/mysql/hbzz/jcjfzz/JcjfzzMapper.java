package cn.iocoder.yudao.module.lghjft.dal.mysql.hbzz.jcjfzz;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jcjfzz.vo.JcjfzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.hbzz.jcjfzz.vo.JcjfzzResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.hbzz.jcjfzz.JcjfzzDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JcjfzzMapper extends BaseMapperX<JcjfzzDO> {

    List<JcjfzzResVO> selectLegacyList(@Param("req") JcjfzzPageReqVO reqVO);

    JcjfzzResVO selectLegacyById(@Param("id") Long id);
}
