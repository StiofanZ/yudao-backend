package cn.iocoder.yudao.module.lghjft.dal.mysql.xwqy.xwqyjfmx60;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjfmx60.vo.Xwqyjfmx60PageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xwqy.xwqyjfyfmx.XwqyjfyfmxDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Xwqyjfmx60Mapper extends BaseMapperX<XwqyjfyfmxDO> {

    List<XwqyjfyfmxDO> selectXwqyjfmx60List(@Param("req") Xwqyjfmx60PageReqVO req);

    long selectXwqyjfmx60Count(@Param("req") Xwqyjfmx60PageReqVO req);
}
