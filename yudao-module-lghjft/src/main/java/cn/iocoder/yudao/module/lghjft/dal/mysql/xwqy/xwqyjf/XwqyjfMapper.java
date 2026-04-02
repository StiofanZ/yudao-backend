package cn.iocoder.yudao.module.lghjft.dal.mysql.xwqy.xwqyjf;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xwqy.xwqyjf.vo.XwqyjfPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xwqy.xwqyjf.XwqyjfDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface XwqyjfMapper extends BaseMapperX<XwqyjfDO> {

    default PageResult<XwqyjfDO> selectPage(XwqyjfPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<XwqyjfDO>()
                .eqIfPresent(XwqyjfDO::getDjxh, reqVO.getDjxh())
                .eqIfPresent(XwqyjfDO::getShxydm, reqVO.getShxydm())
                .eqIfPresent(XwqyjfDO::getNsrmc, reqVO.getNsrmc())
                .eqIfPresent(XwqyjfDO::getDeptId, reqVO.getDeptId())
                .orderByDesc(XwqyjfDO::getGhjfId));
    }

    List<XwqyjfDO> selectPageYf(@Param("req") XwqyjfPageReqVO req);
}
