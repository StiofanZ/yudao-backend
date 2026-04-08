package cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.dmwh.fpbl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.dmwh.fpbl.vo.CsFpblPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.dmwh.fpbl.CsFpblDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CsFpblMapper extends BaseMapperX<CsFpblDO> {

    default PageResult<CsFpblDO> selectPage(CsFpblPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CsFpblDO>()
                .eqIfPresent(CsFpblDO::getLx, reqVO.getLx())
                .likeIfPresent(CsFpblDO::getMs, reqVO.getMs())
                .eqIfPresent(CsFpblDO::getYxqq, reqVO.getYxqq())
                .eqIfPresent(CsFpblDO::getYxqz, reqVO.getYxqz())
                .eqIfPresent(CsFpblDO::getXybz, reqVO.getXybz())
                .eqIfPresent(CsFpblDO::getJcghbl, reqVO.getJcghbl())
                .eqIfPresent(CsFpblDO::getHyghbl, reqVO.getHyghbl())
                .eqIfPresent(CsFpblDO::getSdghbl, reqVO.getSdghbl())
                .eqIfPresent(CsFpblDO::getXjghbl, reqVO.getXjghbl())
                .eqIfPresent(CsFpblDO::getSjghbl, reqVO.getSjghbl())
                .eqIfPresent(CsFpblDO::getSzghbl, reqVO.getSzghbl())
                .eqIfPresent(CsFpblDO::getQgzghbl, reqVO.getQgzghbl())
                .eqIfPresent(CsFpblDO::getSjcjbl, reqVO.getSjcjbl())
                .eqIfPresent(CsFpblDO::getSdsjbl, reqVO.getSdsjbl())
                .eqIfPresent(CsFpblDO::getSwjgbl, reqVO.getSwjgbl())
                .likeIfPresent(CsFpblDO::getTj, reqVO.getTj())
                .eqIfPresent(CsFpblDO::getYxj, reqVO.getYxj())
                .eqIfPresent(CsFpblDO::getMrbz, reqVO.getMrbz())
                .orderByAsc(CsFpblDO::getYxj));
    }
}
