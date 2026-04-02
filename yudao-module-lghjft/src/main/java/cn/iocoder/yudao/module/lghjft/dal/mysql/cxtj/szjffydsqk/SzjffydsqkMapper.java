package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.szjffydsqk;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.szjffydsqk.vo.SzjffydsqkPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.szjffydsqk.SzjffydsqkDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SzjffydsqkMapper extends BaseMapperX<SzjffydsqkDO> {

    default PageResult<SzjffydsqkDO> selectPage(SzjffydsqkPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SzjffydsqkDO>()
                .eqIfPresent(SzjffydsqkDO::getDwdm, reqVO.getDwdm())
                .eqIfPresent(SzjffydsqkDO::getDwmc, reqVO.getDwmc())
                .orderByDesc(SzjffydsqkDO::getDwdm));
    }
}
