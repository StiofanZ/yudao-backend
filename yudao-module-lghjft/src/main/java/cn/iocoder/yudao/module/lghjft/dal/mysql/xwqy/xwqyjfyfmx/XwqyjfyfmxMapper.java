package cn.iocoder.yudao.module.lghjft.dal.mysql.xwqy.xwqyjfyfmx;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjfyfmx.vo.XwqyjfyfmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xwqy.xwqyjfyfmx.XwqyjfyfmxDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XwqyjfyfmxMapper extends BaseMapperX<XwqyjfyfmxDO> {

    /**
     * 60% fund details list (v1: selectXwqyjfListyf)
     */
    List<XwqyjfyfmxDO> selectXwqyjfyfmxList(@Param("req") XwqyjfyfmxPageReqVO req);

    /**
     * 60% fund details count
     */
    long selectXwqyjfyfmxCount(@Param("req") XwqyjfyfmxPageReqVO req);
}
