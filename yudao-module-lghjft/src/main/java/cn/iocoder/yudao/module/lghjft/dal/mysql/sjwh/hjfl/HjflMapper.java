package cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.hjfl;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;

import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.hjfl.vo.HjflPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.hjfl.HjflDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 户籍分类 Mapper
 *
 * @author 李文军
 */
@Mapper
public interface HjflMapper extends BaseMapperX<HjflDO> {

    default PageResult<HjflDO> selectPage(HjflPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<HjflDO>()
                .eqIfPresent(HjflDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(HjflDO::getBz, reqVO.getBz())
                .eqIfPresent(HjflDO::getHjflDm, reqVO.getHjflDm())
                .eqIfPresent(HjflDO::getHjflmc, reqVO.getHjflmc())
                .eqIfPresent(HjflDO::getSxh, reqVO.getSxh())

                .orderByDesc(HjflDO::getHjflid));
    }

}