package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.fyfcqk;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.fyfcqk.vo.FyfcqkPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.fyfcqk.FyfcqkDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FyfcqkMapper extends BaseMapperX<FyfcqkDO> {

    default PageResult<FyfcqkDO> selectPage(FyfcqkPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FyfcqkDO>()
                .eqIfPresent(FyfcqkDO::getDeptId, reqVO.getDeptId())
                .orderByDesc(FyfcqkDO::getDeptId));
    }
}
