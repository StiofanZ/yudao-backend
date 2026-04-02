package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.dpzsjyzjfmx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.dpzsjyzjfmx.vo.DpzsjyzjfmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.dpzsjyzjfmx.DpzsjyzjfmxDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DpzsjyzjfmxMapper extends BaseMapperX<DpzsjyzjfmxDO> {

    default PageResult<DpzsjyzjfmxDO> selectPage(DpzsjyzjfmxPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DpzsjyzjfmxDO>()
                .eqIfPresent(DpzsjyzjfmxDO::getDjxh, reqVO.getDjxh())
                .orderByDesc(DpzsjyzjfmxDO::getSpuuid));
    }
}
