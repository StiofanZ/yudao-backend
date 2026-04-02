package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.fydsqk;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fydsqk.vo.FydsqkPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.fydsqk.FydsqkDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FydsqkMapper extends BaseMapperX<FydsqkDO> {

    default PageResult<FydsqkDO> selectPage(FydsqkPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FydsqkDO>()
                .eqIfPresent(FydsqkDO::getDeptId, reqVO.getDeptId())
                .orderByDesc(FydsqkDO::getDeptId));
    }
}
