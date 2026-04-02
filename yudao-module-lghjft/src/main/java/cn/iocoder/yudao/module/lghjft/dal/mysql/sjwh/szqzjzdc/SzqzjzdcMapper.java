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
                .eqIfPresent(SzqzjzdcDO::getKjn, reqVO.getKjn())
                .orderByDesc(SzqzjzdcDO::getPzbh));
    }

    List<SzqzjzdcDO> selectLegacySdjzList(@Param("req") SzqzjzdcPageReqVO reqVO);

    List<SzqzjzdcDO> selectLegacyHyjzList(@Param("req") SzqzjzdcPageReqVO reqVO);
}
