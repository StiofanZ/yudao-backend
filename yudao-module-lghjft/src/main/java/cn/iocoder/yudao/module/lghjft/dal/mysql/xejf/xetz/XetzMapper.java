package cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xetz;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xetz.vo.XetzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xetz.XetzDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface XetzMapper extends BaseMapperX<XetzDO> {

    default PageResult<XetzDO> selectPage(XetzPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<XetzDO>()
                .eqIfPresent(XetzDO::getJfqj, reqVO.getJfqj())
                .eqIfPresent(XetzDO::getDjxh, reqVO.getDjxh())
                .eqIfPresent(XetzDO::getShxydm, reqVO.getShxydm())
                .likeIfPresent(XetzDO::getNsrmc, reqVO.getNsrmc())
                .eqIfPresent(XetzDO::getDeptId, reqVO.getDeptId())
                .orderByDesc(XetzDO::getJfqj));
    }
}
