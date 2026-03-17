package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.hbfhz;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.hbfhz.YhbfhzDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.hbfhz.vo.*;

/**
 * 银行拨付汇总 Mapper
 *
 * @author 李文军
 */
@Mapper
public interface YhbfhzMapper extends BaseMapperX<YhbfhzDO> {

    default PageResult<YhbfhzDO> selectPage(YhbfhzPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<YhbfhzDO>()
                .eqIfPresent(YhbfhzDO::getBfhzpch, reqVO.getBfhzpch())
                .eqIfPresent(YhbfhzDO::getBfidq, reqVO.getBfidq())
                .eqIfPresent(YhbfhzDO::getBfidz, reqVO.getBfidz())
                .eqIfPresent(YhbfhzDO::getBfbmc, reqVO.getBfbmc())
                .eqIfPresent(YhbfhzDO::getBs, reqVO.getBs())
                .eqIfPresent(YhbfhzDO::getHzje, reqVO.getHzje())
                .eqIfPresent(YhbfhzDO::getThbs, reqVO.getThbs())
                .eqIfPresent(YhbfhzDO::getThje, reqVO.getThje())
                .eqIfPresent(YhbfhzDO::getSbbs, reqVO.getSbbs())
                .eqIfPresent(YhbfhzDO::getSbje, reqVO.getSbje())
                .eqIfPresent(YhbfhzDO::getCgbs, reqVO.getCgbs())
                .eqIfPresent(YhbfhzDO::getCgje, reqVO.getCgje())
                .eqIfPresent(YhbfhzDO::getUuid, reqVO.getUuid())
                .eqIfPresent(YhbfhzDO::getBfzt, reqVO.getBfzt())
                .eqIfPresent(YhbfhzDO::getBfjg, reqVO.getBfjg())
                .eqIfPresent(YhbfhzDO::getThrq, reqVO.getThrq())
                .eqIfPresent(YhbfhzDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(YhbfhzDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(YhbfhzDO::getUpdateBy, reqVO.getUpdateBy())
                .eqIfPresent(YhbfhzDO::getBfidStr, reqVO.getBfidStr())
                .orderByDesc(YhbfhzDO::getBfhzid));
    }

}