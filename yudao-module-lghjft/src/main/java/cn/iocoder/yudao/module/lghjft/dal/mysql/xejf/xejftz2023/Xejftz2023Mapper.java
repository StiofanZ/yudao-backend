package cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xejftz2023;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejftz2023.vo.Xejftz2023PageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejftz2023.vo.Xejftz2023ResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejftz2023.Xejftz2023DO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Xejftz2023Mapper extends BaseMapperX<Xejftz2023DO> {

    /**
     * v1: selectXetjList from Xejf2023Mapper.xml
     */
    List<Xejftz2023ResVO> selectXetjList(@Param("req") Xejftz2023PageReqVO req);
}
