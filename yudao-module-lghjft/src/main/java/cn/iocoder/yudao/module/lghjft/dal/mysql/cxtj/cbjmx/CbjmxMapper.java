package cn.iocoder.yudao.module.lghjft.dal.mysql.cxtj.cbjmx;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.cbjmx.vo.CbjmxPageReqVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.cbjmx.vo.CbjmxhzResVO;
import cn.iocoder.yudao.module.lghjft.controller.admin.cxtj.cbjmx.vo.CbjmxtjResVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.cxtj.cbjmx.CbjmxDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CbjmxMapper extends BaseMapperX<CbjmxDO> {

    default PageResult<CbjmxDO> selectPage(CbjmxPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CbjmxDO>()
                .eqIfPresent(CbjmxDO::getNd, reqVO.getNd())
                .eqIfPresent(CbjmxDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(CbjmxDO::getShxydm, reqVO.getShxydm())
                .eqIfPresent(CbjmxDO::getDjxh, reqVO.getDjxh())
                .eqIfPresent(CbjmxDO::getNsrmc, reqVO.getNsrmc())
                .orderByDesc(CbjmxDO::getGhjfId));
    }

    List<CbjmxtjResVO> selectTjList(@Param("req") CbjmxPageReqVO req);

    List<CbjmxhzResVO> selectHzList(@Param("req") CbjmxPageReqVO req);
}
