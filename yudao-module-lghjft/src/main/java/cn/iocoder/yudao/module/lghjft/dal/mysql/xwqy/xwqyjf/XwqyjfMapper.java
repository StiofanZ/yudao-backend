package cn.iocoder.yudao.module.lghjft.dal.mysql.xwqy.xwqyjf;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjf.vo.XwqyjfPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xwqy.xwqyjf.XwqyjfDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XwqyjfMapper extends BaseMapperX<XwqyjfDO> {

    /**
     * 95% fund details list (v1: selectXwqyjfList)
     */
    List<XwqyjfDO> selectXwqyjfList(@Param("req") XwqyjfPageReqVO req);

    /**
     * 95% fund details count
     */
    long selectXwqyjfCount(@Param("req") XwqyjfPageReqVO req);

    /**
     * 60% fund details list (v1: selectXwqyjfListyf)
     */
    List<XwqyjfDO> selectXwqyjfListyf(@Param("req") XwqyjfPageReqVO req);

    /**
     * 60% fund details count
     */
    long selectXwqyjfCountyf(@Param("req") XwqyjfPageReqVO req);
}
