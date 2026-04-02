package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.jrbxzqdw;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.jrbxzqdw.vo.JrbxzqdwPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.jrbxzqdw.JrbxzqdwDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JrbxzqdwMapper extends BaseMapperX<JrbxzqdwDO> {

    default PageResult<JrbxzqdwDO> selectPage(JrbxzqdwPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<JrbxzqdwDO>()
                .eqIfPresent(JrbxzqdwDO::getDwdm, reqVO.getDwdm())
                .eqIfPresent(JrbxzqdwDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(JrbxzqdwDO::getShxydm, reqVO.getShxydm())
                .eqIfPresent(JrbxzqdwDO::getNsrmc, reqVO.getNsrmc())
                .eqIfPresent(JrbxzqdwDO::getDjxh, reqVO.getDjxh())
                .orderByDesc(JrbxzqdwDO::getId));
    }
}
