package cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xebfzb;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xebfzb.vo.XebfzbPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xebfzb.XebfzbDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface XebfzbMapper extends BaseMapperX<XebfzbDO> {

    default PageResult<XebfzbDO> selectPage(XebfzbPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<XebfzbDO>()
                .eqIfPresent(XebfzbDO::getXend, reqVO.getXend())
                .eqIfPresent(XebfzbDO::getSjdm, reqVO.getSjdm())
                .eqIfPresent(XebfzbDO::getDeptId, reqVO.getDeptId())
                .orderByDesc(XebfzbDO::getXend));
    }
}
