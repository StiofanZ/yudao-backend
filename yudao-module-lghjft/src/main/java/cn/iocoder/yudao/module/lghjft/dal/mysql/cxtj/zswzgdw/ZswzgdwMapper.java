package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.zswzgdw;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.zswzgdw.vo.ZswzgdwPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.zswzgdw.ZswzgdwDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ZswzgdwMapper extends BaseMapperX<ZswzgdwDO> {

    default PageResult<ZswzgdwDO> selectPage(ZswzgdwPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ZswzgdwDO>()
                .eqIfPresent(ZswzgdwDO::getDwdm, reqVO.getDwdm())
                .eqIfPresent(ZswzgdwDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(ZswzgdwDO::getShxydm, reqVO.getShxydm())
                .eqIfPresent(ZswzgdwDO::getNsrmc, reqVO.getNsrmc())
                .eqIfPresent(ZswzgdwDO::getDjxh, reqVO.getDjxh())
                .orderByDesc(ZswzgdwDO::getDjxh));
    }
}
