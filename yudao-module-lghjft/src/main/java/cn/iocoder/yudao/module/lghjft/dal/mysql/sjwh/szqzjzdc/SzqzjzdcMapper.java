package cn.iocoder.yudao.module.lghjft.dal.mysql.sjwh.szqzjzdc;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.lghjft.controller.admin.sjwh.szqzjzdc.vo.SzqzjzdcPageReqVO;
import cn.iocoder.yudao.module.lghjft.dal.dataobject.sjwh.szqzjzdc.SzqzjzdcDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SzqzjzdcMapper extends BaseMapperX<SzqzjzdcDO> {

    default PageResult<SzqzjzdcDO> selectPage(SzqzjzdcPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SzqzjzdcDO>()
                .eqIfPresent(SzqzjzdcDO::getPzbh, reqVO.getPzbh())
                .eqIfPresent(SzqzjzdcDO::getKjn, reqVO.getKjn())
                .eqIfPresent(SzqzjzdcDO::getPzlx, reqVO.getPzlx())
                .eqIfPresent(SzqzjzdcDO::getPzrq, reqVO.getPzrq())
                .eqIfPresent(SzqzjzdcDO::getKjtx, reqVO.getKjtx())
                .likeIfPresent(SzqzjzdcDO::getZy, reqVO.getZy())
                .eqIfPresent(SzqzjzdcDO::getKjkmm, reqVO.getKjkmm())
                .eqIfPresent(SzqzjzdcDO::getDfje, reqVO.getDfje())
                .eqIfPresent(SzqzjzdcDO::getFjzs, reqVO.getFjzs())
                .eqIfPresent(SzqzjzdcDO::getZdr, reqVO.getZdr())
                .eqIfPresent(SzqzjzdcDO::getZjxz, reqVO.getZjxz())
                .likeIfPresent(SzqzjzdcDO::getXjgh, reqVO.getXjgh())
                .orderByDesc(SzqzjzdcDO::getPzbh));
    }

    /** V1: selectSzqzjzdcList — main list (UNION ALL from szqzdzzc view) */
    List<SzqzjzdcDO> selectLegacyMainList(@Param("req") SzqzjzdcPageReqVO reqVO);

    /** V1: selectSzqzjzdcListsd — 属地 list (from HYJFCX) */
    List<SzqzjzdcDO> selectLegacySdjzList(@Param("req") SzqzjzdcPageReqVO reqVO);

    /** V1: selectSzqzjzdcListhy — 行业 list (from HYJFCX) */
    List<SzqzjzdcDO> selectLegacyHyjzList(@Param("req") SzqzjzdcPageReqVO reqVO);

    /** V1: selectSzqzjzdcListcbj — 筹备金 list (from cbjjzxx) */
    List<SzqzjzdcDO> selectLegacyCbjList(@Param("req") SzqzjzdcPageReqVO reqVO);
}
