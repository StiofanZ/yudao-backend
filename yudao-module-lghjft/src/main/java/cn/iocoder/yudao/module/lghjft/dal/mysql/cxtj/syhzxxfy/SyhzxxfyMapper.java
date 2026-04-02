package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.syhzxxfy;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.syhzxxfy.vo.SyhzxxfyPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.syhzxxfy.SyhzxxfyDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SyhzxxfyMapper extends BaseMapperX<SyhzxxfyDO> {

    default PageResult<SyhzxxfyDO> selectPage(SyhzxxfyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SyhzxxfyDO>()
                .eqIfPresent(SyhzxxfyDO::getNd, reqVO.getNd())
                .eqIfPresent(SyhzxxfyDO::getDeptId, reqVO.getDeptId())
                .orderByDesc(SyhzxxfyDO::getNd));
    }
}
