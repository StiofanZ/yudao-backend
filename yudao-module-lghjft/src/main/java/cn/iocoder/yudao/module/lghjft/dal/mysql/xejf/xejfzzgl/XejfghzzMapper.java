package cn.iocoder.yudao.module.lghjft.dal.mysql.xejf.xejfzzgl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.xejf.xejfzzgl.vo.XejfghzzPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.xejf.xejfzzgl.XejfghzzDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Mapper
public interface XejfghzzMapper extends BaseMapperX<XejfghzzDO> {

    private static List<String> toList(String[] values) {
        return values == null ? Collections.emptyList() : Arrays.asList(values);
    }

    default PageResult<XejfghzzDO> selectPage(XejfghzzPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<XejfghzzDO>()
                .eqIfPresent(XejfghzzDO::getDjxh, reqVO.getDjxh())
                .eqIfPresent(XejfghzzDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(XejfghzzDO::getShxydm, reqVO.getShxydm())
                .likeIfPresent(XejfghzzDO::getNsrmc, reqVO.getNsrmc())
                .eqIfPresent(XejfghzzDO::getSsglyxm, reqVO.getSsglyxm())
                .eqIfPresent(XejfghzzDO::getLxr, reqVO.getLxr())
                .eqIfPresent(XejfghzzDO::getLxdh, reqVO.getLxdh())
                .eqIfPresent(XejfghzzDO::getGhlbDm, reqVO.getGhlbDm())
                .eqIfPresent(XejfghzzDO::getXtlbDm, reqVO.getXtlbDm())
                .inIfPresent(XejfghzzDO::getHjfl10Dm, toList(reqVO.getXejfzz255()))
                .inIfPresent(XejfghzzDO::getHjfl5Dm, toList(reqVO.getXejfzz25()))
                .eqIfPresent(XejfghzzDO::getJcghzh, reqVO.getJcghzh())
                .eqIfPresent(XejfghzzDO::getJcghhh, reqVO.getJcghhh())
                .orderByDesc(XejfghzzDO::getDjxh));
    }
}
