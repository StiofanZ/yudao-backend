package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.yhbfjgcx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.yhbfjgcx.vo.YhbfjgcxPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.yhbfjgcx.YhbfjgcxDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 银行拨付结果查询 Mapper
 *
 * @author 李文军
 */
@Mapper
public interface YhbfjgcxMapper extends BaseMapperX<YhbfjgcxDO> {

    default PageResult<YhbfjgcxDO> selectPage(YhbfjgcxPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<YhbfjgcxDO>()
                .eqIfPresent(YhbfjgcxDO::getBfrq, reqVO.getBfrq())
                .eqIfPresent(YhbfjgcxDO::getBfhzid, reqVO.getBfhzid())
                .eqIfPresent(YhbfjgcxDO::getBfbmc, reqVO.getBfbmc())
                .eqIfPresent(YhbfjgcxDO::getBfhzpch, reqVO.getBfhzpch())
                .eqIfPresent(YhbfjgcxDO::getBfzt, reqVO.getBfzt())
                .eqIfPresent(YhbfjgcxDO::getBfzbs, reqVO.getBfzbs())
                .eqIfPresent(YhbfjgcxDO::getBfzje, reqVO.getBfzje())
                .eqIfPresent(YhbfjgcxDO::getCgjg, reqVO.getCgjg())
                .eqIfPresent(YhbfjgcxDO::getCgbs, reqVO.getCgbs())
                .eqIfPresent(YhbfjgcxDO::getCgje, reqVO.getCgje())
                .eqIfPresent(YhbfjgcxDO::getSbjg, reqVO.getSbjg())
                .eqIfPresent(YhbfjgcxDO::getSbbs, reqVO.getSbbs())
                .eqIfPresent(YhbfjgcxDO::getSbje, reqVO.getSbje())
                .eqIfPresent(YhbfjgcxDO::getTpjg, reqVO.getTpjg())
                .eqIfPresent(YhbfjgcxDO::getTpbs, reqVO.getTpbs())
                .eqIfPresent(YhbfjgcxDO::getTpje, reqVO.getTpje())
                .eqIfPresent(YhbfjgcxDO::getFjjg, reqVO.getFjjg())
                .eqIfPresent(YhbfjgcxDO::getFjbs, reqVO.getFjbs())
                .eqIfPresent(YhbfjgcxDO::getFjje, reqVO.getFjje())
                .eqIfPresent(YhbfjgcxDO::getGqjg, reqVO.getGqjg())
                .eqIfPresent(YhbfjgcxDO::getGqbs, reqVO.getGqbs())
                .eqIfPresent(YhbfjgcxDO::getGqje, reqVO.getGqje())
                .eqIfPresent(YhbfjgcxDO::getCxjg, reqVO.getCxjg())
                .eqIfPresent(YhbfjgcxDO::getCxbs, reqVO.getCxbs())
                .eqIfPresent(YhbfjgcxDO::getCxje, reqVO.getCxje())
                .eqIfPresent(YhbfjgcxDO::getCreateBy, reqVO.getCreateBy())
                .eqIfPresent(YhbfjgcxDO::getUpdateBy, reqVO.getUpdateBy())
                .eqIfPresent(YhbfjgcxDO::getRemark, reqVO.getRemark())
                .orderByDesc(YhbfjgcxDO::getBfhzid));
    }

}
