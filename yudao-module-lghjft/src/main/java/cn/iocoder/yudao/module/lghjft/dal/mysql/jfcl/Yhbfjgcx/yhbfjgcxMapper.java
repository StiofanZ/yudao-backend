package cn.iocoder.yudao.module.lghjft.dal.mysql.jfcl.Yhbfjgcx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.jfcl.Yhbfjgcx.vo.yhbfjgcxPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.jfcl.Yhbfjgcx.yhbfjgcxDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 银行拨付结果查询 Mapper
 *
 * @author 李文军
 */
@Mapper
public interface yhbfjgcxMapper extends BaseMapperX<yhbfjgcxDO> {

    default PageResult<yhbfjgcxDO> selectPage(yhbfjgcxPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<yhbfjgcxDO>()
                .eqIfPresent(yhbfjgcxDO::getBfrq, reqVO.getBfrq())
                .eqIfPresent(yhbfjgcxDO::getBfhzid, reqVO.getBfhzid())
                .eqIfPresent(yhbfjgcxDO::getBfbmc, reqVO.getBfbmc())
                .eqIfPresent(yhbfjgcxDO::getBfhzpch, reqVO.getBfhzpch())
                .eqIfPresent(yhbfjgcxDO::getBfzt, reqVO.getBfzt())
                .eqIfPresent(yhbfjgcxDO::getBfzbs, reqVO.getBfzbs())
                .eqIfPresent(yhbfjgcxDO::getBfzje, reqVO.getBfzje())
                .eqIfPresent(yhbfjgcxDO::getCgjg, reqVO.getCgjg())
                .eqIfPresent(yhbfjgcxDO::getCgbs, reqVO.getCgbs())
                .eqIfPresent(yhbfjgcxDO::getCgje, reqVO.getCgje())
                .eqIfPresent(yhbfjgcxDO::getSbjg, reqVO.getSbjg())
                .eqIfPresent(yhbfjgcxDO::getSbbs, reqVO.getSbbs())
                .eqIfPresent(yhbfjgcxDO::getSbje, reqVO.getSbje())
                .eqIfPresent(yhbfjgcxDO::getTpjg, reqVO.getTpjg())
                .eqIfPresent(yhbfjgcxDO::getTpbs, reqVO.getTpbs())
                .eqIfPresent(yhbfjgcxDO::getTpje, reqVO.getTpje())
                .eqIfPresent(yhbfjgcxDO::getFjjg, reqVO.getFjjg())
                .eqIfPresent(yhbfjgcxDO::getFjbs, reqVO.getFjbs())
                .eqIfPresent(yhbfjgcxDO::getFjje, reqVO.getFjje())
                .eqIfPresent(yhbfjgcxDO::getGqjg, reqVO.getGqjg())
                .eqIfPresent(yhbfjgcxDO::getGqbs, reqVO.getGqbs())
                .eqIfPresent(yhbfjgcxDO::getGqje, reqVO.getGqje())
                .eqIfPresent(yhbfjgcxDO::getCxjg, reqVO.getCxjg())
                .eqIfPresent(yhbfjgcxDO::getCxbs, reqVO.getCxbs())
                .eqIfPresent(yhbfjgcxDO::getCxje, reqVO.getCxje())
                .eqIfPresent(yhbfjgcxDO::getCreateBy, reqVO.getCreateBy())
                .eqIfPresent(yhbfjgcxDO::getUpdateBy, reqVO.getUpdateBy())
                .eqIfPresent(yhbfjgcxDO::getRemark, reqVO.getRemark())
                .orderByDesc(yhbfjgcxDO::getBfhzid));
    }

}