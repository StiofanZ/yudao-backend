package cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xejfzzgl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfzzgl.vo.XejfghzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejfzzgl.XejfghzzDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface XejfghzzMapper extends BaseMapperX<XejfghzzDO> {

    default PageResult<XejfghzzDO> selectPage(XejfghzzPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<XejfghzzDO>()
                .eqIfPresent(XejfghzzDO::getDjxh, reqVO.getDjxh())
                .eqIfPresent(XejfghzzDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(XejfghzzDO::getShxydm, reqVO.getShxydm())
                .likeIfPresent(XejfghzzDO::getNsrmc, reqVO.getNsrmc())
                .orderByDesc(XejfghzzDO::getDjxh));
    }
}
