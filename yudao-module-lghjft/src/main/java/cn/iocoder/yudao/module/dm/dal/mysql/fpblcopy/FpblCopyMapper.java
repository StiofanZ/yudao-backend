package cn.iocoder.yudao.module.dm.dal.mysql.fpblcopy;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.dm.dal.dataobject.fpblcopy.FpblCopyDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.dm.controller.admin.fpblcopy.vo.*;

/**
 * 分配比例 Mapper
 *
 * @author 李文军
 */
@Mapper
public interface FpblCopyMapper extends BaseMapperX<FpblCopyDO> {

    default PageResult<FpblCopyDO> selectPage(FpblCopyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FpblCopyDO>()
                .eqIfPresent(FpblCopyDO::getLx, reqVO.getLx())
                .eqIfPresent(FpblCopyDO::getMs, reqVO.getMs())
                .eqIfPresent(FpblCopyDO::getYxqq, reqVO.getYxqq())
                .eqIfPresent(FpblCopyDO::getYxqz, reqVO.getYxqz())
                .eqIfPresent(FpblCopyDO::getXybz, reqVO.getXybz())
                .eqIfPresent(FpblCopyDO::getJcghbl, reqVO.getJcghbl())
                .eqIfPresent(FpblCopyDO::getHyghbl, reqVO.getHyghbl())
                .eqIfPresent(FpblCopyDO::getSdghbl, reqVO.getSdghbl())
                .eqIfPresent(FpblCopyDO::getXjghbl, reqVO.getXjghbl())
                .eqIfPresent(FpblCopyDO::getSjghbl, reqVO.getSjghbl())
                .eqIfPresent(FpblCopyDO::getSzghbl, reqVO.getSzghbl())
                .eqIfPresent(FpblCopyDO::getQgzghbl, reqVO.getQgzghbl())
                .eqIfPresent(FpblCopyDO::getSjcjbl, reqVO.getSjcjbl())
                .eqIfPresent(FpblCopyDO::getSdsjbl, reqVO.getSdsjbl())
                .eqIfPresent(FpblCopyDO::getSwjgbl, reqVO.getSwjgbl())
                .eqIfPresent(FpblCopyDO::getYxj, reqVO.getYxj())
                .orderByDesc(FpblCopyDO::getBlId));
    }

}