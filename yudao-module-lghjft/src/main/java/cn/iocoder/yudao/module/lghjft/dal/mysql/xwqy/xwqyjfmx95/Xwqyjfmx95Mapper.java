package cn.iocoder.yudao.module.lghjft.dal.mysql.xwqy.xwqyjfmx95;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjfmx95.vo.Xwqyjfmx95PageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xwqy.xwqyjf.XwqyjfDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Xwqyjfmx95Mapper extends BaseMapperX<XwqyjfDO> {

    List<XwqyjfDO> selectXwqyjfmx95List(@Param("req") Xwqyjfmx95PageReqVO req);

    long selectXwqyjfmx95Count(@Param("req") Xwqyjfmx95PageReqVO req);
}
