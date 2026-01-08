package cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.yhwd;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.yhwd.vo.YhwdPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.yhwd.YhwdDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 银行网点 Mapper
 *
 * @author 李文军
 */
@Mapper
public interface YhwdMapper extends BaseMapperX<YhwdDO> {

    default PageResult<YhwdDO> selectPage(YhwdPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<YhwdDO>()
                .eqIfPresent(YhwdDO::getYhhbDm, reqVO.getYhhbDm())
                .eqIfPresent(YhwdDO::getYhwdDm, reqVO.getYhwdDm())
                .eqIfPresent(YhwdDO::getYhwdmc, reqVO.getYhwdmc())
                .eqIfPresent(YhwdDO::getYhwdjc, reqVO.getYhwdjc())
                .eqIfPresent(YhwdDO::getWdhh, reqVO.getWdhh())
                .eqIfPresent(YhwdDO::getQshh, reqVO.getQshh())
                .eqIfPresent(YhwdDO::getXzqhDm, reqVO.getXzqhDm())
                .eqIfPresent(YhwdDO::getSxh, reqVO.getSxh())
                .eqIfPresent(YhwdDO::getYxqz, reqVO.getYxqz())
                .eqIfPresent(YhwdDO::getWddz, reqVO.getWddz())
                .eqIfPresent(YhwdDO::getWddh, reqVO.getWddh())
                .orderByDesc(YhwdDO::getYhhbId));
    }

}