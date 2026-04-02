package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.ndrwwc;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.ndrwwc.vo.NdrwwcPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.ndrwwc.NdrwwcDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NdrwwcMapper extends BaseMapperX<NdrwwcDO> {

    default PageResult<NdrwwcDO> selectPage(NdrwwcPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<NdrwwcDO>()
                .eqIfPresent(NdrwwcDO::getNd, reqVO.getNd())
                .eqIfPresent(NdrwwcDO::getDwdm, reqVO.getDwdm())
                .eqIfPresent(NdrwwcDO::getDwmc, reqVO.getDwmc())
                .orderByDesc(NdrwwcDO::getNd));
    }
}
