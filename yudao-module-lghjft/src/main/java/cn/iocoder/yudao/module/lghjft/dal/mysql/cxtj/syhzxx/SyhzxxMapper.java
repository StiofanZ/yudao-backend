package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.syhzxx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.syhzxx.vo.SyhzxxPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.syhzxx.SyhzxxDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SyhzxxMapper extends BaseMapperX<SyhzxxDO> {

    default PageResult<SyhzxxDO> selectPage(SyhzxxPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SyhzxxDO>()
                .eqIfPresent(SyhzxxDO::getDeptId, reqVO.getDeptId())
                .orderByDesc(SyhzxxDO::getDeptId));
    }
}
